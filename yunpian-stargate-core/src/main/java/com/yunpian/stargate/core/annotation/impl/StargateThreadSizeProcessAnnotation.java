package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateThreadSize;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationConsume;
import com.yunpian.stargate.core.dto.StargateThreadSizeDTO;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:42 PM
 */
public class StargateThreadSizeProcessAnnotation
  extends AbsProcessAnnotationConsume<StargateThreadSizeDTO> {

  @Override
  protected void processConsume(StargateThreadSizeDTO stargateThreadSizeDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateThreadSize stargateThreadSize = null;
    if (method == null) {
      stargateThreadSize = (StargateThreadSize) aClass.getAnnotation(StargateThreadSize.class);
    } else {
      stargateThreadSize = method.getAnnotation(StargateThreadSize.class);
    }
    if (stargateThreadSize == null || stargateThreadSize.value() <= 0) {
      return;
    }
    stargateThreadSizeDTO.setThreadSize(stargateThreadSize.value());
  }
}
