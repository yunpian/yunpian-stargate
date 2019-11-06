package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.dto.ProducerMethodsDTO;
import java.lang.reflect.Method;
import org.apache.rocketmq.client.producer.SendCallback;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:5:06 PM
 */
public class MethodsProcessProducerAnnotation
  extends AbsProcessAnnotationProducer<ProducerMethodsDTO> {

  @Override
  public void processProducer(ProducerMethodsDTO producerMethodsDTO, Class aClass, Object o,
    Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    producerMethodsDTO.setValid(isValid(method));
    producerMethodsDTO.setObject(o);
    producerMethodsDTO.setClazz(aClass);
    producerMethodsDTO.setMethod(method);
    Class[] classes = method.getParameterTypes();
    int parameterSize = classes.length;
    producerMethodsDTO.setParameterSize(parameterSize);
    for (int i = 0; i < parameterSize; ++i) {
      if (SendCallback.class.isAssignableFrom(classes[i])) {
        producerMethodsDTO.setSendCallbackIndex(i);
        continue;
      }
    }

  }

  private boolean isValid(Method method) {
    StargateMapper stargateMapper = null;
    stargateMapper = method.getAnnotation(StargateMapper.class);
    if (stargateMapper == null) {
      return false;
    }
    return true;
  }

}
