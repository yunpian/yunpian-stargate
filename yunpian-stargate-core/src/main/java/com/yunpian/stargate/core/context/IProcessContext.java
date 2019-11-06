package com.yunpian.stargate.core.context;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessContext<DTO> {

  Class getKey();

  DTO getDTO();
}
