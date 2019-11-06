package com.yunpian.stargate.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.process.IStargateClientEncod;
import com.yunpian.stargate.core.vo.StargateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/29 Time:下午6:30
 */
public class JacksonStargateClientEncode implements IStargateClientEncod {

  private static Logger logger = LoggerFactory.getLogger(JacksonStargateClientEncode.class);
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] encod(StargateMessage stargateMessage, ProducerContext producerContext) {
    try {
      byte[] bytes = new byte[0];
      bytes = objectMapper.writeValueAsBytes(stargateMessage);
      return bytes;
    } catch (Throwable e) {
      logger.error("JacksonStargateClientEncode :", e);
      return null;
    }
  }
}
