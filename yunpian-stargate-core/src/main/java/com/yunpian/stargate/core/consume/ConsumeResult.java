package com.yunpian.stargate.core.consume;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/14 Time:3:50 PM
 */
public class ConsumeResult {

  public static final ConsumeResult SUCCESS =
    new ConsumeResult(ConsumeConcurrentlyStatus.CONSUME_SUCCESS);
  public static final ConsumeResult RECONSUME_LATER =
    new ConsumeResult(ConsumeConcurrentlyStatus.RECONSUME_LATER);

  private ConsumeConcurrentlyStatus consumeConcurrentlyStatus;

  public ConsumeResult() {
    this(ConsumeConcurrentlyStatus.CONSUME_SUCCESS);
  }

  public ConsumeResult(ConsumeConcurrentlyStatus consumeConcurrentlyStatus) {
    this.consumeConcurrentlyStatus = consumeConcurrentlyStatus;
  }

  public ConsumeConcurrentlyStatus getConsumeConcurrentlyStatus() {
    return consumeConcurrentlyStatus;
  }

  public void setConsumeConcurrentlyStatus(
    ConsumeConcurrentlyStatus consumeConcurrentlyStatus) {
    this.consumeConcurrentlyStatus = consumeConcurrentlyStatus;
  }
}
