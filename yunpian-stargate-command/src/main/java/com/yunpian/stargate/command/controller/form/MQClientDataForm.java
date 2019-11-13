package com.yunpian.stargate.command.controller.form;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/7 Time:4:38 PM
 */
public class MQClientDataForm {

  private List<String> appName;
  private List<String> topic;
  private List<String> group;
  private String type;

  public List<String> getAppName() {
    return appName;
  }

  public void setAppName(List<String> appName) {
    this.appName = appName;
  }

  public List<String> getTopic() {
    return topic;
  }

  public void setTopic(List<String> topic) {
    this.topic = topic;
  }

  public List<String> getGroup() {
    return group;
  }

  public void setGroup(List<String> group) {
    this.group = group;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
