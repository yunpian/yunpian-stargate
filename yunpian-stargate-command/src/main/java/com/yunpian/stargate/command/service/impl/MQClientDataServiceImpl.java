package com.yunpian.stargate.command.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yunpian.stargate.command.dto.MQClientData;
import com.yunpian.stargate.command.service.IMQClientDataService;
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
    //设置cache的初始大小为10，要合理设置该值
    .initialCapacity(10)
    //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
    .concurrencyLevel(5)
    //设置cache中的数据在写入之后的存活时间为10秒
    .expireAfterWrite(30, TimeUnit.SECONDS)
    //构建cache实例
    .build();

  @Override
  public MQClientData add(MQClientData mqClientData) {
    if (mqClientData == null || mqClientData.getId() == null) {
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

}
