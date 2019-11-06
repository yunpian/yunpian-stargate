package com.yunpian.extend.tagparam;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class TagParamMessage implements IProcessMessageProducer {

  @Override
  public int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable {
    //DemoModel在上下文中的数据
    TagParamDTO tagParamDTO = producerContext.getDTO(TagParamDTO.class);
    if (tagParamDTO.getIndex() < 0) {
      //返回SUCCESS继续解析其他处理器
      return SUCCESS;
    }
    message.setTags(String.valueOf(args[tagParamDTO.getIndex()]));
    return SUCCESS;
  }
}
