package com.yunpian.stargate.command.mq.producer;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateEncod;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.annotation.StargateProducer;
import com.yunpian.stargate.dogrobber.dto.CommandHandleDTO;
import com.yunpian.stargate.jackson.JacksonStargateClientEncode;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:5:35 PM
 */
@StargateProducer
public interface CommandSendProducer {

  @StargateMapper(CommandHandleDTO.TOPIC)
  @StargateEncod(JacksonStargateClientEncode.class)
  void send(@StargateBody CommandHandleDTO commandHandleDTO);

}

