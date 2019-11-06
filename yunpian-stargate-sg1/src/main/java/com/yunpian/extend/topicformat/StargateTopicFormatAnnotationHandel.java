package com.yunpian.extend.topicformat;

import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.utils.StringUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class StargateTopicFormatAnnotationHandel extends
  AbsProcessAnnotationProducer<StargateTopicFormatDTO> {

  @Override
  protected void processProducer(StargateTopicFormatDTO stargateTopicFormatDTO, Class aClass,
    Object o, Method method) throws Throwable {
    if (method == null) {
      return;
    }
    Parameter[] parameters = method.getParameters();
    int parameterSize = parameters.length;
    Annotation[][] annotations = method.getParameterAnnotations();
    for (int i = 0; i < parameterSize; ++i) {
      for (Annotation annotations1 : annotations[i]) {
        if (annotations1 instanceof StargateTopicFormat) {
          StargateTopicFormat stargateTopicFormat = (StargateTopicFormat) annotations1;
          String name = stargateTopicFormat.value();
          if (StringUtils.isEmpty(name)) {
            throw new StargateException("Annotation @StargateTopicFormat value is Empty");
          }
          stargateTopicFormatDTO.put(name, i);
        }
      }
    }
  }
}
