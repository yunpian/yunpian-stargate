package com.yunpian.extend.bus;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.core.utils.StringUtils;
import org.apache.rocketmq.common.message.Message;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class StargateMsgBusMessageHandel implements IProcessMessageProducer {

  @Override
  public int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable {
    StargateMsgBusDTO stargateMsgBusDTO = producerContext.getDTOOrNew(StargateMsgBusDTO.class);
    int stargateMsgBusPipIndex = stargateMsgBusDTO.getStargateMsgBusPipIndex();
    if (stargateMsgBusPipIndex > 0) {
      Object msgBusPip = args[stargateMsgBusPipIndex];
      String tag = null;
      if (msgBusPip != null) {
        tag = String.valueOf(msgBusPip);
      }
      if (!StringUtils.isBlank(tag)) {
        message.setTags(tag);
      }
    }
    return SUCCESS;
  }
}
