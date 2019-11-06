package com.yunpian.stargate.core.client.impl;

import com.yunpian.stargate.core.client.AbsProcessClientConsume;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class ConsumeBaseProcessClientConsume extends
  AbsProcessClientConsume<StargateConsumeBaseDTO> {

  @Override
  protected void processConsume(StargateConsumeBaseDTO stargateConsumeBaseDTO,
    MQConsumer mqConsumer) throws Throwable {
    DefaultMQPushConsumer defaultMQPushConsumer = (DefaultMQPushConsumer) mqConsumer;
    defaultMQPushConsumer.setConsumeMessageBatchMaxSize(1);
    defaultMQPushConsumer.setPullBatchSize(stargateConsumeBaseDTO.getPullBatchSize());
    defaultMQPushConsumer.setConsumeFromWhere(stargateConsumeBaseDTO.getConsumeFromWhere());
    if (stargateConsumeBaseDTO.isBroadcasting()) {
      defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
    } else {
      defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
    }
  }
}
