package com.yunpian.stargate.command.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IMQClientDataService;
import com.yunpian.stargate.dogrobber.dto.CommandHandleDTO;
import com.yunpian.stargate.dogrobber.dto.DataUploadDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:6:14 PM
 */
@Service
public class MQClientDataServiceImpl implements IMQClientDataService {

  private Cache<String, MQClientData> db = CacheBuilder.newBuilder()
    .initialCapacity(100)
    .concurrencyLevel(20)
    .expireAfterWrite(360, TimeUnit.SECONDS)
    .build();

  @Override
  public MQClientData add(MQClientData mqClientData) {
    if (mqClientData == null || mqClientData.getId() == null) {
      return null;
    }
    if (mqClientData.getTopic().contains(DataUploadDTO.TOPIC) ||
      mqClientData.getTopic().contains(CommandHandleDTO.TOPIC)) {
      return null;
    }
    db.put(mqClientData.getId(), mqClientData);
    return mqClientData;
  }

  @Override
  public List<MQClientData> list() {
    return new ArrayList<>(db.asMap().values());
  }

  @Override
  public MQClientData get(String id) {
    return db.getIfPresent(id);
  }

  @Override
  public void removeAll() {
    db.invalidateAll();
  }

}
