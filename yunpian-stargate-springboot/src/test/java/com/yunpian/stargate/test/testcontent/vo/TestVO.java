package com.yunpian.stargate.test.testcontent.vo;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/15 Time:3:27 PM
 */
public class TestVO {
  private String string;
  private String name;

  public TestVO() {
  }

  public TestVO(String string, String name) {
    this.string = string;
    this.name = name;
  }

  @Override
  public String toString() {
    return "TestVO{" +
      "string='" + string + '\'' +
      ", name='" + name + '\'' +
      '}';
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestVO testVO = (TestVO) o;
    return Objects.equals(string, testVO.string) &&
      Objects.equals(name, testVO.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(string, name);
  }
}
