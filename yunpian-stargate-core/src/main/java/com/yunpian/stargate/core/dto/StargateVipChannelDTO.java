package com.yunpian.stargate.core.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:34 PM
 */
public class StargateVipChannelDTO {

  private Boolean vipChannel;

  public Boolean getVipChannel() {
    return vipChannel;
  }

  public void setVipChannel(Boolean vipChannel) {
    this.vipChannel = vipChannel;
  }

  @Override
  public String toString() {
    return "StargateVipChannelDTO{" +
      "vipChannel=" + vipChannel +
      '}';
  }

}
