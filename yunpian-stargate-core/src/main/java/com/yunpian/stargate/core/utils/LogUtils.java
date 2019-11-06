package com.yunpian.stargate.core.utils;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/7/25 Time:2:20 PM
 */
public class LogUtils {

  private static final Logger log = LoggerFactory.getLogger(LogUtils.class);

  public static final void messageLog(MessageExt messageExt) {
    try {
      log.debug("\n{}\n{}{}\n{}\n{}\n{}",
        "==========================Consume message log start=========================",
        "time:", System.currentTimeMillis(),
        messageExt.toString(), new String(messageExt.getBody()),
        "===========================Consume message log end==========================");
    } catch (Throwable e) {
      log.error("Throwable:", e);
    }
  }

  public static final void messageLog(Message message) {
    try {
      log.debug("\n{}\n{}{}\n{}\n{}\n{}",
        "-------------------------Producer message log start-------------------------",
        "time:", System.currentTimeMillis(),
        message.toString(), new String(message.getBody()),
        "--------------------------Producer message log end--------------------------");
    } catch (Throwable e) {
      log.error("Throwable:", e);
    }

  }
}
