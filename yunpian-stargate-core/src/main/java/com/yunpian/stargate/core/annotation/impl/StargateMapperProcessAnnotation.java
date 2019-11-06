package com.yunpian.stargate.core.annotation.impl;

import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.core.annotation.AbsProcessAnnotationProducerAndConsume;
import com.yunpian.stargate.core.dto.StargateMapperDTO;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.utils.StringUtils;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:3:25 PM
 */
public class StargateMapperProcessAnnotation
  extends AbsProcessAnnotationProducerAndConsume<StargateMapperDTO> {

  @Override
  protected void processConsume(StargateMapperDTO stargateMapperDTO, Class aClass, Object o,
    Method method) throws Throwable {
    processMapper(stargateMapperDTO, aClass, method);
  }

  @Override
  protected void processProducer(StargateMapperDTO stargateMapperDTO, Class aClass, Object o,
    Method method) throws Throwable {
    processMapper(stargateMapperDTO, aClass, method);
  }

  private void processMapper(StargateMapperDTO stargateMapperDTO, Class aClass, Method method) {
    StargateMapper stargateMapper = null;
    if (method == null) {
      stargateMapper = (StargateMapper) aClass.getAnnotation(StargateMapper.class);
    } else {
      stargateMapper = method.getAnnotation(StargateMapper.class);

    }
    if (stargateMapper == null) {
      return;
    }
    String group = stargateMapper.value();
    String topic = stargateMapper.value();
    String tag = stargateMapper.tag();
    if (StringUtils.isEmpty(group)) {
      group = stargateMapper.group();
    }
    if (StringUtils.isEmpty(topic)) {
      topic = stargateMapper.topic();
    }

    if (!StringUtils.isEmpty(group)) {
      stargateMapperDTO.addGroup(group);
    } else if (method == null) {
    } else {
      throw new StargateException("group is null " + aClass.getName() + " " + method.getName());
    }
    if (!StringUtils.isEmpty(topic)) {
      stargateMapperDTO.addTopic(topic);
    } else if (method == null) {
    } else {
      throw new StargateException("topic is null " + aClass.getName() + " " + method.getName());
    }
    if (!StringUtils.isEmpty(tag)) {
      stargateMapperDTO.setTag(tag);
    } else if (method == null) {
    } else {
      stargateMapperDTO.setTag("*");
    }
  }
}
