package com.yunpian.stargate.core.dto;

import com.yunpian.stargate.core.process.IStargateClientEncod;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:08 PM
 */
public class StargateEncodDTO {

  private IStargateClientEncod rocketClientEncod;

  public IStargateClientEncod getRocketClientEncod() {
    return rocketClientEncod;
  }

  public void setRocketClientEncod(IStargateClientEncod rocketClientEncod) {
    this.rocketClientEncod = rocketClientEncod;
  }

  @Override
  public String toString() {
    return "StargateEncodDTO{" +
      "rocketClientEncod=" + rocketClientEncod +
      '}';
  }
}
