package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateVipChannel;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducerAndConsume;
import com.yunpian.stargate.core.dto.StargateVipChannelDTO;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:31 PM
 */
public class StargateVipChannelProcessAnnotation
  extends AbsProcessAnnotationProducerAndConsume<StargateVipChannelDTO> {

  private void process(StargateVipChannelDTO stargateVipChannelDTO, Class aClass, Object o,
    Method method) throws Throwable {
    StargateVipChannel stargateVipChannel = null;
    if (method == null) {
      stargateVipChannel = (StargateVipChannel) aClass.getAnnotation(StargateVipChannel.class);
    } else {
      stargateVipChannel = method.getAnnotation(StargateVipChannel.class);
    }
    if (stargateVipChannel == null) {
      return;
    }
    stargateVipChannelDTO.setVipChannel(stargateVipChannel.value());
  }

  @Override
  protected void processConsume(StargateVipChannelDTO stargateVipChannelDTO, Class aClass, Object o,
    Method method) throws Throwable {
    process(stargateVipChannelDTO,aClass,o,method);
  }

  @Override
  protected void processProducer(StargateVipChannelDTO stargateVipChannelDTO, Class aClass,
    Object o, Method method) throws Throwable {
    process(stargateVipChannelDTO,aClass,o,method);
  }
}
