package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateBase;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducerAndConsume;
import com.yunpian.stargate.core.dto.StargateBaseDTO;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:47 PM
 */
public class StargateBaseProcessAnnotation
  extends AbsProcessAnnotationProducerAndConsume<StargateBaseDTO> {


  @Override
  protected void processConsume(StargateBaseDTO stargateBaseDTO, Class aClass, Object o,
    Method method)
    throws Throwable {
    processStargate(stargateBaseDTO, aClass, method);
  }

  @Override
  protected void processProducer(StargateBaseDTO stargateBaseDTO, Class aClass, Object o,
    Method method) throws Throwable {
    processStargate(stargateBaseDTO, aClass, method);
  }

  private void processStargate(StargateBaseDTO stargateBaseDTO, Class aClass, Method method)
    throws Throwable {
    if (method == null) {
      return;
    }
    stargateBaseDTO.setId(UUID.randomUUID().toString());
    StargateBase stargateBase = method.getAnnotation(StargateBase.class);
    if (stargateBase == null) {
      return;
    }
    stargateBaseDTO.setTags(stargateBase.tags());
  }
}
