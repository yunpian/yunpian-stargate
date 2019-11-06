package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateDelayParam;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.dto.StargateDelayDTO;
import com.yunpian.stargate.core.exception.StargateException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:42 PM
 */
public class StargateDelayParamProcessAnnotation
  extends AbsProcessAnnotationProducer<StargateDelayDTO> {

  @Override
  protected void processProducer(StargateDelayDTO stargateDelayDTO, Class aClass, Object o,
    Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    Class[] classes = method.getParameterTypes();
    int parameterSize = classes.length;
    Annotation[][] annotations = method.getParameterAnnotations();
    for (int i = 0; i < parameterSize; ++i) {
      for (Annotation annotations1 : annotations[i]) {
        if (annotations1 instanceof StargateDelayParam) {
          if (!classes[i].equals(long.class)) {
            throw new StargateException("StargateDelayParam type not long");
          }
          stargateDelayDTO.setDelayParamIndex(i);
          break;
        }
      }
    }
  }

}
