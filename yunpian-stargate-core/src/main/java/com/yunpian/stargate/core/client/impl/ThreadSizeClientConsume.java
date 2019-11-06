package com.yunpian.stargate.core.client.impl;

import com.yunpian.stargate.core.client.AbsProcessClientConsume;
import com.yunpian.stargate.core.dto.StargateThreadSizeDTO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class ThreadSizeClientConsume extends AbsProcessClientConsume<StargateThreadSizeDTO> {

  @Override
  protected void processConsume(StargateThreadSizeDTO stargateThreadSizeDTO,
    MQConsumer mqConsumer) throws Throwable {
    DefaultMQPushConsumer defaultMQPushConsumer = (DefaultMQPushConsumer) mqConsumer;
    defaultMQPushConsumer.setConsumeThreadMin(stargateThreadSizeDTO.getThreadSize());
    defaultMQPushConsumer.setConsumeThreadMax(stargateThreadSizeDTO.getThreadSize());
  }
}
