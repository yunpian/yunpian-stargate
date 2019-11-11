package com.yunpian.stargate.command.dto;

import com.yunpian.stargate.dogrobber.dto.ConsumerClientInfoDTO;
import com.yunpian.stargate.dogrobber.dto.ProducerClientInfoDTO;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:5:47 PM
 */
public class MQClientData {

  private String id;
  private String[] tags;
  private String key;
  private String idCard;
  private String appName;
  private Date refreshTime;
  private int type;
  private Map<String, Object> data;
  private ConsumerClientInfoDTO consumerClientInfoDTO;
  private ProducerClientInfoDTO producerClientInfoDTO;
  private String group = "";
  private String topic = "";
  private String tag = "";

  public ConsumerClientInfoDTO getConsumerClientInfoDTO() {
    return consumerClientInfoDTO;
  }

  public void setConsumerClientInfoDTO(
    ConsumerClientInfoDTO consumerClientInfoDTO) {
    this.consumerClientInfoDTO = consumerClientInfoDTO;
  }

  public ProducerClientInfoDTO getProducerClientInfoDTO() {
    return producerClientInfoDTO;
  }

  public void setProducerClientInfoDTO(
    ProducerClientInfoDTO producerClientInfoDTO) {
    this.producerClientInfoDTO = producerClientInfoDTO;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Date getRefreshTime() {
    return refreshTime;
  }

  public void setRefreshTime(Date refreshTime) {
    this.refreshTime = refreshTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }
}
