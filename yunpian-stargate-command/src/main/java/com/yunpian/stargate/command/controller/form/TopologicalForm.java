package com.yunpian.stargate.command.controller.form;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/11 Time:4:04 PM
 */
public class TopologicalForm {

  private String appName;
  private String topic;
  private String group;
  private boolean fuzzy;
  private boolean showTopic;

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

  public boolean getShowTopic() {
    return showTopic;
  }

  public void setShowTopic(boolean showTopic) {
    this.showTopic = showTopic;
  }
}
