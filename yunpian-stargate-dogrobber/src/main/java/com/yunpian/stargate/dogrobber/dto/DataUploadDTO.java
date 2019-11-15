package com.yunpian.stargate.dogrobber.dto;

import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:3:34 PM
 */
public class DataUploadDTO {

  public static final String TOPIC = "STARGATE_COMMAND_DATA_UPLOAD_GI73JN29G34HJ78F";
  private String id;
  private String[] tags;
  private String key;
  private String idCard;
  private String appName;
  private int type;
  private Map<String, Object> data;
  private ConsumerClientInfoDTO consumerClientInfoDTO;
  private ProducerClientInfoDTO producerClientInfoDTO;

  private String group = "";
  private String topic = "";
  private String tag = "";

  public DataUploadDTO() {
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

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
}
