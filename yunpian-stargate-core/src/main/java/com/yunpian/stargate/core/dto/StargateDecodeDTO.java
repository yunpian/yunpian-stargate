package com.yunpian.stargate.core.dto;

import com.yunpian.stargate.core.process.IStargateClientDecode;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:03 PM
 */
public class StargateDecodeDTO {

  private IStargateClientDecode rocketClientDecode;

  public IStargateClientDecode getRocketClientDecode() {
    return rocketClientDecode;
  }

  public void setRocketClientDecode(
    IStargateClientDecode rocketClientDecode) {
    this.rocketClientDecode = rocketClientDecode;
  }

  @Override
  public String toString() {
    return "StargateDecodeDTO{" +
      "rocketClientDecode=" + rocketClientDecode +
      '}';
  }
}
