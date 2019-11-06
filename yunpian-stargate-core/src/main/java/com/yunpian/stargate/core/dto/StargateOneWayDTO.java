package com.yunpian.stargate.core.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:34 PM
 */
public class StargateOneWayDTO {

  private boolean oneWay;

  public boolean isOneWay() {
    return oneWay;
  }

  public void setOneWay(boolean oneWay) {
    this.oneWay = oneWay;
  }

  @Override
  public String toString() {
    return "StargateOneWayDTO{" +
      "oneWay=" + oneWay +
      '}';
  }
}
