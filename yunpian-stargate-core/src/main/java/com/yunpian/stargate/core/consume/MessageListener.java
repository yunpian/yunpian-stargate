package com.yunpian.stargate.core.consume;

import java.util.List;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/27 Time:上午9:57
 */
public class MessageListener implements MessageListenerConcurrently {

  private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

  private ConsumeHandler consumeHandler;

  @Override
  public ConsumeConcurrentlyStatus consumeMessage(
    //这个参数已经保持size为1
    List<MessageExt> messageExts,
    ConsumeConcurrentlyContext consumeConcurrentlyContext) {
    ConsumeResult consumeResult = null;
    //保险一点遍历一下,避免漏消息
    for (MessageExt messageExt : messageExts) {
      try {
        consumeResult = consumeHandler.handler(
          messageExt,
          consumeConcurrentlyContext);
      } catch (Throwable e) {
        log.error("Throwable:", e);
      }
    }
    if (consumeResult == null || consumeResult.getConsumeConcurrentlyStatus() == null) {
      return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
    return consumeResult.getConsumeConcurrentlyStatus();
  }

  public ConsumeHandler getConsumeHandler() {
    return consumeHandler;
  }

  public void setConsumeHandler(ConsumeHandler consumeHandler) {
    this.consumeHandler = consumeHandler;
  }
}
