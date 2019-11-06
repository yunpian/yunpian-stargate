package com.yunpian.extend.bus;

import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.core.annotation.IProcessAnnotationConsume;
import com.yunpian.stargate.core.annotation.IProcessAnnotationProducer;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.dto.ProducerMethodsDTO;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import com.yunpian.stargate.core.dto.StargateMapperDTO;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.utils.StringUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class StargateMsgBusAnnotationHandel
  implements
  IProcessAnnotationConsume,
  IProcessAnnotationProducer {

  private String appName;

  public StargateMsgBusAnnotationHandel(String appName) {
    this.appName = appName;
  }

  @Override
  public void processConsumeByContext(ConsumeContext consumeContext, Class aClass, Object o,
    Method method) throws Throwable {
    StargateMapperDTO stargateMapperDTO = consumeContext.getDTOOrNew(StargateMapperDTO.class);
    ConsumeMethodsDTO consumeMethodsDTO = consumeContext.getDTOOrNew(ConsumeMethodsDTO.class);
    StargateConsumeBaseDTO stargateConsumeBaseDTO = consumeContext
      .getDTOOrNew(StargateConsumeBaseDTO.class);

    StargateMsgBus stargateMsgBus = null;
    if (method == null) {
      stargateMsgBus = (StargateMsgBus) aClass.getAnnotation(StargateMsgBus.class);
    } else {
      stargateMsgBus = method.getAnnotation(StargateMsgBus.class);
      StargateMapper stargateMapper = method.getAnnotation(StargateMapper.class);
      if (stargateMapper != null && stargateMsgBus != null) {
        throw new StargateException(
          "There is only one choice in @StargateMapper and @StargateMsgBus");
      }
    }
    if (stargateMsgBus == null) {
      return;
    }

    String topic = stargateMsgBus.value();
    String name = stargateMsgBus.name();
    if (!StringUtils.isEmpty(name)) {
      topic = name;
    }
    String tag = stargateMsgBus.pip();
    if (StringUtils.isBlank(tag)) {
      tag = "*";
    }
    if (!StringUtils.isEmpty(topic)) {
      stargateMapperDTO.addTopic(topic);
    } else {
      throw new StargateException("Bus Name is null " + aClass.getName() + " " + method.getName());
    }

    stargateMapperDTO.addGroup(appName + "-" + topic.trim());
    stargateMapperDTO.setTag(tag.trim());
    consumeMethodsDTO.setValid(true);
    stargateConsumeBaseDTO.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
  }

  @Override
  public void processProducerByContext(ProducerContext producerContext, Class aClass, Object o,
    Method method) throws Throwable {
    StargateMapperDTO stargateMapperDTO = producerContext.getDTOOrNew(StargateMapperDTO.class);
    ProducerMethodsDTO producerMethodsDTO = producerContext.getDTOOrNew(ProducerMethodsDTO.class);
    StargateMsgBusDTO stargateMsgBusDTO = producerContext.getDTOOrNew(StargateMsgBusDTO.class);

    StargateMsgBus stargateMsgBus = null;
    if (method == null) {
      stargateMsgBus = (StargateMsgBus) aClass.getAnnotation(StargateMsgBus.class);
    } else {
      stargateMsgBus = method.getAnnotation(StargateMsgBus.class);
      StargateMapper stargateMapper = method.getAnnotation(StargateMapper.class);
      if (stargateMapper != null && stargateMsgBus != null) {
        throw new StargateException(
          "There is only one choice in @StargateMapper and @StargateMsgBus");
      }
    }
    if (stargateMsgBus == null) {
      return;
    }

    String topic = stargateMsgBus.value();
    String name = stargateMsgBus.name();
    if (!StringUtils.isEmpty(name)) {
      topic = name;
    }
    String tag = stargateMsgBus.pip();
    if (StringUtils.isBlank(tag)) {
      tag = "*";
    }
    if (!StringUtils.isEmpty(topic)) {
      stargateMapperDTO.addTopic(topic);
    } else {
      throw new StargateException("Bus Name is null " + aClass.getName() + " " + method.getName());
    }

    if (method != null) {
      Class[] classes = method.getParameterTypes();
      int parameterSize = classes.length;
      Annotation[][] annotations = method.getParameterAnnotations();
      method:
      for (int i = 0; i < parameterSize; ++i) {
        for (Annotation annotations1 : annotations[i]) {
          if (annotations1 instanceof StargateMsgBusPip) {
            stargateMsgBusDTO.setStargateMsgBusPipIndex(i);
            break method;
          }
        }
      }
    }

    stargateMapperDTO.addGroup(appName + "-" + topic);
    stargateMapperDTO.setTag(tag);
    producerMethodsDTO.setValid(true);
  }

}
