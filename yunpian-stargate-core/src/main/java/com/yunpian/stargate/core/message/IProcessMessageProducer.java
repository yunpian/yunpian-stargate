package com.yunpian.stargate.core.message;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.utils.ISortIndex;
import org.apache.rocketmq.common.message.Message;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:14 PM
 */
public interface IProcessMessageProducer extends ISortIndex {

  //正常继续
  int SUCCESS = 0;
  //不理会后面的处理器直接发送消息
  int SEND = 1;
  //中断发送
  int BREAK = 2;

  int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable;

}
