package com.yunpian.extend.demo;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.StargateEncodDTO;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.core.utils.StringUtils;
import com.yunpian.stargate.core.vo.StargateMessage;
import org.apache.rocketmq.common.message.Message;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class DemoMessage implements IProcessMessageProducer {

  @Override
  public int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable {
    //DemoModel在上下文中的数据
    DemoModelDTO demoModelDTO = producerContext.getDTO(DemoModelDTO.class);
    //获取要替代的数据
    String s = demoModelDTO.getV();
    //如果是null就结束
    if (StringUtils.isEmpty(s)) {
      //返回SUCCESS继续解析其他处理器
      return SUCCESS;
    }
    //构造消息载体
    StargateMessage<String> stargateMessage = new StargateMessage<>();
    stargateMessage.setBody(s);
    //获取编码器
    StargateEncodDTO stargateEncodDTO = producerContext.getDTO(StargateEncodDTO.class);
    //替换消息内容
    message
      .setBody(stargateEncodDTO.getRocketClientEncod().encod(stargateMessage, producerContext));
    //返回SUCCESS继续解析其他处理器
    return SUCCESS;
  }
}
