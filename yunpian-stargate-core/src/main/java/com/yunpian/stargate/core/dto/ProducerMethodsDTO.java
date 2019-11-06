package com.yunpian.stargate.core.dto;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:2:52 PM
 */
public class ProducerMethodsDTO {

  private int parameterSize;
  private Method method;
  private Object object;
  private Class clazz;
  private int sendCallbackIndex = -1;
  private boolean valid = true;

  public int getSendCallbackIndex() {
    return sendCallbackIndex;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public void setSendCallbackIndex(int sendCallbackIndex) {
    this.sendCallbackIndex = sendCallbackIndex;
  }

  public int getParameterSize() {
    return parameterSize;
  }

  public void setParameterSize(int parameterSize) {
    this.parameterSize = parameterSize;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public Class getClazz() {
    return clazz;
  }

  public void setClazz(Class clazz) {
    this.clazz = clazz;
  }

  @Override
  public String toString() {
    return "ProducerMethodsDTO{" +
      "parameterSize=" + parameterSize +
      ", method=" + method +
      ", object=" + object +
      ", clazz=" + clazz +
      ", sendCallbackIndex=" + sendCallbackIndex +
      ", valid=" + valid +
      '}';
  }
}
