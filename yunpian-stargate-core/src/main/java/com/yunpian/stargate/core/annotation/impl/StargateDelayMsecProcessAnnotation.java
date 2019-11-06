package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateDelayMsec;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.dto.StargateDelayDTO;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:42 PM
 */
public class StargateDelayMsecProcessAnnotation
  extends AbsProcessAnnotationProducer<StargateDelayDTO> {

  @Override
  protected void processProducer(StargateDelayDTO stargateDelayDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateDelayMsec stargateDelayMsec = null;
    if (method == null) {
      stargateDelayMsec = (StargateDelayMsec) aClass.getAnnotation(StargateDelayMsec.class);
    } else {
      stargateDelayMsec = method.getAnnotation(StargateDelayMsec.class);
    }
    if (stargateDelayMsec == null || stargateDelayMsec.value() < 0) {
      return;
    }
    stargateDelayDTO.setDelayMsec(stargateDelayMsec.value());
  }

}
