package com.yunpian.stargate.core.annotation;

import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.utils.ISortIndex;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessAnnotationConsume extends ISortIndex {

  void processConsumeByContext(ConsumeContext consumeContext, Class aClass, Object o, Method method)
    throws Throwable;

}
