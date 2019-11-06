package com.yunpian.stargate.manage.dto;

import com.yunpian.stargate.core.context.ProducerContext;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/30 Time:4:56 PM
 */
public class StargateProducerManageDTO {

  private String key;
  private ProducerContext producerContext;

  public StargateProducerManageDTO(ProducerContext producerContext) {
    this.producerContext = producerContext;
  }

  public ProducerContext getProducerContext() {
    return producerContext;
  }

  public void setProducerContext(ProducerContext producerContext) {
    this.producerContext = producerContext;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
