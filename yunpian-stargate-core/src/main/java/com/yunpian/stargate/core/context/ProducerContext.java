package com.yunpian.stargate.core.context;

import com.yunpian.stargate.core.dto.ProducerMethodsDTO;
import com.yunpian.stargate.core.exception.StargateException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:10:28 AM
 */
public class ProducerContext extends StargateContext {

  protected DefaultMQProducer mqProducer;

  @Override
  public ProducerContext clone() {
    ProducerContext producerContext = new ProducerContext();
    try {
      for (Class aClass : data.keySet()) {
        Object o = data.get(aClass);
        producerContext.data.put(aClass, BeanUtils.cloneBean(o));
      }
    } catch (Exception e) {
      throw new StargateException("copy ConsumeContext error:", e);
    }
    return producerContext;
  }

  @Override
  public boolean isValid() {
    ProducerMethodsDTO producerMethodsDTO = (ProducerMethodsDTO) data
      .get(ProducerMethodsDTO.class);
    return producerMethodsDTO.isValid();
  }

  public DefaultMQProducer getMqProducer() {
    return mqProducer;
  }

  public void setMqProducer(DefaultMQProducer mqProducer) {
    this.mqProducer = mqProducer;
  }

}
