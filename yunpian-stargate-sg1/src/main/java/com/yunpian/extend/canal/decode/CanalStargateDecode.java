package com.yunpian.extend.canal.decode;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpian.extend.canal.CanalMessage;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.dto.StargateBodyDTO;
import com.yunpian.stargate.core.process.IStargateClientDecode;
import com.yunpian.stargate.core.vo.StargateMessage;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liunan
 * @date by 2019/10/30 1:35 下午
 */
public class CanalStargateDecode implements IStargateClientDecode {


  private static Logger logger = LoggerFactory.getLogger(CanalStargateDecode.class);
  private static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    objectMapper.setDateFormat(sqlDateFormat);
  }

  @Override
  public StargateMessage decode(byte[] data, ConsumeContext consumeContext) {
    try {
      ConsumeMethodsDTO consumeMethodsDTO = consumeContext.getDTO(ConsumeMethodsDTO.class);
      StargateBodyDTO stargateBodyDTO = consumeContext.getDTO(StargateBodyDTO.class);
      JavaType javaTypePar = null;
      if (stargateBodyDTO.getBodyIndex() >= 0) {
        Type type = consumeMethodsDTO.getMethod().getGenericParameterTypes()[stargateBodyDTO
          .getBodyIndex()];
        javaTypePar = objectMapper.getTypeFactory().constructType(type);
      }
      CanalMessage cm = objectMapper.readValue(data, javaTypePar);
      logger.debug("CanalMessage:{}", cm);
      StargateMessage<CanalMessage> stargateMessage = new StargateMessage<>();
      stargateMessage.setBody(cm);
      return stargateMessage;

    } catch (Exception e) {
      logger.error("YunpianStargateClientDecode close error:", e);
    }
    return null;
  }
}
