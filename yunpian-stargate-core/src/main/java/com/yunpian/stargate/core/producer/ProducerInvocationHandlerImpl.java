package com.yunpian.stargate.core.producer;

import com.yunpian.stargate.core.builder.ProcessCenter;
import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.ProducerMethodsDTO;
import com.yunpian.stargate.core.dto.StargateOneWayDTO;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.core.utils.LogUtils;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/20 Time:下午3:52
 */
public class ProducerInvocationHandlerImpl implements InvocationHandler {

  private static final Logger log = LoggerFactory.getLogger(ProducerInvocationHandlerImpl.class);
  private final Map<Method, ProducerContext> producerContextMap = new ConcurrentHashMap();

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    ProducerContext producerContext = producerContextMap.get(method);
    if (producerContext == null) {
      return objectBaseMethod(proxy, method, args);
    }
    MQProducer client = producerContext.getMqProducer();
    if (client == null) {
      throw new StargateException("client is null " + method.getName());
    }
    Message message = new Message();

    for (IProcessMessageProducer producerContextMethod : ProcessCenter
      .getProducersMessageProcess()) {
      log.debug(
        "IProcessMessageProducer for " + method.getName() + ":" + producerContextMethod.getClass()
          .getName() + " ing");
      int result = producerContextMethod.processProducer(producerContext, message, args);
      if (result == IProcessMessageProducer.BREAK) {
        log.debug(
          "IProcessMessageProducer for " + method.getName() + ":" + producerContextMethod.getClass()
            .getName() + " BREAK");
        return null;
      }
      if (result == IProcessMessageProducer.SEND) {
        log.debug(
          "IProcessMessageProducer for " + method.getName() + ":" + producerContextMethod.getClass()
            .getName() + " SEND");
        break;
      }
    }

    StargateOneWayDTO stargateOneWayDTO = producerContext.getDTO(StargateOneWayDTO.class);
    ProducerMethodsDTO producerMethodsDTO = producerContext.getDTO(ProducerMethodsDTO.class);

    LogUtils.messageLog(message);
    if (stargateOneWayDTO.isOneWay()) {
      log.debug("send message for " + method.getName() + ":sendOneway");
      client.sendOneway(message);
      return null;
    }
    if (producerMethodsDTO.getSendCallbackIndex() > 0) {
      log.debug("send message for " + method.getName() + ":SendCallback");
      client.send(message, (SendCallback) args[producerMethodsDTO.getSendCallbackIndex()]);
      return null;
    }
    log.debug("send message for " + method.getName() + ":send");
    return client.send(message);
  }

  private Object objectBaseMethod(Object proxy, Method method, Object[] args)
    throws Throwable {
    if ("toString".equals(method.getName()) && method.getParameterTypes().length == 0 &&
      method.getReturnType().equals(String.class)) {
      return "StargateProducerProxy";
    }
    if (isDefaultMethod(method)) {
      Object o = invokeDefaultMethod(proxy, method, args);
      return o;
    }
    return method.invoke(this, args);
  }

  public void putContextMap(Method method, ProducerContext producerContext) {
    this.producerContextMap.put(method, producerContext);
  }

  private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
    throws Throwable {
    final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
      .getDeclaredConstructor(Class.class, int.class);
    if (!constructor.isAccessible()) {
      constructor.setAccessible(true);
    }
    final Class<?> declaringClass = method.getDeclaringClass();
    return constructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE)
      .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
  }

  private boolean isDefaultMethod(Method method) {
    return ((method.getModifiers()
      & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC)
      && method.getDeclaringClass().isInterface();
  }
}
