package com.yunpian.extend.tagparam;

import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class TagParamAnnotationHandel extends AbsProcessAnnotationProducer<TagParamDTO> {

  @Override
  protected void processProducer(TagParamDTO tagParamDTO, Class aClass, Object o, Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    Class[] classes = method.getParameterTypes();
    int parameterSize = classes.length;
    Annotation[][] annotations = method.getParameterAnnotations();
    method:
    for (int i = 0; i < parameterSize; ++i) {
      for (Annotation annotations1 : annotations[i]) {
        if (annotations1 instanceof StargateTagParam) {
          tagParamDTO.setIndex(i);
          break method;
        }
      }
    }
  }
}
