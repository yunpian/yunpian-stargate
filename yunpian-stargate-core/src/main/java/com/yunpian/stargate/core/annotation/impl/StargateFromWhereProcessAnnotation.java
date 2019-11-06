package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateFromWhere;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationConsume;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import com.yunpian.stargate.core.utils.StringUtils;
import java.lang.reflect.Method;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:27 PM
 */
public class StargateFromWhereProcessAnnotation
  extends AbsProcessAnnotationConsume<StargateConsumeBaseDTO> {

  @Override
  protected void processConsume(StargateConsumeBaseDTO stargateConsumeBaseDTO, Class aClass,
    Object o,
    Method method) throws Throwable {
    StargateFromWhere stargateFromWhere = null;
    if (method == null) {
      stargateFromWhere = (StargateFromWhere) aClass.getAnnotation(StargateFromWhere.class);
    } else {
      stargateFromWhere = method.getAnnotation(StargateFromWhere.class);
    }
    if (stargateFromWhere == null || StringUtils.isBlank(stargateFromWhere.value())
      || ConsumeFromWhere.valueOf(stargateFromWhere.value()) == null) {
      return;
    }
    stargateConsumeBaseDTO.setConsumeFromWhere(ConsumeFromWhere.valueOf(stargateFromWhere.value()));
  }
}
