package com.yunpian.stargate.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.dto.StargateBodyDTO;
import com.yunpian.stargate.core.process.IStargateClientDecode;
import com.yunpian.stargate.core.vo.StargateMessage;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/29 Time:下午6:30
 */
public class JacksonStargateClientDecode implements IStargateClientDecode {

  private static Logger logger = LoggerFactory.getLogger(JacksonStargateClientDecode.class);
  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public StargateMessage decode(byte[] data, ConsumeContext consumeContext) {
    try {
      String dataStr = new String(data);
      JavaType javaTypePar = null;

      ConsumeMethodsDTO consumeMethodsDTO = consumeContext.getDTO(ConsumeMethodsDTO.class);
      StargateBodyDTO stargateBodyDTO = consumeContext.getDTO(StargateBodyDTO.class);

      if (stargateBodyDTO.getBodyIndex() >= 0) {
        Type type = consumeMethodsDTO.getMethod().getGenericParameterTypes()[stargateBodyDTO
          .getBodyIndex()];
        javaTypePar = objectMapper.getTypeFactory().constructType(type);
      }
      JavaType javaType = null;
      if (javaTypePar != null) {
        javaType = objectMapper.getTypeFactory()
          .constructParametricType(StargateMessage.class, javaTypePar);
      } else {
        javaType = objectMapper.getTypeFactory().constructType(StargateMessage.class);
      }
      StargateMessage stargateMessage = objectMapper.readValue(dataStr, javaType);
      return stargateMessage;
    } catch (Throwable e) {
      logger.error("JacksonStargateClientDecode :", e);
      return null;
    }
  }
}
