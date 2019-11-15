package com.yunpian.stargate.dogrobber.dto;

import org.apache.rocketmq.common.ServiceState;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/4 Time:5:48 PM
 */
public class ProducerClientInfoDTO {

  private String producerGroup;
  private String createTopicKey;
  private int defaultTopicQueueNums;
  private int sendMsgTimeout;
  private int compressMsgBodyOverHowmuch;
  private int retryTimesWhenSendFailed;
  private boolean retryAnotherBrokerWhenNotStoreOK;
  private int maxMessageSize;
  private boolean unitMode;
  private String namesrvAddr;
  private String clientIP;
  private String instanceName;
  private int clientCallbackExecutorThreads;
  private int pollNameServerInteval;
  private int heartbeatBrokerInterval;
  private int persistConsumerOffsetInterval;
  private ServiceState serviceState;

  public ServiceState getServiceState() {
    return serviceState;
  }

  public void setServiceState(ServiceState serviceState) {
    this.serviceState = serviceState;
  }

  public String getProducerGroup() {
    return producerGroup;
  }

  public void setProducerGroup(String producerGroup) {
    this.producerGroup = producerGroup;
  }

  public String getCreateTopicKey() {
    return createTopicKey;
  }

  public void setCreateTopicKey(String createTopicKey) {
    this.createTopicKey = createTopicKey;
  }

  public int getDefaultTopicQueueNums() {
    return defaultTopicQueueNums;
  }

  public void setDefaultTopicQueueNums(int defaultTopicQueueNums) {
    this.defaultTopicQueueNums = defaultTopicQueueNums;
  }

  public int getSendMsgTimeout() {
    return sendMsgTimeout;
  }

  public void setSendMsgTimeout(int sendMsgTimeout) {
    this.sendMsgTimeout = sendMsgTimeout;
  }

  public int getCompressMsgBodyOverHowmuch() {
    return compressMsgBodyOverHowmuch;
  }

  public void setCompressMsgBodyOverHowmuch(int compressMsgBodyOverHowmuch) {
    this.compressMsgBodyOverHowmuch = compressMsgBodyOverHowmuch;
  }

  public int getRetryTimesWhenSendFailed() {
    return retryTimesWhenSendFailed;
  }

  public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
    this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
  }

  public boolean isRetryAnotherBrokerWhenNotStoreOK() {
    return retryAnotherBrokerWhenNotStoreOK;
  }

  public void setRetryAnotherBrokerWhenNotStoreOK(boolean retryAnotherBrokerWhenNotStoreOK) {
    this.retryAnotherBrokerWhenNotStoreOK = retryAnotherBrokerWhenNotStoreOK;
  }

  public int getMaxMessageSize() {
    return maxMessageSize;
  }

  public void setMaxMessageSize(int maxMessageSize) {
    this.maxMessageSize = maxMessageSize;
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
