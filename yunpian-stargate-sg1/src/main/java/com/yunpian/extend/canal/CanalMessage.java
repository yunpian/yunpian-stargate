package com.yunpian.extend.canal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liunan
 * @date by 2019/11/1 10:50 上午
 */
public class CanalMessage<T> {

  private List<T> data = new ArrayList<>();
  private String type;
  private Boolean isDdl;
  /**
   * 执行时间
   */
  private Long es;
  /**
   * 日志时间
   */
  private Long ts;
  private String sql;

  public List<T> getData() {
    return data;
  }

  public CanalMessage<T> setData(List<T> data) {
    this.data = data;
    return this;
  }

  public String getType() {
    return type;
  }

  public CanalMessage<T> setType(String type) {
    this.type = type;
    return this;
  }

  public Boolean getDdl() {
    return isDdl;
  }

  public CanalMessage<T> setDdl(Boolean ddl) {
    isDdl = ddl;
    return this;
  }

  public Long getEs() {
    return es;
  }

  public CanalMessage<T> setEs(Long es) {
    this.es = es;
    return this;
  }

  public Long getTs() {
    return ts;
  }

  public CanalMessage<T> setTs(Long ts) {
    this.ts = ts;
    return this;
  }

  public String getSql() {
    return sql;
  }

  public CanalMessage<T> setSql(String sql) {
    this.sql = sql;
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CanalMessage{");
    sb.append("data=").append(data);
    sb.append(", type='").append(type).append('\'');
    sb.append(", isDdl=").append(isDdl);
    sb.append(", es=").append(es);
    sb.append(", ts=").append(ts);
    sb.append(", sql='").append(sql).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
