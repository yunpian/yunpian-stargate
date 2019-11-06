package com.yunpian.stargate.core.dto;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:2:59 PM
 */
public class StargateBaseDTO {

  private String id;
  private String[] tags;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "StargateBaseDTO{" +
      "id='" + id + '\'' +
      ", tags=" + Arrays.toString(tags) +
      '}';
  }
}
