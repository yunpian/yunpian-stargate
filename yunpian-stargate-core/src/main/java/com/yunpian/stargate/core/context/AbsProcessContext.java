package com.yunpian.stargate.core.context;

import com.yunpian.stargate.core.exception.StargateException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public abstract class AbsProcessContext<DTO> implements IProcessContext<DTO> {

  @Override
  public Class getKey() {
    Type type = getClass().getGenericSuperclass();
    ParameterizedType parameterizedType = (ParameterizedType) type;
    return (Class) parameterizedType.getActualTypeArguments()[0];
  }

  @Override
  public DTO getDTO() {
    Type type = getClass().getGenericSuperclass();
    ParameterizedType parameterizedType = (ParameterizedType) type;
    Type typeArgument = parameterizedType.getActualTypeArguments()[0];
    try {
      return (DTO) ((Class) typeArgument).newInstance();
    } catch (Exception e) {
      throw new StargateException("create DTO error", e);
    }
  }
}
