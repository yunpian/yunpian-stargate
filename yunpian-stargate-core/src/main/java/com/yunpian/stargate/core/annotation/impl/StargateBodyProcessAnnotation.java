package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducerAndConsume;
import com.yunpian.stargate.core.dto.StargateBodyDTO;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:47 PM
 */
public class StargateBodyProcessAnnotation
  extends AbsProcessAnnotationProducerAndConsume<StargateBodyDTO> {


  @Override
  protected void processConsume(StargateBodyDTO stargateBodyDTO, Class aClass, Object o,
    Method method)
    throws Throwable {
    processStargate(stargateBodyDTO, aClass, method);
  }

  @Override
  protected void processProducer(StargateBodyDTO stargateBodyDTO, Class aClass, Object o,
    Method method) throws Throwable {
    processStargate(stargateBodyDTO, aClass, method);
  }

  private void processStargate(StargateBodyDTO stargateBodyDTO, Class aClass, Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    Class[] classes = method.getParameterTypes();
    int parameterSize = classes.length;
    Annotation[][] annotations = method.getParameterAnnotations();
    for (int i = 0; i < parameterSize; ++i) {
      for (Annotation annotations1 : annotations[i]) {
        if (annotations1 instanceof StargateBody) {
          stargateBodyDTO.setBodyIndex(i);
          stargateBodyDTO.setBodyClass(classes[i]);
          continue;
        }
      }
    }
  }
}
