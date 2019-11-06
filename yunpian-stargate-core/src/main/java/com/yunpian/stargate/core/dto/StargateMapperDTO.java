package com.yunpian.stargate.core.dto;

import com.yunpian.stargate.core.utils.StringUtils;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:3:13 PM
 */
public class StargateMapperDTO {

  private String group = "";
  private String topic = "";
  private String tag = "";

  public void addGroup(String group) {
    if (StringUtils.isEmpty(this.group)) {
      this.group = group;
    } else {
      this.group = this.group + "-" + group;
    }
  }

  public void addTopic(String topic) {
    if (StringUtils.isEmpty(this.topic)) {
      this.topic = topic;
    } else {
      this.topic = this.topic + "-" + topic;
    }
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "StargateMapperDTO{" +
      "group='" + group + '\'' +
      ", topic='" + topic + '\'' +
      ", tag='" + tag + '\'' +
      '}';
  }
}
