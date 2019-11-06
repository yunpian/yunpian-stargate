package com.yunpian.stargate.manage;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/12 Time:1:28 PM
 */
public class StargateInstance {

  private StargateInstance() {
  }

  private static StargateInstance stargateInstance = new StargateInstance();
  private IStargateConsumeManage stargateConsumeManage;
  private IStargateProcessManage stargateProcessManage;
  private String idCard;

  public static StargateInstance getStargateInstance() {
    return stargateInstance;
  }

  public void init(IStargateProcessManage stargateProcessManage,
    IStargateConsumeManage stargateConsumeManage) {
    this.stargateProcessManage = stargateProcessManage;
    this.stargateConsumeManage = stargateConsumeManage;
    this.idCard = UUID.randomUUID().toString();
  }

  public boolean isInit() {
    if (this.stargateProcessManage != null || this.stargateConsumeManage != null) {
      return true;
    }
    return false;
  }

  public IStargateConsumeManage getStargateConsumeManage() {
    return stargateConsumeManage;
  }

  public IStargateProcessManage getStargateProcessManage() {
    return stargateProcessManage;
  }

  public String getIdCard() {
    return idCard;
  }
}
