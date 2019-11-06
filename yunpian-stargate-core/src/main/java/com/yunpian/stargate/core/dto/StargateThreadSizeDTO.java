package com.yunpian.stargate.core.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:36 PM
 */
public class StargateThreadSizeDTO {

  private int threadSize;

  public int getThreadSize() {
    return threadSize;
  }

  public void setThreadSize(int threadSize) {
    this.threadSize = threadSize;
  }

  @Override
  public String toString() {
    return "StargateThreadSizeDTO{" +
      "threadSize=" + threadSize +
      '}';
  }
}
