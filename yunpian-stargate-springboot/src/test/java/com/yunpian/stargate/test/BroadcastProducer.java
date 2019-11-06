package com.yunpian.stargate.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class BroadcastProducer {

  public static void main(String[] args) throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
    producer.setNamesrvAddr("127.0.0.1:9876");
    producer.start();

    for (int i = 0; i < 100; i++) {
      Message msg = new Message("TopicTest",
        "TagA",
        "OrderID188",
        "Hello world".getBytes("UTF-8"));
      SendResult sendResult = producer.send(msg);
      System.out.printf("%s%n", sendResult);
    }
    producer.shutdown();
  }
}