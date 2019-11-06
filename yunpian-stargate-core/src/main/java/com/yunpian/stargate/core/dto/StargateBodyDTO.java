package com.yunpian.stargate.core.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:2:59 PM
 */
public class StargateBodyDTO {

  private int bodyIndex = -1;
  private Class bodyClass;

  public int getBodyIndex() {
    return bodyIndex;
  }

  public void setBodyIndex(int bodyIndex) {
    this.bodyIndex = bodyIndex;
  }

  public Class getBodyClass() {
    return bodyClass;
  }

  public void setBodyClass(Class bodyClass) {
    this.bodyClass = bodyClass;
  }

  @Override
  public String toString() {
    return "StargateBodyDTO{" +
      "bodyIndex=" + bodyIndex +
      ", bodyClass=" + bodyClass +
      '}';
  }
}
