package com.yunpian.stargate.serialize;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.process.IStargateClientEncod;
import com.yunpian.stargate.core.vo.StargateMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/29 Time:下午6:30
 */
public class SerializeStargateClientEncode implements IStargateClientEncod {

  private static final Logger log = LoggerFactory.getLogger(SerializeStargateClientEncode.class);

  @Override
  public byte[] encod(StargateMessage stargateMessage, ProducerContext producerContext) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = null;

    try {
      objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(stargateMessage);
      byte[] bytes = byteArrayOutputStream.toByteArray();
      return bytes;
    } catch (Throwable e) {
      log.error("SerializeRocketClientEncod error:", e);
    } finally {
      try {
        objectOutputStream.close();
      } catch (IOException e) {
        log.error("SerializeRocketClientEncod close error:", e);
      }

    }

    return null;
  }
}
