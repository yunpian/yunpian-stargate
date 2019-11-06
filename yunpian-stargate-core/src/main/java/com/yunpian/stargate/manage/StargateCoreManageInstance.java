package com.yunpian.stargate.manage;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/12 Time:1:28 PM
 */
public class StargateCoreManageInstance {

  public static IStargateConsumeManageCore getStargateConsumeCoreManage() {
    return (IStargateConsumeManageCore) StargateInstance.getStargateInstance()
      .getStargateConsumeManage();
  }

  public static IStargateProducerManageCore getStargateProcessCoreManage() {
    return (IStargateProducerManageCore) StargateInstance.getStargateInstance()
      .getStargateProcessManage();
  }

  public static String getIdCard() {
    return StargateInstance.getStargateInstance().getIdCard();
  }
}
