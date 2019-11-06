package com.yunpian.stargate.serialize;

import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.process.IStargateClientDecode;
import com.yunpian.stargate.core.vo.StargateMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/29 Time:下午6:30
 */
public class SerializeStargateClientDecode implements IStargateClientDecode {

  private static final Logger log = LoggerFactory.getLogger(SerializeStargateClientDecode.class);

  @Override
  public StargateMessage decode(byte[] data, ConsumeContext consumeContext) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
    ObjectInputStream objectInputStream = null;

    try {
      objectInputStream = new ObjectInputStream(byteArrayInputStream);
      StargateMessage stargateMessage = (StargateMessage) objectInputStream.readObject();
      return stargateMessage;
    } catch (Throwable e) {
      log.error("SerializeStargateClientDecode error:", e);
    } finally {
      try {
        objectInputStream.close();
      } catch (IOException e) {
        log.error("SerializeStargateClientDecode close error:", e);
      }

    }
    return null;
  }

}
