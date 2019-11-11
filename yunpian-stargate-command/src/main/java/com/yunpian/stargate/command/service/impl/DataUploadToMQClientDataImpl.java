package com.yunpian.stargate.command.service.impl;

import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IDataUploadToMQClientData;
import com.yunpian.stargate.dogrobber.dto.DataUploadDTO;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:5:48 PM
 */
@Service
public class DataUploadToMQClientDataImpl implements IDataUploadToMQClientData {

  @Override
  public MQClientData handle(DataUploadDTO dataUploadDTO) {
    MQClientData mqClientData = new MQClientData();
    BeanUtils.copyProperties(dataUploadDTO, mqClientData);
    mqClientData.setRefreshTime(new Date());
    return mqClientData;
  }
}
