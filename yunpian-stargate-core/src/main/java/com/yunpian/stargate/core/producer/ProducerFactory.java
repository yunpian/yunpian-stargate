package com.yunpian.stargate.core.producer;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.utils.ProcessID;
import com.yunpian.stargate.core.utils.StargateEnvironment;
import com.yunpian.stargate.core.utils.StringUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/26 Time:下午10:51
 */
public class ProducerFactory {

  private static final Map<String, DefaultMQProducer> producerMap = new ConcurrentHashMap<String, DefaultMQProducer>();
  private static final Object lock = new Object();

  public static DefaultMQProducer getProducer(ProducerContext producerContext)
    throws MQClientException {
    return getProducer(producerContext.getGroup(), producerContext.getNamesrvAddr(), false);
  }

  public static DefaultMQProducer getProducerAndStart(String group, String namesrvAddr)
    throws MQClientException {
    return getProducer(group, namesrvAddr, true);
  }

  public static DefaultMQProducer getProducer(String group, String namesrvAddr, boolean start)
    throws MQClientException {
    if (StringUtils.isBlank(group) || StringUtils.isBlank(namesrvAddr) ||
      StargateEnvironment.getNamesrv(namesrvAddr) == null) {
      throw new StargateException("nameServer or group is null");
    }
//    group = group + "-" + namesrvAddr;
    synchronized (lock) {
      DefaultMQProducer mqProducer = producerMap.get(group);
      if (mqProducer != null) {
        return mqProducer;
      }
      DefaultMQProducer producer = new DefaultMQProducer(group);
      producer.setNamesrvAddr(StargateEnvironment.getNamesrv(namesrvAddr));
      producer.setInstanceName(ProcessID.getPID() + "-" + namesrvAddr);
      producerMap.put(group, producer);
      if (start) {
        producer.start();
      }
      return producer;
    }
  }
}
