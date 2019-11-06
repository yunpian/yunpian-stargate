package com.yunpian.stargate.test.plusmodel;

import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class TestAnnotation extends AbsProcessAnnotationProducer<TestModelDTO> {

  @Override
  protected void processProducer(TestModelDTO testModelDTO, Class aClass, Object o, Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    TestModel testModel = method.getAnnotation(TestModel.class);
    if (testModel == null) {
      return;
    }
    testModelDTO.setV(testModel.value());
  }
}
