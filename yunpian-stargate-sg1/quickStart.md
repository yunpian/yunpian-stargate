# 快速开始

#### 6个接口

- IProcessAnnotationConsume
  - *消费者注解处理器*
- IProcessAnnotationProducer
  - *生产者注解处理器*
- IProcessClientConsume
  - *消费者Client处理器*
- IProcessClientProducer
  - *生产者Client处理器*
- IProcessMessageConsum
  - *消费者Message处理器*
- IProcessMessageProducer  
  - *生产者Message处理器*

#### 两个上下文

+ ConsumeContext
  + 消费者上下文
  + 每个消费者方法一个上下文
  + 每个消费者类在初始化过程中会有一个临时的上下文
  + 方法上下文创建时会从类上下文中继承数据
+ ProducerContext
  - 生产者上下文
  - 每个生产者方法一个上下文
  - 每个生产者接口在初始化过程中会有一个临时的上下文
  - 方法上下文创建时会从类上下文中继承数据

#### 扩展一个新注解

1. 实现Spring MVC中的@RequestBody注解

2. 认识两个接口
   - IProcessAnnotationConsume

     - *消费者注解处理器*
     - 参数1：ConsumeContext
       - 消费者上下文
     - 参数2：Class
       - 消费者类对应的Class
     - 参数3：Object
       - 消费者实例
     - 参数4：Method
       - 当前处理的方法
       - 处理类注解时为null
   - IProcessAnnotationProducer

     - *生产者注解处理器*

       - 参数1：ProducerContext
         - 生产者上下文
       - 参数2：Class
         - 生产者接口
       - 参数3：Object
         - 生产者代理实例

       - 参数4：Method
         - 当前处理的方法
         - 处理类注解时为null

3. 实现接口

   + 通过Class或Method获取你想要的注解
   + 处理注解信息
   + 将数据保存到上下文
   + 示例

   ```java
   public class DemoProcessAnnotationConsume
     implements IProcessAnnotationConsume {
     @Override
     public void processConsumeByContext(
       ConsumeContext consumeContext,
       Class aClass,
       Object o,
       Method method
       ) throws Throwable {
    
       //method=null说明在处理类注解，这个注解只需要处理方法注解即可
       if (method == null) {
         return;
       }
       //从上下文中获取到存放StargateBody注解数据的DTO修改数据
       StargateBodyDTO stargateBodyDTO = consumeContext.getDTO(StargateBodyDTO.class);
       Class[] classes = method.getParameterTypes();
       int parameterSize = classes.length;
       Annotation[][] annotations = method.getParameterAnnotations();
       for (int i = 0; i < parameterSize; ++i) {
         for (Annotation annotations1 : annotations[i]) {
           //找到RequestBody注解，设置到DTO
           if (annotations1 instanceof RequestBody) {
             stargateBodyDTO.setBodyIndex(i);
             stargateBodyDTO.setBodyClass(classes[i]);
             continue;
           }
         }
       }
     }
   }
   ```

   ```java
   @Configuration
   public class StargateConfiguration {
   
     //配置后yml文件中的配置将失效
     @Bean
     public StargateConfig stargateConfig() {
       StargateConfig stargateConfig = new StargateConfig();
       stargateConfig.setThreadSize(6);
       stargateConfig.setNamesrvDefault("yunpian");
       Map<String, String> nameServer = new ConcurrentHashMap<>();
       nameServer.put("yunpian", "127.0.0.1:9876");
       stargateConfig.setNamesrvAddr(nameServer);
       stargateConfig.setEnv("auto-test");
       stargateConfig.setEncodClass(JacksonStargateClientEncode.class);
       stargateConfig.setDecodeClass(JacksonStargateClientDecode.class);
     	//注册注解处理器 
       stargateConfig.addProcessAnnotationConsume(new DemoProcessAnnotationConsume());
         
       return stargateConfig;
     }
   }
   ```

4. 3个抽象类

   + AbsProcessAnnotationConsume
     + 将自动把泛型中的DTO取出，继承该类不用再关心上下文
   + AbsProcessAnnotationProducer
     - 将自动把泛型中的DTO取出，继承该类不用再关心上下文
   + AbsProcessAnnotationProducerAndConsume
     - 将自动把泛型中的DTO取出，继承该类不用再关心上下文
     - 用于一些生产者和消费者都被使用的注解

5. 看一下StargateBody的处理过程

   ```java
   public class StargateBodyProcessAnnotation
     extends AbsProcessAnnotationProducerAndConsume<StargateBodyDTO> {
     @Override
     protected void processConsume(StargateBodyDTO stargateBodyDTO, Class aClass, Object o,
       Method method)
       throws Throwable {
       processStargate(stargateBodyDTO, aClass, method);
     }
   
     @Override
     protected void processProducer(StargateBodyDTO stargateBodyDTO, Class aClass, Object o,
       Method method) throws Throwable {
       processStargate(stargateBodyDTO, aClass, method);
     }
   
     private void processStargate(StargateBodyDTO stargateBodyDTO, Class aClass, Method method)
       throws Throwable {
       if (method == null) {
         return;
       }
       Class[] classes = method.getParameterTypes();
       int parameterSize = classes.length;
       Annotation[][] annotations = method.getParameterAnnotations();
       for (int i = 0; i < parameterSize; ++i) {
         for (Annotation annotations1 : annotations[i]) {
           if (annotations1 instanceof StargateBody) {
             stargateBodyDTO.setBodyIndex(i);
             stargateBodyDTO.setBodyClass(classes[i]);
             continue;
           }
         }
       }
     }
   }
   ```

6. 自己再实现一个把发送消息的内容全部替换成注解里的内容的功能模块

   + 这里只有注解处理部分
   + 如何替换看下面

   ```java
   @Target({ElementType.METHOD})
   @Documented
   @Retention(RetentionPolicy.RUNTIME)
   public @interface DemoModel {
   
     String value() default "";
   }
   ```

   ```java
   public class DemoModelDTO {
   
     public String v;
   
     public String getV() {
       return v;
     }
   
     public void setV(String v) {
       this.v = v;
     }
   }
   ```

   ```java
   public class DemoAnnotation extends AbsProcessAnnotationProducer<DemoModelDTO> {
   
     @Override
     protected void processProducer(DemoModelDTO demoModelDTO, Class aClass, Object o, Method method)
       throws Throwable {
       if (method == null) {
         return;
       }
       DemoModel demoModel = method.getAnnotation(DemoModel.class);
       if (demoModel == null) {
         return;
       }
       //把注解解析的数据放到上下文中
       demoModelDTO.setV(demoModel.value());
     }
   }
   
   ```

#### 修改RocketMq Client的属性

1. 2个接口和3个抽象类

   + 大家可以参考注解的接口的抽象类
   + IProcessClientConsume
     + ConsumeContext
     + MQConsumer
   + IProcessClientProducer
     - ProducerContext
     - MQProducer
   + AbsProcessClientConsume
   + AbsProcessClientProducer
   + AbsProcessClientProducerAndConsume

2. 看一下广播的实现

   ```java
   public class ConsumeBaseProcessClientConsume extends
     AbsProcessClientConsume<StargateConsumeBaseDTO> {
   
     @Override
     protected void processConsume(StargateConsumeBaseDTO stargateConsumeBaseDTO,
       MQConsumer mqConsumer) throws Throwable {
       DefaultMQPushConsumer defaultMQPushConsumer = (DefaultMQPushConsumer) mqConsumer;
       defaultMQPushConsumer.setPullBatchSize(stargateConsumeBaseDTO.getPullBatchSize());
       defaultMQPushConsumer.setConsumeFromWhere(stargateConsumeBaseDTO.getConsumeFromWhere());
       if (stargateConsumeBaseDTO.isBroadcasting()) {
         defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
       } else {
         defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
       }
     }
   }
   ```

#### 消息发送前的加工

1. 两个接口
   + IProcessMessageConsume
     + ConsumeContext
     + MessageExt
       + 接收到的消息
     + ConsumeConcurrentlyContext
     + Object[]
       + 消费者方法参数
     + 返回结果int
       + SUCCESS 正常继续
       + CONSUME 不理会后面的处理器直接消费消息
       + BREAK 中断处理
   + IProcessMessageProducer
     - ProducerContext
     - Message
       - 要发送的消息
     - Object[]
       - 生产者方法参数
     - 返回结果int
       - SUCCESS 正常继续
       - CONSUME 不理会后面的处理器直接消费消息
       - BREAK 中断处理

2. 实现一个生产者消息处理器

   + 把发送消息的内容全部替换成注解里的内容 
   + DemoModelDTO

   ```java
   public class DemoMessage implements IProcessMessageProducer {
   
     @Override
     public int processProducer(ProducerContext producerContext, Message message, Object[] args)
       throws Throwable {
       //DemoModel在上下文中的数据
       DemoModelDTO demoModelDTO = producerContext.getDTO(DemoModelDTO.class);
       //获取要替代的数据
       String s = demoModelDTO.getV();
       //如果是null就结束
       if (StringUtils.isEmpty(s)) {
         //返回SUCCESS继续解析其他处理器
         return SUCCESS;
       }
       //构造消息载体
       StargateMessage<String> stargateMessage = new StargateMessage<>();
       stargateMessage.setBody(s);
       //获取编码器
       StargateEncodDTO stargateEncodDTO = producerContext.getDTO(StargateEncodDTO.class);
       //替换消息内容
       message
         .setBody(stargateEncodDTO.getRocketClientEncod().encod(stargateMessage, producerContext));
       //返回SUCCESS继续解析其他处理器
       return SUCCESS;
     }
   }
   ```

#### 注册这些处理器 

+ 在配置的时候注册这些处理器

    ```java
    @Configuration
    public class StargateConfiguration {

      //配置后yml文件中的配置将失效
      @Bean
      public StargateConfig stargateConfig() {
        StargateConfig stargateConfig = new StargateConfig();
        stargateConfig.setThreadSize(6);
        stargateConfig.setNamesrvDefault("yunpian");
        Map<String, String> nameServer = new ConcurrentHashMap<>();
        nameServer.put("yunpian", "127.0.0.1:9876");
        stargateConfig.setNamesrvAddr(nameServer);
        stargateConfig.setEnv("test");
        stargateConfig.setEncodClass(JacksonStargateClientEncode.class);
        stargateConfig.setDecodeClass(JacksonStargateClientDecode.class);
    
    //    stargateConfig.addProcessMessageConsume();
        //注册之前写好的消息处理器
        stargateConfig.addProcessMessageProducer(new DemoMessage());
    
    //    stargateConfig.addProcessClientConsume();
    //    stargateConfig.addProcessClientProducer();
    
    //    stargateConfig.addProcessAnnotationConsume();
        //注册之前写好的注解处理器
        stargateConfig.addProcessAnnotationProducer(new DemoAnnotation());
    
        return stargateConfig;
      }
    }
    ```

