package com.yunpian.stargate.core.utils;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/27 Time:2:28 PM
 */
public class SortIndexComparator implements Comparator<ISortIndex> {

  @Override
  public int compare(ISortIndex sortIndex1, ISortIndex sortIndex2) {
    return sortIndex1.getIndex() - sortIndex2.getIndex();
  }

}
