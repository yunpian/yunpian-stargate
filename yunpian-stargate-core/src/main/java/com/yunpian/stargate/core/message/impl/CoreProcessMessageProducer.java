package com.yunpian.stargate.core.message.impl;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.StargateBodyDTO;
import com.yunpian.stargate.core.dto.StargateDelayDTO;
import com.yunpian.stargate.core.dto.StargateEncodDTO;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.core.process.IStargateClientEncod;
import com.yunpian.stargate.core.utils.DelayUtils;
import com.yunpian.stargate.core.vo.StargateMessage;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class CoreProcessMessageProducer implements IProcessMessageProducer {

  private static final Logger log = LoggerFactory.getLogger(CoreProcessMessageProducer.class);

  private static final String DELAY_KEY = "dtime";

  @Override
  public int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable {
    StargateBodyDTO stargateBodyDTO = producerContext.getDTO(StargateBodyDTO.class);
    Object dataBody = null;
    if (stargateBodyDTO.getBodyIndex() >= 0) {
      dataBody = args[stargateBodyDTO.getBodyIndex()];
    }

    StargateDelayDTO stargateDelayDTO = producerContext.getDTO(StargateDelayDTO.class);
    if (stargateDelayDTO.getDelayParamIndex() >= 0) {
      stargateDelayDTO.setDelayMsec((long) args[stargateDelayDTO.getDelayParamIndex()]);
    }

    long delayMsec = stargateDelayDTO.getDelayMsec();
    int level = DelayUtils.getMaxDelayLevelBySec(delayMsec);
    StargateMessage stargateMessage = new StargateMessage(dataBody,
      delayMsec + System.currentTimeMillis());
    message.putUserProperty(DELAY_KEY, String.valueOf(delayMsec));

    IStargateClientEncod rocketClientEncod = producerContext.getDTO(StargateEncodDTO.class)
      .getRocketClientEncod();
    byte[] data = rocketClientEncod.encod(stargateMessage, producerContext);
    String topic = producerContext.getTopic();
    String tag = producerContext.getTag();
    message.setTopic(topic);
    message.setTags(tag);
    message.setBody(data);
    if (level > 0) {
      message.setDelayTimeLevel(level);
    }

    return SUCCESS;
  }
}
