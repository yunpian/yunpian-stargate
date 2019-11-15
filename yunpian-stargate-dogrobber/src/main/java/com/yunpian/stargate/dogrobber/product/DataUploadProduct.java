package com.yunpian.stargate.dogrobber.product;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateEncod;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.annotation.StargateProducer;
import com.yunpian.stargate.dogrobber.dto.DataUploadDTO;
import com.yunpian.stargate.jackson.JacksonStargateClientEncode;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:3:32 PM
 */
@StargateProducer("com-yunpian-stargate-dogrobber-product-DataUploadProduct")
public interface DataUploadProduct {

  @StargateMapper(DataUploadDTO.TOPIC)
  @StargateEncod(JacksonStargateClientEncode.class)
  void send(@StargateBody DataUploadDTO dataUploadDTO);
}
