package com.yunpian.stargate.springboot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/5 Time:6:47 PM
 */
@Component
@ConfigurationProperties("stargate")
public class StargateProperties {

  private Map<String, String> namesrvAddr = new ConcurrentHashMap<String, String>();
  private String namesrvDefault;
  private String decodeClassName;
  private String encodClassName;
  private Integer threadSize;
  private Boolean delayMsecSwitch;
  private String env;
  private Boolean vipChannel;
  private long[] delayLevel;
  private String appName;

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public long[] getDelayLevel() {
    return delayLevel;
  }

  public void setDelayLevel(long[] delayLevel) {
    this.delayLevel = delayLevel;
  }

  public Boolean getVipChannel() {
    return vipChannel;
  }

  public void setVipChannel(Boolean vipChannel) {
    this.vipChannel = vipChannel;
  }

  public String getNamesrvDefault() {
    return namesrvDefault;
  }

  public void setNamesrvDefault(String namesrvDefault) {
    this.namesrvDefault = namesrvDefault;
  }

  public Map<String, String> getNamesrvAddr() {
    return namesrvAddr;
  }

  public void setNamesrvAddr(Map<String, String> namesrvAddr) {
    this.namesrvAddr = namesrvAddr;
  }

  public String getDecodeClassName() {
    return decodeClassName;
  }

  public void setDecodeClassName(String decodeClassName) {
    this.decodeClassName = decodeClassName;
  }

  public String getEncodClassName() {
    return encodClassName;
  }

  public void setEncodClassName(String encodClassName) {
    this.encodClassName = encodClassName;
  }

  public Integer getThreadSize() {
    return threadSize;
  }

  public void setThreadSize(Integer threadSize) {
    this.threadSize = threadSize;
  }

  public Boolean getDelayMsecSwitch() {
    return delayMsecSwitch;
  }

  public void setDelayMsecSwitch(Boolean delayMsecSwitch) {
    this.delayMsecSwitch = delayMsecSwitch;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }
}
