package com.yunpian.stargate.command.controller.vo;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/11 Time:4:04 PM
 */
public class TopologicalNodeVO {

  private String name;
  private int value;
  private int symbolSize;
  private int category;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getSymbolSize() {
    return symbolSize;
  }

  public void setSymbolSize(int symbolSize) {
    this.symbolSize = symbolSize;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }
}
