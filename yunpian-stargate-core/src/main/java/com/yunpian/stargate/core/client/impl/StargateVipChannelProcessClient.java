package com.yunpian.stargate.core.client.impl;

import com.yunpian.stargate.core.client.AbsProcessClientProducerAndConsume;
import com.yunpian.stargate.core.dto.StargateVipChannelDTO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:4:31 PM
 */
public class StargateVipChannelProcessClient
  extends AbsProcessClientProducerAndConsume<StargateVipChannelDTO> {

  @Override
  protected void processConsume(StargateVipChannelDTO stargateVipChannelDTO, MQConsumer mqConsumer)
    throws Throwable {
    DefaultMQPushConsumer defaultMQPushConsumer = (DefaultMQPushConsumer) mqConsumer;
    if (stargateVipChannelDTO.getVipChannel() == null) {
      defaultMQPushConsumer.setVipChannelEnabled(false);
      return;
    }
    defaultMQPushConsumer.setVipChannelEnabled(stargateVipChannelDTO.getVipChannel());
  }

  @Override
  protected void processProducer(StargateVipChannelDTO stargateVipChannelDTO, MQProducer mqProducer)
    throws Throwable {
    DefaultMQProducer defaultMQProducer = (DefaultMQProducer) mqProducer;
    if (stargateVipChannelDTO.getVipChannel() == null) {
      defaultMQProducer.setVipChannelEnabled(false);
      return;
    }
    defaultMQProducer.setVipChannelEnabled(stargateVipChannelDTO.getVipChannel());
  }

}
