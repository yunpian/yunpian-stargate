package com.yunpian.stargate.core.consume;

import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.exception.StargateGroupExistException;
import com.yunpian.stargate.core.utils.ProcessID;
import com.yunpian.stargate.core.utils.StargateEnvironment;
import com.yunpian.stargate.core.utils.StringUtils;
import com.yunpian.stargate.manage.StargateCoreManageInstance;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/9/27 Time:上午9:35
 */
public class ConsumeFactory {

  private static final Object lock = new Object();
  private static final Logger log = LoggerFactory.getLogger(ConsumeFactory.class);

  public static DefaultMQPushConsumer getConsume(ConsumeContext consumeContext) {
    synchronized (lock) {

      String nameServer = consumeContext.getNamesrvAddr();
      String group = consumeContext.getGroup();
//      group = group + "-" + nameServer;

      if (StringUtils.isBlank(nameServer) || StringUtils.isBlank(nameServer) ||
        StargateEnvironment.getNamesrv(nameServer) == null) {
        throw new StargateException("nameServer or group is null");
      }

      MQPushConsumer pushConsumer = StargateCoreManageInstance.getStargateConsumeCoreManage()
        .getConsumeByGroup(group);
      if (pushConsumer != null) {
        //Consumer只能有一个处理方法所以禁止创建两次
        throw new StargateGroupExistException();
      }

      DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
      consumer.setNamesrvAddr(StargateEnvironment.getNamesrv(nameServer));
      consumer.setInstanceName(ProcessID.getPID() + "-" + nameServer);

      return consumer;
    }
  }

  public static boolean startMQConsumer(String key) {
    log.info("startMQConsumer:" + key);
    return startMQConsumer(
      StargateCoreManageInstance.getStargateConsumeCoreManage().getConsumeByGroup(key));
  }

  public static boolean startMQConsumer(DefaultMQPushConsumer mqPushConsumer) {
    if (mqPushConsumer == null) {
      return false;
    }
    try {
      mqPushConsumer.start();
    } catch (MQClientException e) {
      e.printStackTrace();
      log.error("MQClientException by startMQConsumer:{}", mqPushConsumer.getConsumerGroup(), e);
      return false;
    }
    log.info("startMQConsumer success:{}", mqPushConsumer.getConsumerGroup());
    return true;
  }

  public static void registerMessageListener(MQConsumer mqConsumer,
    MessageListener messageListener) {
    MQPushConsumer mqPushConsumer = (MQPushConsumer) mqConsumer;
    mqPushConsumer.registerMessageListener(messageListener);
  }

}
