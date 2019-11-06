package com.yunpian.stargate.core.context;

import com.yunpian.stargate.core.consume.ConsumeHandler;
import com.yunpian.stargate.core.consume.MessageListener;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.dto.StargateThreadSizeDTO;
import com.yunpian.stargate.core.exception.StargateException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:10:28 AM
 */
public class ConsumeContext extends StargateContext {

  private DefaultMQPushConsumer mqConsumer;
  private ConsumeHandler consumeHandler;
  private MessageListener messageListener;

  public DefaultMQPushConsumer getMqConsumer() {
    return mqConsumer;
  }

  public void setMqConsumer(DefaultMQPushConsumer mqConsumer) {
    this.mqConsumer = mqConsumer;
  }

  public ConsumeHandler getConsumeHandler() {
    return consumeHandler;
  }

  public void setConsumeHandler(ConsumeHandler consumeHandler) {
    this.consumeHandler = consumeHandler;
  }

  public MessageListener getMessageListener() {
    return messageListener;
  }

  public void setMessageListener(MessageListener messageListener) {
    this.messageListener = messageListener;
  }

  @Override
  public ConsumeContext clone() throws CloneNotSupportedException {
    ConsumeContext consumeContext = new ConsumeContext();
    try {
      for (Class aClass : data.keySet()) {
        Object o = data.get(aClass);
        consumeContext.data.put(aClass, BeanUtils.cloneBean(o));
      }
    } catch (Exception e) {
      throw new StargateException("copy ConsumeContext error:", e);
    }
    return consumeContext;
  }

  @Override
  public boolean isValid() {
    ConsumeMethodsDTO producerMethodsDTO = (ConsumeMethodsDTO) data
      .get(ConsumeMethodsDTO.class);
    return producerMethodsDTO.isValid();
  }

  public int getThreadSize() {
    StargateThreadSizeDTO threadSizeDTO = (StargateThreadSizeDTO) data
      .get(StargateThreadSizeDTO.class);
    return threadSizeDTO.getThreadSize();
  }
}
