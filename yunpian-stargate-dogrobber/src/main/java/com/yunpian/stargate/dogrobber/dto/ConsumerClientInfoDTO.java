package com.yunpian.stargate.dogrobber.dto;

import java.util.Map;
import org.apache.rocketmq.common.ServiceState;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/4 Time:5:44 PM
 */
public class ConsumerClientInfoDTO {

  private String consumerGroup;
  private MessageModel messageModel;
  private ConsumeFromWhere consumeFromWhere;
  private String consumeTimestamp;
  private Map<String, String> subscription;
  private int consumeThreadMin;
  private int consumeThreadMax;
  private long adjustThreadPoolNumsThreshold;
  private int consumeConcurrentlyMaxSpan;
  private int pullThresholdForQueue;
  private long pullInterval;
  private int consumeMessageBatchMaxSize;
  private int pullBatchSize;
  private boolean postSubscriptionWhenPull;
  private boolean unitMode;
  private String namesrvAddr;
  private String clientIP;
  private String instanceName;
  private int clientCallbackExecutorThreads;
  private int pollNameServerInteval;
  private int heartbeatBrokerInterval;
  private int persistConsumerOffsetInterval;
  private ServiceState serviceState;
  private boolean pause;

  public boolean isPause() {
    return pause;
  }

  public void setPause(boolean pause) {
    this.pause = pause;
  }

  public ServiceState getServiceState() {
    return serviceState;
  }

  public void setServiceState(ServiceState serviceState) {
    this.serviceState = serviceState;
  }

  public String getConsumerGroup() {
    return consumerGroup;
  }

  public void setConsumerGroup(String consumerGroup) {
    this.consumerGroup = consumerGroup;
  }

  public MessageModel getMessageModel() {
    return messageModel;
  }

  public void setMessageModel(MessageModel messageModel) {
    this.messageModel = messageModel;
  }

  public ConsumeFromWhere getConsumeFromWhere() {
    return consumeFromWhere;
  }

  public void setConsumeFromWhere(ConsumeFromWhere consumeFromWhere) {
    this.consumeFromWhere = consumeFromWhere;
  }

  public String getConsumeTimestamp() {
    return consumeTimestamp;
  }

  public void setConsumeTimestamp(String consumeTimestamp) {
    this.consumeTimestamp = consumeTimestamp;
  }

  public Map<String, String> getSubscription() {
    return subscription;
  }

  public void setSubscription(Map<String, String> subscription) {
    this.subscription = subscription;
  }

  public int getConsumeThreadMin() {
    return consumeThreadMin;
  }

  public void setConsumeThreadMin(int consumeThreadMin) {
    this.consumeThreadMin = consumeThreadMin;
  }

  public int getConsumeThreadMax() {
    return consumeThreadMax;
  }

  public void setConsumeThreadMax(int consumeThreadMax) {
    this.consumeThreadMax = consumeThreadMax;
  }

  public long getAdjustThreadPoolNumsThreshold() {
    return adjustThreadPoolNumsThreshold;
  }

  public void setAdjustThreadPoolNumsThreshold(long adjustThreadPoolNumsThreshold) {
    this.adjustThreadPoolNumsThreshold = adjustThreadPoolNumsThreshold;
  }

  public int getConsumeConcurrentlyMaxSpan() {
    return consumeConcurrentlyMaxSpan;
  }

  public void setConsumeConcurrentlyMaxSpan(int consumeConcurrentlyMaxSpan) {
    this.consumeConcurrentlyMaxSpan = consumeConcurrentlyMaxSpan;
  }

  public int getPullThresholdForQueue() {
    return pullThresholdForQueue;
  }

  public void setPullThresholdForQueue(int pullThresholdForQueue) {
    this.pullThresholdForQueue = pullThresholdForQueue;
  }

  public long getPullInterval() {
    return pullInterval;
  }

  public void setPullInterval(long pullInterval) {
    this.pullInterval = pullInterval;
  }

  public int getConsumeMessageBatchMaxSize() {
    return consumeMessageBatchMaxSize;
  }

  public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
    this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
  }

  public int getPullBatchSize() {
    return pullBatchSize;
  }

  public void setPullBatchSize(int pullBatchSize) {
    this.pullBatchSize = pullBatchSize;
  }

  public boolean isPostSubscriptionWhenPull() {
    return postSubscriptionWhenPull;
  }

  public void setPostSubscriptionWhenPull(boolean postSubscriptionWhenPull) {
    this.postSubscriptionWhenPull = postSubscriptionWhenPull;
  }

  public boolean isUnitMode() {
    return unitMode;
  }

  public void setUnitMode(boolean unitMode) {
    this.unitMode = unitMode;
  }

  public String getNamesrvAddr() {
    return namesrvAddr;
  }

  public void setNamesrvAddr(String namesrvAddr) {
    this.namesrvAddr = namesrvAddr;
  }

  public String getClientIP() {
    return clientIP;
  }

  public void setClientIP(String clientIP) {
    this.clientIP = clientIP;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public int getClientCallbackExecutorThreads() {
    return clientCallbackExecutorThreads;
  }

  public void setClientCallbackExecutorThreads(int clientCallbackExecutorThreads) {
    this.clientCallbackExecutorThreads = clientCallbackExecutorThreads;
  }

  public int getPollNameServerInteval() {
    return pollNameServerInteval;
  }

  public void setPollNameServerInteval(int pollNameServerInteval) {
    this.pollNameServerInteval = pollNameServerInteval;
  }

  public int getHeartbeatBrokerInterval() {
    return heartbeatBrokerInterval;
  }

  public void setHeartbeatBrokerInterval(int heartbeatBrokerInterval) {
    this.heartbeatBrokerInterval = heartbeatBrokerInterval;
  }

  public int getPersistConsumerOffsetInterval() {
    return persistConsumerOffsetInterval;
  }

  public void setPersistConsumerOffsetInterval(int persistConsumerOffsetInterval) {
    this.persistConsumerOffsetInterval = persistConsumerOffsetInterval;
  }
}
