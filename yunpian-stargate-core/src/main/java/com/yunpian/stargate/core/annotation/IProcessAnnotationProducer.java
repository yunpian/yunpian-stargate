package com.yunpian.stargate.core.annotation;

import com.yunpian.stargate.core.context.ProducerContext;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessAnnotationProducer {

  void processProducerByContext(ProducerContext producerContext, Class aClass, Object o,
    Method method) throws Throwable;

}
