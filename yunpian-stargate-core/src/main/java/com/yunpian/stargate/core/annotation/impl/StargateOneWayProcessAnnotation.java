package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateOneWay;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.dto.StargateOneWayDTO;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:42 PM
 */
public class StargateOneWayProcessAnnotation
  extends AbsProcessAnnotationProducer<StargateOneWayDTO> {

  @Override
  protected void processProducer(StargateOneWayDTO stargateOneWayDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateOneWay stargateOneWay = null;
    if (method == null) {
      stargateOneWay = (StargateOneWay) aClass.getAnnotation(StargateOneWay.class);
    } else {
      stargateOneWay = method.getAnnotation(StargateOneWay.class);
    }
    if (stargateOneWay == null) {
      return;
    }
    stargateOneWayDTO.setOneWay(true);
  }
}
