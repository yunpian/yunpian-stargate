package com.yunpian.stargate.core.message;

import com.yunpian.stargate.core.context.ConsumeContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessMessageConsume {

  //正常继续
  int SUCCESS = 0;
  //不理会后面的处理器直接消费消息
  int CONSUME = 1;
  //中断处理
  int BREAK = 2;

  int processConsume(ConsumeContext consumeContext, MessageExt messageExt,
    ConsumeConcurrentlyContext consumeConcurrentlyContext, Object[] args)
    throws Throwable;
}
