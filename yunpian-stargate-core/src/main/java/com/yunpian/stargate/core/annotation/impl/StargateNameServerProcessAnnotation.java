package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateNameServer;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducerAndConsume;
import com.yunpian.stargate.core.dto.StargateNameServerDTO;
import com.yunpian.stargate.core.utils.StringUtils;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/15 Time:下午7:09
 */
public class StargateNameServerProcessAnnotation
  extends AbsProcessAnnotationProducerAndConsume<StargateNameServerDTO> {

  @Override
  protected void processProducer(StargateNameServerDTO stargateNameServerDTO, Class aClass,
    Object o,
    Method method) throws Throwable {
    processStargate(stargateNameServerDTO, aClass, method);
  }

  @Override
  protected void processConsume(StargateNameServerDTO stargateNameServerDTO, Class aClass, Object o,
    Method method) throws Throwable {
    processStargate(stargateNameServerDTO, aClass, method);
  }

  public void processStargate(StargateNameServerDTO stargateNameServerDTO, Class aClass,
    Method method) throws Throwable {
    StargateNameServer stargateNameServer = null;
    if (method == null) {
      stargateNameServer = (StargateNameServer) aClass.getAnnotation(StargateNameServer.class);
    } else {
      stargateNameServer = method.getAnnotation(StargateNameServer.class);
    }
    if (stargateNameServer == null || StringUtils.isBlank(stargateNameServer.value())) {
      return;
    }
    stargateNameServerDTO.setNamesrvAddr(stargateNameServer.value());
  }
}
