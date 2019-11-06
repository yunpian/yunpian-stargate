package com.yunpian.stargate.core.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:05 PM
 */
public class StargateDelayDTO {

  private long delayMsec;
  private int delayParamIndex = -1;

  public long getDelayMsec() {
    return delayMsec;
  }

  public void setDelayMsec(long delayMsec) {
    this.delayMsec = delayMsec;
  }

  public int getDelayParamIndex() {
    return delayParamIndex;
  }

  public void setDelayParamIndex(int delayParamIndex) {
    this.delayParamIndex = delayParamIndex;
  }

  @Override
  public String toString() {
    return "StargateDelayDTO{" +
      "delayMsec=" + delayMsec +
      ", delayParamIndex=" + delayParamIndex +
      '}';
  }
}
