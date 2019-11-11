package com.yunpian.stargate.command.config;

import com.yunpian.stargate.core.utils.StringUtils;
import com.yunpian.stargate.jackson.JacksonStargateClientDecode;
import com.yunpian.stargate.jackson.JacksonStargateClientEncode;
import com.yunpian.stargate.springboot.StargateConfig;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoukaifan
 */

@Configuration
public class StargateConfiguration {

  private static String PREFIX = null;

  static {
    String prefix = System.getProperty("qipeng.server.group");
    if (!StringUtils.isBlank(prefix)) {
      PREFIX = prefix;
    }
  }

  /**
   * 配置后yml文件中的配置将失效
   */
  @Bean
  public StargateConfig stargateConfig(
    @Value("${rocket.yp-rocket}") String yunpianName,
    @Value("${spring.application.name}") String appName) {
    StargateConfig stargateConfig = new StargateConfig();
    stargateConfig.setThreadSize(6);
    stargateConfig.setNamesrvDefault("yunpian");
    Map<String, String> nameServer = new ConcurrentHashMap<>();
    nameServer.put("yunpian", yunpianName);
    stargateConfig.setNamesrvAddr(nameServer);
    stargateConfig.setEnv("zkf");
    stargateConfig.setEncodClass(JacksonStargateClientEncode.class);
    stargateConfig.setDecodeClass(JacksonStargateClientDecode.class);
    return stargateConfig;
  }
}
