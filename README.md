# Stargate

### 本版本非正式版，如需要使用请等待1.0.0版本

### 简介

Stargate（星门）是一个RocketMQ的客户端，使用全注解的方式，可以很方便的与SpringBoot|Cloud整合，也可以独立使用，你可以在Maven中引入相关依赖

+ Spring Boot|Cloud 引入依赖

```xml
<dependency>
    <groupId>com.yunpian.stargate</groupId>
    <artifactId>yunpian-stargate-springboot</artifactId>
    <version>0.9.0-SNAPSHOT</version>
</dependency>
```

+ 性能测试
  + 发送组件耗时0.7ms（第一次发送200ms）
  + 加上rocketmq连接发送耗时1.6ms
  + 组件消费者耗时1ms

### 项目介绍

+ Stargate作为一个全注解的RocketMQ组件，极大的简化了使用和学习成本，设计上尽肯能的与Spring Cloud注解风格保持一致，并且支持自己开发插件增强功能。主要以简单、易用、易扩展为目的，降低对消息队列的使用成本。

+ Stargate提供6个接口分别用于生产者和消费者扩展注解，扩展rocketClient的处理，扩展消息的处理，通过这些接口用户可以制作自己的插件来实现定制需求。
+ [项目介绍](./introduction.md)

### 使用手册

+ [查看使用手册](./manual.md)

### 扩展手册

+ [查看扩展手册](./yunpian-stargate-sg1/README.md)-->请前往yunpianmvn-stargate-sg1项目

### 更新日志

+ [查看更新日志](./updateLog.md)

### 控制台（Stargate Command）
