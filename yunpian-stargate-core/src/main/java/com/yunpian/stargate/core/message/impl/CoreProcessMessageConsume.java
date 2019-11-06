package com.yunpian.stargate.core.message.impl;

import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.dto.StargateBodyDTO;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import com.yunpian.stargate.core.dto.StargateDecodeDTO;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.message.IProcessMessageConsume;
import com.yunpian.stargate.core.producer.ProducerFactory;
import com.yunpian.stargate.core.utils.DelayUtils;
import com.yunpian.stargate.core.vo.StargateMessage;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class CoreProcessMessageConsume implements IProcessMessageConsume {

  private static final Logger log = LoggerFactory.getLogger(CoreProcessMessageConsume.class);
  private static final String DELAY_MSEC_GROUP = "DELAY_MSEC_GROUP_CALL_BACK_MESSAGE";

  @Override
  public int processConsume(ConsumeContext consumeContext, MessageExt messageExt,
    ConsumeConcurrentlyContext consumeConcurrentlyContext, Object[] args)
    throws Throwable {

    StargateDecodeDTO stargateDecodeDTO = consumeContext.getDTO(StargateDecodeDTO.class);
    StargateConsumeBaseDTO stargateConsumeBaseDTO = consumeContext
      .getDTO(StargateConsumeBaseDTO.class);
    StargateBodyDTO stargateBodyDTO = consumeContext.getDTO(StargateBodyDTO.class);
    ConsumeMethodsDTO consumeMethodsDTO = consumeContext.getDTO(ConsumeMethodsDTO.class);

    StargateMessage body = stargateDecodeDTO.getRocketClientDecode()
      .decode(messageExt.getBody(), consumeContext);

    long delayMsec = body.getDelayMsec() - System.currentTimeMillis();
    if (delayMsec > 0) {
      if (stargateConsumeBaseDTO.isBroadcasting()) {
        log.warn(
          "consume is broadcasting. prohibition of use @StargateDelayMsec or @StargateDelayParam",
          new StargateException());
      } else {
        //丢回队列
        callBackMessageExt(delayMsec, consumeContext, messageExt);
        return BREAK;
      }
    }

    if (stargateBodyDTO.getBodyIndex() >= 0 && stargateBodyDTO.getBodyClass() != null) {
      args[stargateBodyDTO.getBodyIndex()] = body.getBody();
    }
    if (consumeMethodsDTO.getContextIndex() >= 0) {
      args[consumeMethodsDTO.getContextIndex()] = consumeConcurrentlyContext;
    }
    if (consumeMethodsDTO.getMessageExtIndex() >= 0) {
      args[consumeMethodsDTO.getMessageExtIndex()] = messageExt;
    }
    return SUCCESS;
  }

  private void callBackMessageExt(
    long delayMsec,
    ConsumeContext consumeContext,
    MessageExt messageExt)
    throws Exception {
    log.info("callBackMessageExt:{}", messageExt.getTopic());
    MQProducer mqProducer = ProducerFactory.
      getProducerAndStart(DELAY_MSEC_GROUP, consumeContext.getNamesrvAddr());
    Message message = new Message(messageExt.getTopic(), messageExt.getTags(),
      messageExt.getBody());
    message.setDelayTimeLevel(DelayUtils.getMaxDelayLevelBySec(delayMsec));
    mqProducer.send(message);
  }
}
