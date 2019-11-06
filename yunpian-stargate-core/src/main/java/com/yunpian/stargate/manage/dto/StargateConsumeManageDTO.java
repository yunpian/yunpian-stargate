package com.yunpian.stargate.manage.dto;

import com.yunpian.stargate.core.context.ConsumeContext;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/30 Time:4:56 PM
 */
public class StargateConsumeManageDTO {

  private String key;
  private ConsumeContext consumeContext;

  public StargateConsumeManageDTO(ConsumeContext consumeContext) {
    this.consumeContext = consumeContext;
  }

  public ConsumeContext getConsumeContext() {
    return consumeContext;
  }

  public void setConsumeContext(ConsumeContext consumeContext) {
    this.consumeContext = consumeContext;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}

