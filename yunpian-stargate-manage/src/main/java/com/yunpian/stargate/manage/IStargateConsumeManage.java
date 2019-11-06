package com.yunpian.stargate.manage;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/12 Time:1:28 PM
 * @since 1.4
 */
public interface IStargateConsumeManage {
  boolean startAll();
  void closeAll();
  boolean isAllClose();
}
