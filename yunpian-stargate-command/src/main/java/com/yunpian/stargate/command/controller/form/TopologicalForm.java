package com.yunpian.stargate.command.controller.form;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/11 Time:4:04 PM
 */
public class TopologicalForm {

  private List<String> appName;
  private List<String> topic;
  private List<String> group;

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
}
