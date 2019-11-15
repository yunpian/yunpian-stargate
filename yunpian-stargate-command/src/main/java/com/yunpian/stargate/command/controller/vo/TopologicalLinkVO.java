package com.yunpian.stargate.command.controller.vo;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/11 Time:4:04 PM
 */
public class TopologicalLinkVO {

  private String source;
  private String target;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TopologicalLinkVO that = (TopologicalLinkVO) o;
    return Objects.equals(source, that.source) &&
      Objects.equals(target, that.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, target);
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

}
