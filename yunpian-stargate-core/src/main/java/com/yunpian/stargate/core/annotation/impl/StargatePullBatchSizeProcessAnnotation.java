package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargatePullBatchSize;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationConsume;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import com.yunpian.stargate.core.exception.StargateException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:42 PM
 */
public class StargatePullBatchSizeProcessAnnotation
  extends AbsProcessAnnotationConsume<StargateConsumeBaseDTO> {

  @Override
  protected void processConsume(StargateConsumeBaseDTO stargateConsumeBaseDTO, Class aClass,
    Object o,
    Method method) throws Throwable {
    StargatePullBatchSize stargatePullBatchSize = null;
    if (method == null) {
      stargatePullBatchSize = (StargatePullBatchSize) aClass
        .getAnnotation(StargatePullBatchSize.class);
    } else {
      stargatePullBatchSize = method.getAnnotation(StargatePullBatchSize.class);
    }
    if (stargatePullBatchSize == null) {
      return;
    }
    if (stargatePullBatchSize.value() < 1 || stargatePullBatchSize.value() > 1024) {
      throw new StargateException("StargatePullBatchSize 1<value<1024");
    }
    stargateConsumeBaseDTO.setPullBatchSize(stargatePullBatchSize.value());
  }
}
