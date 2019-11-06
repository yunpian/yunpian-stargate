package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateBroadcasting;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationConsume;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:27 PM
 */
public class StargateBroadcastingProcessAnnotation
  extends AbsProcessAnnotationConsume<StargateConsumeBaseDTO> {

  @Override
  protected void processConsume(StargateConsumeBaseDTO stargateConsumeBaseDTO, Class aClass,
    Object o, Method method) throws Throwable {
    StargateBroadcasting stargateBroadcasting = null;
    if (method == null) {
      stargateBroadcasting = (StargateBroadcasting) aClass
        .getAnnotation(StargateBroadcasting.class);
    } else {
      stargateBroadcasting = method.getAnnotation(StargateBroadcasting.class);
    }
    if (stargateBroadcasting == null) {
      return;
    }
    stargateConsumeBaseDTO.setBroadcasting(stargateBroadcasting.value());
  }
}
