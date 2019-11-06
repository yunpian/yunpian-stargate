package com.yunpian.stargate.core.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:32 PM
 */
public class StargateNameServerDTO {

  private String namesrvAddr;

  public String getNamesrvAddr() {
    return namesrvAddr;
  }

  public void setNamesrvAddr(String namesrvAddr) {
    this.namesrvAddr = namesrvAddr;
  }

  @Override
  public String toString() {
    return "StargateNameServerDTO{" +
      "namesrvAddr='" + namesrvAddr + '\'' +
      '}';
  }
}
