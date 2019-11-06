package com.yunpian.stargate.core.dto;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/22 Time:2:52 PM
 */
public class ConsumeMethodsDTO {

  private int parameterSize;
  private Method method;
  private Object object;
  private Class clazz;
  private int contextIndex = -1;
  private int messageExtIndex = -1;
  private boolean valid = true;

  public int getContextIndex() {
    return contextIndex;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public void setContextIndex(int contextIndex) {
    this.contextIndex = contextIndex;
  }

  public int getMessageExtIndex() {
    return messageExtIndex;
  }

  public void setMessageExtIndex(int messageExtIndex) {
    this.messageExtIndex = messageExtIndex;
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
    return "ConsumeMethodsDTO{" +
      "parameterSize=" + parameterSize +
      ", method=" + method +
      ", object=" + object +
      ", clazz=" + clazz +
      ", contextIndex=" + contextIndex +
      ", messageExtIndex=" + messageExtIndex +
      ", valid=" + valid +
      '}';
  }
}
