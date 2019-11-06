package com.yunpian.stargate.core.builder;

import com.yunpian.stargate.core.annotation.IProcessAnnotationConsume;
import com.yunpian.stargate.core.annotation.IProcessAnnotationProducer;
import com.yunpian.stargate.core.client.IProcessClientConsume;
import com.yunpian.stargate.core.client.IProcessClientProducer;
import com.yunpian.stargate.core.consume.ConsumeFactory;
import com.yunpian.stargate.core.consume.ConsumeHandler;
import com.yunpian.stargate.core.consume.MessageListener;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.producer.ProducerFactory;
import com.yunpian.stargate.core.producer.ProducerInvocationHandlerImpl;
import com.yunpian.stargate.manage.StargateConsumeManageImpl;
import com.yunpian.stargate.manage.StargateInstance;
import com.yunpian.stargate.manage.StargateProcessManageImpl;
import com.yunpian.stargate.manage.dto.StargateConsumeManageDTO;
import com.yunpian.stargate.manage.dto.StargateProducerManageDTO;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/15 Time:下午7:33
 */
public class StargateFactory {

  private static final Logger log = LoggerFactory.getLogger(StargateFactory.class);

  private static StargateProcessManageImpl stargateProcessManage;
  private static StargateConsumeManageImpl stargateConsumeManage;

  private static void init() {
    if (!StargateInstance.getStargateInstance().isInit()) {
      stargateProcessManage = new StargateProcessManageImpl();
      stargateConsumeManage = new StargateConsumeManageImpl();
      StargateInstance.getStargateInstance().init(stargateProcessManage, stargateConsumeManage);
    }
  }

  public static <T> T createProducer(Class<T> clazz, ProducerContext producerContextInit)
    throws Throwable {
    init();
    //生成实例
    //todo 创建对象目前没有太多参数先临时这样
    log.debug("new ProducerInvocationHandlerImpl for Class {}", clazz.getName());
    ProducerInvocationHandlerImpl handler = new ProducerInvocationHandlerImpl();
    T o = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, handler);

    log.debug("clone ProducerContext for class {}", clazz.getName());
    ProducerContext producerContext = producerContextInit.clone();
    for (IProcessAnnotationProducer processProducer : ProcessCenter
      .getProducersAnnotationProcess()) {
      log.debug(
        "IProcessAnnotationProducer for Class {} ing", processProducer.getClass().getName());
      processProducer.processProducerByContext(producerContext, clazz, o, null);
      if (!producerContext.isValid()) {
        log.info("class is not Valid {}", clazz.getName());
        return null;
      }
    }

    Method[] methods = clazz.getMethods();
    method:
    for (Method method : methods) {
      log.debug("clone ProducerContext for method {}", method.getName());
      ProducerContext producerContextMethod = producerContext.clone();
      for (IProcessAnnotationProducer processProducer : ProcessCenter
        .getProducersAnnotationProcess()) {
        log.debug(
          "IProcessAnnotationProducer for Method {} ing", processProducer.getClass().getName());
        processProducer.processProducerByContext(producerContextMethod, clazz, o, method);
      }
      if (!producerContextMethod.isValid()) {
        log.info("method is not Valid {}", method.getName());
        continue method;
      }
      log.debug("create MQProducer {}", method.getName());
      DefaultMQProducer mqProducer = ProducerFactory.getProducer(producerContextMethod);
      producerContextMethod.setMqProducer(mqProducer);
      handler.putContextMap(method, producerContextMethod);
      for (IProcessClientProducer processClientProducer : ProcessCenter
        .getProducersClientProcess()) {
        log.debug("IProcessClientProducer for Method {} ing",
          processClientProducer.getClass().getName());
        processClientProducer.processProducerByContext(producerContextMethod, mqProducer);
      }
      log.info("{} MQProducer start", method.getName());
      stargateProcessManage.add(new StargateProducerManageDTO(producerContextMethod));
      mqProducer.start();
    }
    return o;
  }

  public static <T> T createConsume(Class<T> clazz, ConsumeContext consumeContextInit)
    throws Throwable {
    return createConsume(clazz, consumeContextInit, false);
  }

  public static <T> T createConsume(Class<T> clazz, ConsumeContext consumeContextInit,
    boolean startConsumer)
    throws Throwable {
    init();
    log.debug("new Instance for Class {}", clazz.getName());
    T o = clazz.newInstance();

    log.debug("clone ConsumeContext for class {}", clazz.getName());
    ConsumeContext consumeContext = consumeContextInit.clone();
    for (IProcessAnnotationConsume processConsume : ProcessCenter.getConsumeAnnotationProcess()) {
      log.debug(
        "IProcessAnnotationConsume for Class {} ing", processConsume.getClass().getName());
      processConsume.processConsumeByContext(consumeContext, clazz, o, null);
      if (!consumeContext.isValid()) {
        log.info("class is not Valid {}", clazz.getName());
        return null;
      }
    }

    Method[] methods = clazz.getMethods();
    method:
    for (Method method : methods) {
      //todo 创建对象目前没有太多参数先临时这样
      ConsumeHandler consumeHandler = new ConsumeHandler();
      log.debug("clone ConsumeContext for method {}", method.getName());
      ConsumeContext consumeContextMethod = consumeContext.clone();
      for (IProcessAnnotationConsume processConsume : ProcessCenter.getConsumeAnnotationProcess()) {
        log.debug(
          "IProcessAnnotationConsume for method {} ing", processConsume.getClass().getName());
        processConsume.processConsumeByContext(consumeContextMethod, clazz, o, method);
      }
      if (!consumeContextMethod.isValid()) {
        log.info("method is not Valid {}", clazz.getName());
        continue method;
      }
      log.debug("create MQConsumer {}", method.getName());
      DefaultMQPushConsumer mqConsumer = createMQConsumer(consumeContextMethod);

      consumeHandler.setConsumeContext(consumeContextMethod);
      MessageListener messageListener = new MessageListener();
      messageListener.setConsumeHandler(consumeHandler);
      ConsumeFactory.registerMessageListener(mqConsumer, messageListener);

      consumeContextMethod.setConsumeHandler(consumeHandler);
      consumeContextMethod.setMessageListener(messageListener);
      consumeContextMethod.setMqConsumer(mqConsumer);
      stargateConsumeManage.add(new StargateConsumeManageDTO(consumeContextMethod));
      if (startConsumer) {
        ConsumeFactory.startMQConsumer(consumeContextMethod.getGroup());
      }
    }
    return o;
  }

  public static DefaultMQPushConsumer createMQConsumer(ConsumeContext consumeContext)
    throws Throwable {
    DefaultMQPushConsumer mqConsumer = ConsumeFactory.getConsume(consumeContext);
    for (IProcessClientConsume processClientConsume : ProcessCenter
      .getConsumeClientProcess()) {
      log.debug(
        "IProcessClientConsume for method {} ing", processClientConsume.getClass().getName());
      processClientConsume.processConsumeByContext(consumeContext, mqConsumer);
    }
    return mqConsumer;
  }
}
