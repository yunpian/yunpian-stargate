package com.yunpian.stargate.dogrobber.enums;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:5:09 PM
 */
public enum CommandHandleIdCardEnum {
  ALL_COMMAND("ALL_COMMAND_GSDH324JK324B45J231", "全部消费者监听"),
  ;

  private String idCard;
  private String remark;

  CommandHandleIdCardEnum(String idCard, String remark) {
    this.idCard = idCard;
    this.remark = remark;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
