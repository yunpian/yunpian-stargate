package com.yunpian.stargate.core.message.impl;

import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.dto.StargateBodyDTO;
import com.yunpian.stargate.core.dto.StargateDecodeDTO;
import com.yunpian.stargate.core.message.IProcessMessageConsume;
import com.yunpian.stargate.core.vo.StargateMessage;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class CoreProcessMessageConsume implements IProcessMessageConsume {

  private static final Logger log = LoggerFactory.getLogger(CoreProcessMessageConsume.class);

  @Override
  public int processConsume(ConsumeContext consumeContext, MessageExt messageExt,
    ConsumeConcurrentlyContext consumeConcurrentlyContext, Object[] args)
    throws Throwable {

    StargateDecodeDTO stargateDecodeDTO = consumeContext.getDTO(StargateDecodeDTO.class);
    StargateBodyDTO stargateBodyDTO = consumeContext.getDTO(StargateBodyDTO.class);
    ConsumeMethodsDTO consumeMethodsDTO = consumeContext.getDTO(ConsumeMethodsDTO.class);

    StargateMessage body = stargateDecodeDTO.getRocketClientDecode()
      .decode(messageExt.getBody(), consumeContext);

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
}
