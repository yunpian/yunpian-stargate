# 使用手册

### 快速入门

1. 引入依赖 

   ```xml
   <!--stargate的springboot依赖，包含核心功能以及一个以及JDK原生序列化实现的编解码器-->
   <dependency>
       <groupId>com.yunpian.stargate</groupId>
       <artifactId>yunpian-stargate-springboot</artifactId>
       <version>0.9.0-SNAPSHOT</version>
   </dependency>
   ```
   
   ```xml
   <!--基于jackson的编解码器，根据需要引入-->
   <dependency>
       <groupId>com.yunpian.stargate</groupId>
       <artifactId>yunpian-stargate-jackson</artifactId>
       <version>0.9.0-SNAPSHOT</version>
   </dependency>
   ```
   
   ```xml
   <!--兼容云片的编解码器，根据需要引入-->
   <dependency>
       <groupId>com.yunpian.stargate</groupId>
       <artifactId>yunpian-stargate-yunpian</artifactId>
       <version>0.9.0-SNAPSHOT</version>
   </dependency>
   ```

2. 启动类添加`@EnableStargate`注解

   ```java
   @SpringBootApplication
   @EnableStargate
   public class TestApplication {
       public static void main(String[] args) {
           SpringApplication.run(TestApplication.class, args);
       }
   }
   ```

3. 初始化配置

+ 方法一

   ```yaml
   stargate:
     #默认的解码器类
     decodeClassName: com.yunpian.stargate.jackson.JacksonStargateClientDecode  
     #默认的编码器类
     encodClassName: com.yunpian.stargate.jackson.JacksonStargateClientEncode
     #consume线程池的默认大小
     threadSize: 4
     #指定环境，用于环境隔离
     env: test
     #3.x.xRocketMQ服务器端请设置false
     vipChannel: false
     #指定默认的namesrvAddr，不填为default
     namesrvDefault: yunpian
     #如果收到未到达时间的延时消息是否重新放回队列（自定义毫秒数延时请开启），默认false
     delayMsecSwitch: true
     #NameServer地址，使用‘,’‘;’分隔多个地址均可  
     namesrvAddr:
       default: 127.0.0.1:9876  
       yunpian: 127.0.0.1:9876  
       ycloud: 127.0.0.1:9876  
       weike: 127.0.0.1:9876  
   ```

+ 方法二

   ```java
   @Configuration
   public class Config {
       //配置后yml文件中的配置将失效
       @Bean
       public StargateConfig stargateConfig(){
           StargateConfig stargateConfig = new StargateConfig();
           stargateConfig.setThreadSize(6);
           stargateConfig.setNamesrvDefault("yunpian");
           Map<String,String> nameServer = new ConcurrentHashMap<>();
           nameServer.put("yunpian","127.0.0.1:9876;127.0.0.1:9871");
           nameServer.put("weike","127.0.0.1:9876");
           nameServer.put("default","127.0.0.1:9876");
           stargateConfig.setNamesrvAddr(nameServer);
           stargateConfig.setEnv("test");
           stargateConfig.setVipChannel(false);
           stargateConfig.setEncodClass(JacksonRocketClientEncode.class);
           stargateConfig.setDecodeClass(JacksonRocketClientDecode.class);
           stargateConfig.setDelayMsecSwitch(true);
           return stargateConfig;
       }
   }
   ```

4. 启动消费

   ```java
    if (StargateInstance.getStargateInstance().isInit()) {
        StargateInstance.getStargateInstance().getStargateConsumeManage().startAll();
    }else {
        throw new RuntimeException("错误，未初始化");
    }
   ```

5. 编写生产者

   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("testaaa")
       SendResult test(@StargateBody HashMap a);
   }
   ```

6. 编写消费者

   ```java
   @StargateConsumer
   public class TestConsumer {
       @StargateMapper("testaaa")
       public void test(@StargateBody HashMap a){
           //TODO
       }
   }
   ```

7. 发送消息

   ```java
   public class Test {
       @Autowired
       private TestProducter testProducter;
       public static void main(String[] args){
           SendResult sendResult = testProducter.test(new HashMap());
       }
   }
   ```

8. 关闭消费下线

   ```java
    StargateInstance.getStargateInstance().getStargateConsumeManage().closeAll();
    while (!StargateInstance.getStargateInstance().getStargateConsumeManage().isAllClose()) {
        System.out.println("等待关闭");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println("已经关闭");
   ```

### 生产者

1. 同步发送

   ```java
   @StargateProducer
   @StargateOneWay
   public interface TestProducter {
       @StargateMapper("test")
       void test(@StargateBody HashMap message);
   }
   ```

2. 异步发送

   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("test")
       void test(@StargateBody HashMap message, SendCallback sendCallback);
   }
   ```

3. OneWay

   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("test")
       SendResult test(@StargateBody HashMap message);
   }
   ```

### 消费者

1. 消费者

   ```java
   @StargateConsumer
   public class TestConsumer {
       @StargateMapper("test")
       public void test(@StargateBody HashMap message, MessageExt messageExt, 		       
                        ConsumeConcurrentlyContext consumeConcurrentlyContext){
           //TODO
       }
   }
   ```

### 注解详解

+ StargateProducer			
  + *定义一个生产者*
  + `value:beanName`
+ StargateConsumer		
  + *定义一个消费者*
  + `value:beanName`
+ StargateMapper			
  + *定义topic&group*
  + `value:时topic&group等于本值，优先级低于topic&group`
  + `topic:即RocketMQ的topic`
  + `group:即RocketMQ的group`
  + `tag:即RocketMQ的tag`
+ StargateBody			
  + *标记参数中哪个是消息体*
+ StargateOneWay			
  + *以OneWay方式发送消息*
+ StargateFromWhere		
  + *设置消费者的ConsumeFromWhere*
  + `value:设置消费者的ConsumeFromWhere`
+ StargateEncod			
  + *设置生产者的编码器*
  + `value:设置编码器Class`
+ StargateDecode			
  + *设置消费者的解码器*
  + `value:设置解码器Class`
+ StargateDelayMsec			
  + *设置消息的延时毫秒数*
  + `value:设置消息的延时毫秒数`
+ StargateDelayLevel			
  + *设置消息的延时等级*
  + `value:设置消息的延时等级`
+ StargateThreadSize			
  + *设置消费者的线程池大小*
  + `value:设置消费者的线程池大小`
+ StargateNameServer			
  + *设置连接到哪个NameServer*
  + `value:NameServer名称`
+ StargateDelayParam			
  + *标记动态延时参数*
+ StargatePullBatchSize			
    + *配置每次拉取消息的数量*
  + `value:每次拉取消息的数量默认32`
+ StargateVipChannel			
    + *设置客户端VipChannel，3.x.x RocketMQ请设置false*
  + `value:设置客户端VipChannel false还是true`

### 特殊参数&返回

+ MessageExt
  + `RocketMQ SDK中的MessageExt类，如果消费者存在此参数，调用时会传入`
+ ConsumeConcurrentlyContext
  + `RocketMQ SDK中的ConsumeConcurrentlyContext类，如果消费者存在此参数，调用时会传入`
+ SendResult
  + `RocketMQ SDK中的SendResult，在同步发送时将会返回该类型对象`
+ SendCallback
  + `RocketMQ SDK中的SendCallback，生产者中存在参数时会异步发送并且回调接口`
  
### 使用Java原生的序列化方式

1. 全局配置

   ```yaml
   stargate:
     #默认的解码器类
     decodeClassName: com.yunpian.stargate.serialize.SerializeStargateClientDecode  
     #默认的编码器类
     encodClassName: com.yunpian.stargate.serialize.SerializeStargateClientEncode   
   ```

2. 局部配置

   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("testaaa")
       @StargateEncod(rocketClientEncod = SerializeRocketClientEncode.class)
       SendResult test(@StargateBody HashMap message);
   }
   ```

   ```java
   @StargateConsumer
   public class TestConsumer {
       @StargateMapper("testaaa")
       @StargateDecode(rocketClientDecode = SerializeRocketClientDecode.class)
       public void test(@StargateBody HashMap message, MessageExt messageExt, 		       
                        ConsumeConcurrentlyContext consumeConcurrentlyContext){
           //TODO
       }
   }
   ```
   
### 延时消息

+ 方法一

   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("testaaa")
       @StargateDelayLevel(2)//设置延时等级
       //10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 4h 6h 8h 10h 12h 24h 36h 48h 72h
       SendResult test(@StargateBody HashMap message);
   }
   ```
   
+ 方法二
  
   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("testaaa")
       @StargateDelayMsec(5000)//设置延时毫秒，自动向下取延时等级
       //设置6000时实际延时5000ms，超过2小时，延时2小时
       SendResult test(@StargateBody HashMap message);
   }
   ```
   
### 动态设置延时时间

   ```java
   @StargateProducer
   public interface TestProducter {
       @StargateMapper("testaaa")
       SendResult test(@StargateBody HashMap message, @StargateDelayParam long delayTime);
   }
   ```

### 消费者线程池配置

   ```java
   @StargateProducer
   @StargateThreadSize(3)
   public interface TestProducter {
       @StargateMapper("testaaa")
       SendResult test(@StargateBody HashMap message);
   }
   ```

### 多集群支持

   ```java
   @StargateProducer
   @StargateNameServer("yunpian")//指定集群
   public interface TestProducter {
       @StargateMapper("testaaa")
       SendResult test(@StargateBody HashMap message);
   }
   ```

   ```java
   @StargateConsumer
   @StargateNameServer("yunpian")//指定集群
   public class TestConsumer {
       @StargateMapper("testaaa")
       public void test(@StargateBody HashMap message, MessageExt messageExt, 		       
                        ConsumeConcurrentlyContext consumeConcurrentlyContext){
           //TODO
       }
   }
   ```

### 如何配置广播消息

   ```java
   @StargateConsumer
   public class TestConsumer {
       @StargateMapper("testaaa")
       @StargateBroadcasting
       //注意广播消费者只能接收StargateDelayLevel注解延时
       public void test(@StargateBody HashMap message, MessageExt messageExt, 		       
                        ConsumeConcurrentlyContext consumeConcurrentlyContext){
           //TODO
       }
   }
   ```

### 自定义功能模块

+ IProcessAnnotationConsume			
  + *消费者注解处理器*

+ IProcessAnnotationProducer			
  + *生产者注解处理器*

+ IProcessClientConsume			
  + *消费者Client处理器*

+ IProcessClientProducer			
  + *生产者Client处理器*

+ IProcessMessageConsume			
  + *消费者Message处理器*

+ IProcessMessageProducer			
  + *生产者Message处理器*

### 其他

+ 如有疑问，请提Issues
+ 欢迎参与开发，请提merge
