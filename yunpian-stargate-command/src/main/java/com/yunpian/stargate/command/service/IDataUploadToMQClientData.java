package com.yunpian.stargate.command.service;

import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.dogrobber.dto.DataUploadDTO;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:5:47 PM
 */
public interface IDataUploadToMQClientData {

  MQClientData handle(DataUploadDTO dataUploadDTO);
}
