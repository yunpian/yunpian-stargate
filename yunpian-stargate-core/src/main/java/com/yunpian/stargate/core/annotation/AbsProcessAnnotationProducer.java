package com.yunpian.stargate.core.annotation;

import com.yunpian.stargate.core.context.AbsProcessContext;
import com.yunpian.stargate.core.context.ProducerContext;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public abstract class AbsProcessAnnotationProducer<DTO>
  extends AbsProcessContext<DTO>
  implements IProcessAnnotationProducer {

  protected abstract void processProducer(DTO dto, Class aClass, Object o, Method method)
    throws Throwable;

  @Override
  public void processProducerByContext(ProducerContext producerContext, Class aClass, Object o,
    Method method) throws Throwable {
    processProducer((DTO) producerContext.getDTOOrNew(this), aClass, o, method);
  }

}
