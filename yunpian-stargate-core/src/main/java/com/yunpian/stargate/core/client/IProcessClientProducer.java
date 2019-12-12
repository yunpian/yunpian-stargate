package com.yunpian.stargate.core.client;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.utils.ISortIndex;
import org.apache.rocketmq.client.producer.MQProducer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessClientProducer extends ISortIndex {

  void processProducerByContext(ProducerContext producerContext, MQProducer mqProducer)
    throws Throwable;

}
