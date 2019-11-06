package com.yunpian.stargate.test.testbus2;

import com.yunpian.extend.bus.StargateMsgBusAnnotationHandel;
import com.yunpian.extend.demo.DemoAnnotation;
import com.yunpian.extend.demo.DemoMessage;
import com.yunpian.stargate.jackson.JacksonStargateClientDecode;
import com.yunpian.stargate.jackson.JacksonStargateClientEncode;
import com.yunpian.stargate.springboot.StargateConfig;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/18 Time:1:24 PM
 */

@Configuration
public class StargateConfiguration {

  private static final String appName = "testbus1";

  //配置后yml文件中的配置将失效
  @Bean
  public StargateConfig stargateConfig() {
    StargateConfig stargateConfig = new StargateConfig();
    stargateConfig.setThreadSize(6);
    stargateConfig.setNamesrvDefault("yunpian");
    Map<String, String> nameServer = new ConcurrentHashMap<>();
    nameServer.put("yunpian", "127.0.0.1:9876");
    stargateConfig.setNamesrvAddr(nameServer);
    stargateConfig.setEnv("test2");
    stargateConfig.setEncodClass(JacksonStargateClientEncode.class);
    stargateConfig.setDecodeClass(JacksonStargateClientDecode.class);

//    stargateConfig.addProcessMessageConsume();
    stargateConfig.addProcessMessageProducer(new DemoMessage());

//    stargateConfig.addProcessClientConsume();
//    stargateConfig.addProcessClientProducer();

    stargateConfig.addProcessAnnotationConsume(new StargateMsgBusAnnotationHandel(appName));
    stargateConfig.addProcessAnnotationProducer(new StargateMsgBusAnnotationHandel(appName));
    stargateConfig.addProcessAnnotationProducer(new DemoAnnotation());

    return stargateConfig;
  }
}
