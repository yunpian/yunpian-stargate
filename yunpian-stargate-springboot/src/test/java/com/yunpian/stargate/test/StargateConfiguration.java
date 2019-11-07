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

  public final static String ENV = "auto-test74356";

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
    stargateConfig.setDelayLevel(new long[]{10000, 30000, 60000, 120000, 180000, 240000,
      300000, 360000, 420000, 480000, 540000, 600000, 1200000, 1800000, 3600000, 7200000, 14400000,
      21600000, 28800000, 36000000, 43200000, 86400000, 129600000, 172800000, 259200000});

    return stargateConfig;
  }
}
