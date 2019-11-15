package com.yunpian.stargate.command.service;

import com.yunpian.stargate.command.dto.MQClientData;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:6:13 PM
 */
public interface IMQClientDataService {

  MQClientData add(MQClientData mqClientData);

  List<MQClientData> list();

  MQClientData get(String id);

  void removeAll();
}
