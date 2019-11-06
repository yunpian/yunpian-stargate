package com.yunpian.extend.demo;

import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class DemoAnnotation extends AbsProcessAnnotationProducer<DemoModelDTO> {

  @Override
  protected void processProducer(DemoModelDTO demoModelDTO, Class aClass, Object o, Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    DemoModel demoModel = method.getAnnotation(DemoModel.class);
    if (demoModel == null) {
      return;
    }
    //把注解解析的数据放到上下文中
    demoModelDTO.setV(demoModel.value());
  }
}
