package com.yunpian.stargate.command.mq.consumer;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateBroadcasting;
import com.yunpian.stargate.annotation.StargateConsumer;
import com.yunpian.stargate.annotation.StargateDecode;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IDataUploadToMQClientData;
import com.yunpian.stargate.command.service.IMQClientDataService;
import com.yunpian.stargate.dogrobber.dto.DataUploadDTO;
import com.yunpian.stargate.jackson.JacksonStargateClientDecode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:4:24 PM
 */
@StargateConsumer
public class DataUploadConsumer {

  @Autowired
  private IDataUploadToMQClientData dataUploadToMQClientData;
  @Autowired
  private IMQClientDataService clientDataService;

  @StargateMapper(DataUploadDTO.TOPIC)
  @StargateBroadcasting
  @StargateDecode(JacksonStargateClientDecode.class)
  public void handle(@StargateBody DataUploadDTO dataUploadDTO) {
    MQClientData mqClientData = dataUploadToMQClientData.handle(dataUploadDTO);
    clientDataService.add(mqClientData);
  }

}
