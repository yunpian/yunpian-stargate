package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateEncod;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.dto.StargateEncodDTO;
import com.yunpian.stargate.core.process.IStargateClientEncod;
import com.yunpian.stargate.core.utils.EncodAndDncodeFactory;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:38 PM
 */
public class StargateEncodProcessAnnotation
  extends AbsProcessAnnotationProducer<StargateEncodDTO> {

  @Override
  protected void processProducer(StargateEncodDTO stargateEncodDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateEncod stargateEncod = null;
    if (method == null) {
      stargateEncod = (StargateEncod) aClass.getAnnotation(StargateEncod.class);
    } else {
      stargateEncod = method.getAnnotation(StargateEncod.class);
    }
    if (stargateEncod == null || stargateEncod.value().length <= 0) {
      return;
    }
    Object encod = EncodAndDncodeFactory.getEncodOrDncod(stargateEncod.value()[0]);
    stargateEncodDTO.setRocketClientEncod((IStargateClientEncod) encod);
  }
}
