package com.yunpian.stargate.core.client;

import com.yunpian.stargate.core.context.AbsProcessContext;
import com.yunpian.stargate.core.context.ConsumeContext;
import org.apache.rocketmq.client.consumer.MQConsumer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public abstract class AbsProcessClientConsume<DTO>
  extends AbsProcessContext<DTO>
  implements IProcessClientConsume {

  protected abstract void processConsume(DTO dto, MQConsumer mqConsumer)
    throws Throwable;

  @Override
  public void processConsumeByContext(ConsumeContext consumeContext, MQConsumer mqConsumer)
    throws Throwable {
    processConsume((DTO) consumeContext.getDTOOrNew(this), mqConsumer);
  }

}
