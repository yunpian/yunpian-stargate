package com.yunpian.stargate.core.dto;

import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:01 PM
 */
public class StargateConsumeBaseDTO {

  private boolean broadcasting;
  private int pullBatchSize;
  private ConsumeFromWhere consumeFromWhere;

  public int getPullBatchSize() {
    return pullBatchSize;
  }

  public void setPullBatchSize(int pullBatchSize) {
    this.pullBatchSize = pullBatchSize;
  }

  public ConsumeFromWhere getConsumeFromWhere() {
    return consumeFromWhere;
  }

  public void setConsumeFromWhere(ConsumeFromWhere consumeFromWhere) {
    this.consumeFromWhere = consumeFromWhere;
  }

  public boolean isBroadcasting() {
    return broadcasting;
  }

  public void setBroadcasting(boolean broadcasting) {
    this.broadcasting = broadcasting;
  }

  @Override
  public String toString() {
    return "StargateConsumeBaseDTO{" +
      "broadcasting=" + broadcasting +
      ", pullBatchSize=" + pullBatchSize +
      ", consumeFromWhere=" + consumeFromWhere +
      '}';
  }
}
