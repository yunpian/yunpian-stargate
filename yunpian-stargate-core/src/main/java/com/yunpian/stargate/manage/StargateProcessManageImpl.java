package com.yunpian.stargate.manage;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.StargateBaseDTO;
import com.yunpian.stargate.manage.dto.StargateProducerManageDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/12 Time:1:28 PM
 * 尴尬的打错了名字，发现的时候已经没法改了，StargateProducerManageImpl
 *
 * @since 1.4
 */
public class StargateProcessManageImpl implements IStargateProducerManageCore {

  private Map<String, StargateProducerManageDTO> stargateProducerManageDTOMap
    = new ConcurrentHashMap<String, StargateProducerManageDTO>();

  public void add(StargateProducerManageDTO stargateProducerManageDTO) {
    ProducerContext producerContext = stargateProducerManageDTO.getProducerContext();
    StargateBaseDTO stargateBaseDTO = producerContext.getDTO(StargateBaseDTO.class);
    String key = stargateBaseDTO.getId();
    stargateProducerManageDTO.setKey(key);
    stargateProducerManageDTOMap.put(key, stargateProducerManageDTO);
  }

  @Override
  public List<StargateProducerManageDTO> producerList() {
    return new ArrayList<>(stargateProducerManageDTOMap.values());
  }
}
