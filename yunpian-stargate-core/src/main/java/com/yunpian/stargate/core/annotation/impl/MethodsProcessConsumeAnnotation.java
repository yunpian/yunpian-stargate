package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationConsume;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import java.lang.reflect.Method;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:5:06 PM
 */
public class MethodsProcessConsumeAnnotation
  extends AbsProcessAnnotationConsume<ConsumeMethodsDTO> {

  @Override
  protected void processConsume(ConsumeMethodsDTO consumeMethodsDTO, Class aClass, Object o,
    Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    consumeMethodsDTO.setValid(isValid(method));
    consumeMethodsDTO.setObject(o);
    consumeMethodsDTO.setClazz(aClass);
    consumeMethodsDTO.setMethod(method);
    Class[] classes = method.getParameterTypes();
    int parameterSize = classes.length;
    consumeMethodsDTO.setParameterSize(parameterSize);
    for (int i = 0; i < parameterSize; ++i) {
      if (MessageExt.class.isAssignableFrom(classes[i])) {
        consumeMethodsDTO.setMessageExtIndex(i);
        continue;
      }
      if (ConsumeConcurrentlyContext.class.isAssignableFrom(classes[i])) {
        consumeMethodsDTO.setContextIndex(i);
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
