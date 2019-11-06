package com.yunpian.stargate.springboot;

import com.yunpian.stargate.annotation.StargateConsumer;
import com.yunpian.stargate.annotation.StargateProducer;
import com.yunpian.stargate.core.utils.StringUtils;
import com.yunpian.stargate.springboot.annotation.EnableStargate;
import com.yunpian.stargate.springboot.exception.StargateCreateException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/16 Time:上午10:23
 */
public class StargateRegistrar implements ImportBeanDefinitionRegistrar,
  ResourceLoaderAware, EnvironmentAware {

  private static final Logger log = LoggerFactory.getLogger(StargateRegistrar.class);
  private ResourceLoader resourceLoader;

  private Environment environment;

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;

  }

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
    BeanDefinitionRegistry registry) {
    registrarFactory(registry);
    Set<String> basePackages = getBasePackages(importingClassMetadata);
    basePackages.add("com.yunpian.stargate.dogrobber.consumer");
    basePackages.add("com.yunpian.stargate.dogrobber.product");
    //	扫描指定目录下的注解
    Reflections reflections = new Reflections(basePackages);
    Set<Class<?>> rocketClientConsumerClassSet = reflections
      .getTypesAnnotatedWith(StargateConsumer.class);
    Set<Class<?>> rocketClientProducerClassSet = reflections
      .getTypesAnnotatedWith(StargateProducer.class);
    registryConsume(rocketClientConsumerClassSet, registry);
    registryProducer(rocketClientProducerClassSet, registry);
    registryDogrobber(registry);
  }

  private Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
    Map<String, Object> attrs = importingClassMetadata.
      getAnnotationAttributes(EnableStargate.class.getName());
    Set<String> basePackages = new HashSet<>();
    for (String strings : (String[]) attrs.get("value")) {
      basePackages.add(strings);
    }
    if (basePackages.isEmpty()) {
      basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
    }
    log.debug("basePackages:" + basePackages);
    return basePackages;
  }

  private void registrarFactory(BeanDefinitionRegistry registry) {
    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    genericBeanDefinition.setBeanClass(StargateRegistrarFactory.class);
    genericBeanDefinition.setScope("singleton");       //设置scope
    genericBeanDefinition.setLazyInit(false);          //设置是否懒加载
    genericBeanDefinition.setAutowireCandidate(true);  //设置是否可以被其他对象自动注入
    genericBeanDefinition.setInitMethodName("init");  //设置是否可以被其他对象自动注入
    registry.registerBeanDefinition("stargateRegistrarFactory", genericBeanDefinition);
  }

  private void registryProducer(Set<Class<?>> classSet, BeanDefinitionRegistry registry) {
    log.debug("RocketClientProducer set:" + classSet);
    for (Class<?> clazz : classSet) {
      if (!clazz.isInterface()) {
        StargateCreateException stargateCreateException =
          new StargateCreateException("registryProducer error. Producer must Interface");
        log.error("StargateCreateException:", stargateCreateException);
        throw stargateCreateException;
      }

      StargateProducer rocketClientProducer = clazz.getAnnotation(StargateProducer.class);
      String beanName = rocketClientProducer.value();
      if (StringUtils.isEmpty(beanName)) {
        beanName = getbeanName(clazz);
      }
      registryBean(registry, beanName, "createProducer", new Object[]{clazz});
    }
  }

  private void registryConsume(Set<Class<?>> classSet, BeanDefinitionRegistry registry) {
    log.debug("RocketClientConsume set:" + classSet);
    for (Class<?> clazz : classSet) {
      if (clazz.isAnnotation() || clazz.isArray() || clazz.isEnum() || clazz.isInterface()) {
        StargateCreateException stargateCreateException =
          new StargateCreateException("registryConsume error. Consume must Class");
        log.error("StargateCreateException:", stargateCreateException);
        throw stargateCreateException;
      }

      StargateConsumer rocketClientConsumer = clazz.getAnnotation(StargateConsumer.class);
      String beanName = rocketClientConsumer.value();
      if (StringUtils.isEmpty(beanName)) {
        beanName = getbeanName(clazz);
      }
      registryBean(registry, beanName, "createConsume", new Object[]{clazz});
    }
  }

  private String getbeanName(Class aClass) {
    String name = aClass.getName().replace(".", "-") + "-RocketClientImpl";
    return name;
  }

  private void registryBean(BeanDefinitionRegistry registry, String beanName,
    String factoryMethodName, Object[] arguments) {
    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    genericBeanDefinition.setFactoryBeanName("stargateRegistrarFactory");
    ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
    genericBeanDefinition.setFactoryMethodName(factoryMethodName);
    for (int i = 0; i < arguments.length; ++i) {
      argumentValues.addIndexedArgumentValue(i, arguments[i]);
    }
    genericBeanDefinition.setScope("singleton");       //设置scope
    genericBeanDefinition.setLazyInit(false);          //设置是否懒加载
    genericBeanDefinition.setAutowireCandidate(true);  //设置是否可以被其他对象自动注入
    genericBeanDefinition.setConstructorArgumentValues(argumentValues);
    registry.registerBeanDefinition(beanName, genericBeanDefinition);
  }

  private void registryDogrobber(BeanDefinitionRegistry registry) {
    try {
      Class aClass = Class.forName("com.yunpian.stargate.dogrobber.timejob.UploadStatusJob");
      GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
      genericBeanDefinition.setBeanClass(aClass);
      genericBeanDefinition.setScope("singleton");
      genericBeanDefinition.setLazyInit(false);
      genericBeanDefinition.setAutowireCandidate(true);
      MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
      RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(
        "com-yunpian-stargate-dogrobber-product-DataUploadProduct");
      PropertyValue propertyValue = new PropertyValue("dataUploadProduct", runtimeBeanReference);
      mutablePropertyValues.addPropertyValue(propertyValue);
      genericBeanDefinition.setPropertyValues(mutablePropertyValues);
      genericBeanDefinition.setInitMethodName("init");
      registry.registerBeanDefinition("com-yunpian-stargate-dogrobber-timejob-uploadStatusJob",
        genericBeanDefinition);
    } catch (ClassNotFoundException e) {
      log.info("not find dogrobber");
    }
  }
}
