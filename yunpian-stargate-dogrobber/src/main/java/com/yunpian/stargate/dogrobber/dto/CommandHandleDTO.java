package com.yunpian.stargate.dogrobber.dto;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:4:53 PM
 */
public class CommandHandleDTO {

  public static final String TOPIC = "STARGATE_COMMAND_COMMAND_HANDLE_HU7H99HN56HDZM73SA";
  private int code;
  private String idCard;
  private String key;
  private Object data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
