package com.yunpian.stargate.core.context;

import com.yunpian.stargate.core.dto.StargateMapperDTO;
import com.yunpian.stargate.core.dto.StargateNameServerDTO;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:10:28 AM
 */
public abstract class StargateContext implements Cloneable {

  Logger logger = LoggerFactory.getLogger(StargateContext.class);

  protected final Map<Class, Object> data = new ConcurrentHashMap<>();

  public <T> T getDTO(Class<T> aClass) {
    return (T) data.get(aClass);
  }

  public Object getDTO(IProcessContext processDataKey) {
    return data.get(processDataKey.getKey());
  }

  public Object getDTOOrNew(IProcessContext processContext) {
    Object dto = data.get(processContext.getKey());
    if (dto == null) {
      dto = processContext.getDTO();
      data.put(processContext.getKey(), dto);
    }
    return dto;
  }

  public <T> T getDTOOrNew(Class<T> aClass) {
    Object dto = data.get(aClass);
    if (dto == null) {
      try {
        dto = aClass.newInstance();
      } catch (Exception e) {
        logger.error("Exception:", e);
      }
      data.put(aClass, dto);
    }
    return (T) dto;
  }

  public abstract boolean isValid();

  public String getNamesrvAddr() {
    StargateNameServerDTO stargateNameServerDTO = (StargateNameServerDTO) data
      .get(StargateNameServerDTO.class);
    return stargateNameServerDTO.getNamesrvAddr();
  }

  public String getGroup() {
    StargateMapperDTO stargateMapperDTO = (StargateMapperDTO) data
      .get(StargateMapperDTO.class);
    return stargateMapperDTO.getGroup();
  }

  public String getTopic() {
    StargateMapperDTO stargateMapperDTO = (StargateMapperDTO) data
      .get(StargateMapperDTO.class);
    return stargateMapperDTO.getTopic();
  }

  public String getTag() {
    StargateMapperDTO stargateMapperDTO = (StargateMapperDTO) data
      .get(StargateMapperDTO.class);
    return stargateMapperDTO.getTag();
  }

  public Map<Class, Object> getData() {
    return this.data;
  }
}
