package com.yunpian.stargate.core.client.impl;

import com.yunpian.stargate.core.client.AbsProcessClientConsume;
import com.yunpian.stargate.core.dto.StargateMapperDTO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class MapperProcessClientConsume extends
  AbsProcessClientConsume<StargateMapperDTO> {

  @Override
  protected void processConsume(StargateMapperDTO stargateMapperDTO,
    MQConsumer mqConsumer) throws Throwable {
    DefaultMQPushConsumer defaultMQPushConsumer = (DefaultMQPushConsumer) mqConsumer;
    defaultMQPushConsumer.subscribe(stargateMapperDTO.getTopic(), stargateMapperDTO.getTag());
  }
}
