package com.yunpian.extend.topicformat;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/24 Time:5:53 PM
 */
public class StargateTopicFormatProcessMessageProducer implements IProcessMessageProducer {

  @Override
  public int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable {

    StargateTopicFormatDTO stargateTopicFormatDTO = producerContext
      .getDTOOrNew(StargateTopicFormatDTO.class);
    String topic = message.getTopic();
    for (String key : stargateTopicFormatDTO.keySet()) {
      topic = topic.replaceAll("#\\{" + key + "}",
        String.valueOf(args[stargateTopicFormatDTO.get(key)]));
    }
    message.setTopic(topic);
    return SUCCESS;
  }
}
