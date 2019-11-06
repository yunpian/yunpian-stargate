package com.yunpian.stargate.core.client;

import com.yunpian.stargate.core.context.ConsumeContext;
import org.apache.rocketmq.client.consumer.MQConsumer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessClientConsume {

  void processConsumeByContext(ConsumeContext consumeContext, MQConsumer mqConsumer)
    throws Throwable;
}
