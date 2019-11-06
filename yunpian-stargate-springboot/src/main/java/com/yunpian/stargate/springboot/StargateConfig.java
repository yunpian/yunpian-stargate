package com.yunpian.stargate.springboot;

import com.yunpian.stargate.core.annotation.IProcessAnnotationConsume;
import com.yunpian.stargate.core.annotation.IProcessAnnotationProducer;
import com.yunpian.stargate.core.client.IProcessClientConsume;
import com.yunpian.stargate.core.client.IProcessClientProducer;
import com.yunpian.stargate.core.message.IProcessMessageConsume;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.serialize.SerializeStargateClientDecode;
import com.yunpian.stargate.serialize.SerializeStargateClientEncode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/5 Time:9:40 PM
 */
public class StargateConfig {

  private Map<String, String> namesrvAddr = new ConcurrentHashMap<>();
  private String namesrvDefault = "default";
  private String env;
  private int threadSize = 4;
  private boolean delayMsecSwitch = false;
  private boolean vipChannel = false;
  private Class decodeClass = SerializeStargateClientDecode.class;
  private Class encodClass = SerializeStargateClientEncode.class;

  private List<IProcessAnnotationConsume> processAnnotationConsumes = Collections
    .synchronizedList(new ArrayList<>());
  private List<IProcessAnnotationProducer> processAnnotationProducers = Collections
    .synchronizedList(new ArrayList<>());

  private List<IProcessClientConsume> processClientConsumes = Collections
    .synchronizedList(new ArrayList<>());
  private List<IProcessClientProducer> processClientProducers = Collections
    .synchronizedList(new ArrayList<>());

  private List<IProcessMessageConsume> processMessageConsumes = Collections
    .synchronizedList(new ArrayList<>());
  private List<IProcessMessageProducer> processMessageProducers = Collections
    .synchronizedList(new ArrayList<>());


  public boolean isVipChannel() {
    return vipChannel;
  }

  public void setVipChannel(boolean vipChannel) {
    this.vipChannel = vipChannel;
  }

  public void addProcessMessageConsume(IProcessMessageConsume processMessageConsume) {
    processMessageConsumes.add(processMessageConsume);
  }

  public void addProcessMessageProducer(IProcessMessageProducer processMessageProducer) {
    processMessageProducers.add(processMessageProducer);
  }

  public void addProcessClientConsume(IProcessClientConsume processClientConsume) {
    processClientConsumes.add(processClientConsume);
  }

  public void addProcessClientProducer(IProcessClientProducer processClientProducer) {
    processClientProducers.add(processClientProducer);
  }

  public void addProcessAnnotationConsume(IProcessAnnotationConsume processAnnotationConsume) {
    processAnnotationConsumes.add(processAnnotationConsume);
  }

  public void addProcessAnnotationProducer(IProcessAnnotationProducer processAnnotationProducer) {
    processAnnotationProducers.add(processAnnotationProducer);
  }


  public StargateConfig() {
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
    this.namesrvAddr = new ConcurrentHashMap<>(namesrvAddr);
  }

  public int getThreadSize() {
    return threadSize;
  }

  public void setThreadSize(int threadSize) {
    this.threadSize = threadSize;
  }

  public boolean isDelayMsecSwitch() {
    return delayMsecSwitch;
  }

  public void setDelayMsecSwitch(boolean delayMsecSwitch) {
    this.delayMsecSwitch = delayMsecSwitch;
  }

  public Class getDecodeClass() {
    return decodeClass;
  }

  public void setDecodeClass(Class decodeClass) {
    this.decodeClass = decodeClass;
  }

  public Class getEncodClass() {
    return encodClass;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public void setEncodClass(Class encodClass) {
    this.encodClass = encodClass;
  }

  public List<IProcessAnnotationConsume> getProcessAnnotationConsumes() {
    return processAnnotationConsumes;
  }

  public void setProcessAnnotationConsumes(
    List<IProcessAnnotationConsume> processAnnotationConsumes) {
    this.processAnnotationConsumes = processAnnotationConsumes;
  }

  public List<IProcessAnnotationProducer> getProcessAnnotationProducers() {
    return processAnnotationProducers;
  }

  public void setProcessAnnotationProducers(
    List<IProcessAnnotationProducer> processAnnotationProducers) {
    this.processAnnotationProducers = processAnnotationProducers;
  }

  public List<IProcessClientConsume> getProcessClientConsumes() {
    return processClientConsumes;
  }

  public void setProcessClientConsumes(
    List<IProcessClientConsume> processClientConsumes) {
    this.processClientConsumes = processClientConsumes;
  }

  public List<IProcessClientProducer> getProcessClientProducers() {
    return processClientProducers;
  }

  public void setProcessClientProducers(
    List<IProcessClientProducer> processClientProducers) {
    this.processClientProducers = processClientProducers;
  }

  public List<IProcessMessageConsume> getProcessMessageConsumes() {
    return processMessageConsumes;
  }

  public void setProcessMessageConsumes(
    List<IProcessMessageConsume> processMessageConsumes) {
    this.processMessageConsumes = processMessageConsumes;
  }

  public List<IProcessMessageProducer> getProcessMessageProducers() {
    return processMessageProducers;
  }

  public void setProcessMessageProducers(
    List<IProcessMessageProducer> processMessageProducers) {
    this.processMessageProducers = processMessageProducers;
  }
}
