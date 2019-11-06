package com.yunpian.stargate.test.plusmodel;

import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.StargateEncodDTO;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.core.utils.StringUtils;
import com.yunpian.stargate.core.vo.StargateMessage;
import org.apache.rocketmq.common.message.Message;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:20 PM
 */
public class TestMessage implements IProcessMessageProducer {

  @Override
  public int processProducer(ProducerContext producerContext, Message message, Object[] args)
    throws Throwable {
    TestModelDTO testModelDTO = producerContext.getDTO(TestModelDTO.class);
    String s = testModelDTO.getV();
    if (StringUtils.isEmpty(s)) {
      return SUCCESS;
    }
    StargateMessage<String> stargateMessage = new StargateMessage<>();
    stargateMessage.setBody(s);
    StargateEncodDTO stargateEncodDTO = producerContext.getDTO(StargateEncodDTO.class);
    message
      .setBody(stargateEncodDTO.getRocketClientEncod().encod(stargateMessage, producerContext));
    return SUCCESS;
  }
}
