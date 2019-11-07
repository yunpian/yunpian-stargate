package com.yunpian.stargate.core.utils;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/4 Time:8:19 PM
 */
public class DelayUtils {

  public static long[] delayLevel = new long[]{0, 1000, 5000, 10000, 30000, 60000, 120000, 180000,
    240000, 300000, 360000, 420000, 480000, 540000, 600000, 1200000, 1800000, 3600000, 7200000};
//  public static long[] delayLevel = new long[]{0, 10000, 30000, 60000, 120000, 180000, 240000,
//    300000, 360000, 420000, 480000, 540000, 600000, 1200000, 1800000, 3600000, 7200000, 14400000,
//    21600000, 28800000, 36000000, 43200000, 86400000, 129600000, 172800000, 259200000};

  public static int getMaxDelayLevelBySec(long sec) {
    if (sec <= 0) {
      return 0;
    }
    if (sec <= delayLevel[1]) {
      return 1;
    }
    for (int i = 0; i < delayLevel.length; ++i) {
      if (sec > delayLevel[i]) {
        continue;
      } else if (sec == delayLevel[i]) {
        return i;
      } else {
        return i - 1;
      }
    }
    return delayLevel.length - 1;
  }
}
