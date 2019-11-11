package com.yunpian.stargate.command.controller.form;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/7 Time:4:38 PM
 */
public class MQClientDataForm {

  private String appName;
  private String topic;
  private String group;
  private String type;
  private boolean fuzzy;

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public boolean getFuzzy() {
    return fuzzy;
  }

  public void setFuzzy(boolean fuzzy) {
    this.fuzzy = fuzzy;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
