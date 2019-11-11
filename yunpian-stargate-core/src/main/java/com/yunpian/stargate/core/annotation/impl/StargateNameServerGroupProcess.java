package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.core.annotation.IProcessAnnotationConsume;
import com.yunpian.stargate.core.annotation.IProcessAnnotationProducer;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.StargateMapperDTO;
import com.yunpian.stargate.core.dto.StargateNameServerDTO;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:31 PM
 */
public class StargateNameServerGroupProcess
  implements
  IProcessAnnotationConsume,
  IProcessAnnotationProducer {

  @Override
  public void processConsumeByContext(ConsumeContext consumeContext, Class aClass, Object o,
    Method method) throws Throwable {
    if (method == null) {
      return;
    }
    StargateMapperDTO stargateMapperDTO = consumeContext.getDTO(StargateMapperDTO.class);
    StargateNameServerDTO stargateNameServerDTO = consumeContext
      .getDTO(StargateNameServerDTO.class);
    stargateMapperDTO
      .setGroup(consumeContext.getGroup() + "-" + stargateNameServerDTO.getNamesrvAddr());
  }

  @Override
  public void processProducerByContext(ProducerContext producerContext, Class aClass, Object o,
    Method method) throws Throwable {
    if (method == null) {
      return;
    }
    StargateMapperDTO stargateMapperDTO = producerContext.getDTO(StargateMapperDTO.class);
    StargateNameServerDTO stargateNameServerDTO = producerContext
      .getDTO(StargateNameServerDTO.class);
    stargateMapperDTO
      .setGroup(producerContext.getGroup() + "-" + stargateNameServerDTO.getNamesrvAddr());
  }

}
