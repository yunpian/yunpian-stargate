package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateDelayLevel;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducer;
import com.yunpian.stargate.core.dto.StargateDelayDTO;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.utils.DelayUtils;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:42 PM
 */
public class StargateDelayLevelProcessAnnotation
  extends AbsProcessAnnotationProducer<StargateDelayDTO> {

  @Override
  protected void processProducer(StargateDelayDTO stargateDelayDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateDelayLevel stargateDelayLevel = null;
    if (method == null) {
      stargateDelayLevel = (StargateDelayLevel) aClass.getAnnotation(StargateDelayLevel.class);
    } else {
      stargateDelayLevel = method.getAnnotation(StargateDelayLevel.class);
    }
    if (stargateDelayLevel == null || stargateDelayLevel.value() < 0) {
      return;
    }
    if (stargateDelayLevel.value() >= DelayUtils.delayLevel.length) {
      throw new StargateException("delayLevel greater than " + (DelayUtils.delayLevel.length - 1));
    }
    stargateDelayDTO.setLevel(stargateDelayLevel.value());
  }

}
