package com.yunpian.stargate.command.utils;

import java.io.Serializable;

/**
 * @author zhoukaifan
 */

public class Click<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  private T data;
  private int code;
  private long timestamp;
  private String msg;
  private Throwable throwable;

  protected Click() {
  }

  public static <T> Click<T> buildSucc(T data) {
    Click<T> resp = new Click();
    resp.setData(data);
    resp.setCode(Click.Code.SUCCESS.code());
    resp.setMsg(Click.Code.SUCCESS.msg());
    resp.setTimestamp(System.currentTimeMillis());
    return resp;
  }

  public static <T> Click<T> buildSucc() {
    return (Click<T>) buildSucc((Object) null);
  }

  public static <T> Click<T> buildFaild(int code, T data, String msg, Throwable e) {
    Click<T> resp = new Click();
    resp.setCode(code);
    resp.setData(data);
    resp.setMsg(msg);
    resp.setThrowable(e);
    resp.setTimestamp(System.currentTimeMillis());
    return resp;
  }

  public static <T> Click<T> buildFaild(int code, String msg, Throwable e) {
    return (Click<T>) buildFaild(code, (Object) null, msg, e);
  }

  public static <T> Click<T> buildFaild(Click.Code code, String msg, Throwable e) {
    return buildFaild(code.code(), msg, e);
  }

  public static <T> Click<T> buildFaild(Click.Code code, String msg) {
    return buildFaild(code, msg, (Throwable) null);
  }

  public static <T> Click<T> buildFaild(Click.Code code, Throwable e) {
    return buildFaild(code, code.msg(), e);
  }

  public static <T> Click<T> buildFaild(Click.Code code) {
    return buildFaild(code, code.msg(), (Throwable) null);
  }

  public static <T> Click<T> buildFaild(int code, String msg) {
    return buildFaild(code, msg, (Throwable) null);
  }

  public static <T> Click<T> buildByClick(Click<?> click, Serializable data) {
    Click click1 = new Click();
    click1.setCode(click.getCode());
    click1.setThrowable(click.getThrowable());
    click1.setData(data);
    click1.setMsg(click.getMsg());
    click1.setTimestamp(click.getTimestamp());
    return click1;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getMsg() {
    return this.msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public Throwable getThrowable() {
    return this.throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }

  public boolean succ() {
    return this.code == Click.Code.SUCCESS.code();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    } else if (!(obj instanceof Click)) {
      return false;
    } else if (obj == this) {
      return true;
    } else {
      return this.code == ((Click) obj).getCode();
    }
  }

  public boolean equalsByCode(Click.Code code) {
    if (code == null) {
      return false;
    } else {
      return this.code == code.code();
    }
  }

  @Override
  public String toString() {
    return "Click{data=" + this.data + ", code=" + this.code + ", timestamp=" + this.timestamp
      + ", msg='" + this.msg + '\'' + ", throwable=" + this.throwable + '}';
  }

  public static enum Code {
    UNKNOWN(-1, "Code is unknown"),
    ERROR_500(500, "Request is error:500"),
    ERROR_404(404, "Request is error:404"),
    ERROR_400(400, "Request is error:400"),
    ERROR(2, "Request is error"),
    FAIL_DAO(1003, "Request is fail:DAOException"),
    FAIL_TIMEOUT(1002, "Request is fail:TIMEOUT"),
    FAIL_NULL(1001, "Request is fail:NULL"),
    FAIL(1, "Request is fail"),
    BAD_ARGS(8001, "bad args:"),
    SUCCESS(0, "SUCCESS");

    private int code;
    private String msg;

    private Code(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public int code() {
      return this.code;
    }

    public String msg() {
      return this.msg;
    }

    private Click.Code getCode(int code) {
      Click.Code[] var2 = values();
      int var3 = var2.length;

      for (int var4 = 0; var4 < var3; ++var4) {
        Click.Code code1 = var2[var4];
        if (code1.code() == code) {
          return code1;
        }
      }

      return UNKNOWN;
    }
  }
}
