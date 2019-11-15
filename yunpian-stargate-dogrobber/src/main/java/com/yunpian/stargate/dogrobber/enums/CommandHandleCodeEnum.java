package com.yunpian.stargate.dogrobber.enums;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:5:09 PM
 */
public enum CommandHandleCodeEnum {
  START_CONSUMER(101, "启动消费者"),
  STOP_CONSUMER(102, "关闭消费者"),
  SUSPEND_CONSUMER(103, "暂停消费"),
  RESUME_CONSUMER(104, "恢复消费"),
  REFRESH_DATA(105, "刷新数据"),
  SET_THREAD_SIZE(106, "设置线程池大小"),
  ;

  private int code;
  private String remark;

  CommandHandleCodeEnum(int code, String remark) {
    this.code = code;
    this.remark = remark;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
