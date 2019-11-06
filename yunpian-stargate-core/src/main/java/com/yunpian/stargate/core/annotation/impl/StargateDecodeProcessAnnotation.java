package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateDecode;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationConsume;
import com.yunpian.stargate.core.dto.StargateDecodeDTO;
import com.yunpian.stargate.core.process.IStargateClientDecode;
import com.yunpian.stargate.core.utils.EncodAndDncodeFactory;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:31 PM
 */
public class StargateDecodeProcessAnnotation
  extends AbsProcessAnnotationConsume<StargateDecodeDTO> {

  @Override
  protected void processConsume(StargateDecodeDTO stargateDecodeDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateDecode stargateDecode = null;
    if (method == null) {
      stargateDecode = (StargateDecode) aClass.getAnnotation(StargateDecode.class);
    } else {
      stargateDecode = method.getAnnotation(StargateDecode.class);
    }
    if (stargateDecode == null || stargateDecode.value().length <= 0) {
      return;
    }
    Object decode = EncodAndDncodeFactory.getEncodOrDncod(stargateDecode.value()[0]);
    stargateDecodeDTO.setRocketClientDecode((IStargateClientDecode) decode);
  }
}
