package com.yunpian.stargate.test;

import com.yunpian.stargate.jackson.JacksonStargateClientDecode;
import com.yunpian.stargate.jackson.JacksonStargateClientEncode;
import com.yunpian.stargate.springboot.StargateConfig;
import com.yunpian.stargate.test.plusmodel.TestAnnotation;
import com.yunpian.stargate.test.plusmodel.TestMessage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/18 Time:1:24 PM
 */

@Configuration
public class StargateConfiguration {

  public final static String ENV = "auto-test756";

  //配置后yml文件中的配置将失效
  @Bean
  public StargateConfig stargateConfig() {
    StargateConfig stargateConfig = new StargateConfig();
    stargateConfig.setThreadSize(6);
    stargateConfig.setNamesrvDefault("yunpian");
    Map<String, String> nameServer = new ConcurrentHashMap<>();
    nameServer.put("yunpian", "172.16.24.197:9876");
    stargateConfig.setNamesrvAddr(nameServer);
    stargateConfig.setEnv(ENV);
    stargateConfig.setVipChannel(false);
    stargateConfig.setEncodClass(JacksonStargateClientEncode.class);
    stargateConfig.setDecodeClass(JacksonStargateClientDecode.class);

//    stargateConfig.addProcessMessageConsume();
    stargateConfig.addProcessMessageProducer(new TestMessage());

//    stargateConfig.addProcessClientConsume();
//    stargateConfig.addProcessClientProducer();

//    stargateConfig.addProcessAnnotationConsume();
    stargateConfig.addProcessAnnotationProducer(new TestAnnotation());

    return stargateConfig;
  }
}
