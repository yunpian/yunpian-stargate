package com.yunpian.stargate.core.client;

import com.yunpian.stargate.core.context.AbsProcessContext;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.context.ProducerContext;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.producer.MQProducer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public abstract class AbsProcessClientProducerAndConsume<DTO>
  extends AbsProcessContext<DTO>
  implements
  IProcessClientProducer,
  IProcessClientConsume {

  protected abstract void processConsume(DTO dto, MQConsumer mqConsumer) throws Throwable;

  @Override
  public void processConsumeByContext(ConsumeContext consumeContext, MQConsumer mqConsumer)
    throws Throwable {
    processConsume((DTO) consumeContext.getDTOOrNew(this), mqConsumer);
  }

  protected abstract void processProducer(DTO dto, MQProducer mqProducer) throws Throwable;

  @Override
  public void processProducerByContext(ProducerContext producerContext, MQProducer mqProducer)
    throws Throwable {
    processProducer((DTO) producerContext.getDTOOrNew(this), mqProducer);
  }

}
