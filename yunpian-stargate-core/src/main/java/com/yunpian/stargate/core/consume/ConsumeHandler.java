package com.yunpian.stargate.core.consume;

import com.yunpian.stargate.core.builder.ProcessCenter;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.dto.ConsumeMethodsDTO;
import com.yunpian.stargate.core.message.IProcessMessageConsume;
import com.yunpian.stargate.core.utils.LogUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumeHandler {

  private static final Logger log = LoggerFactory.getLogger(ConsumeHandler.class);

  private ConsumeContext consumeContext;

  public ConsumeResult handler(
    MessageExt messageExt,
    ConsumeConcurrentlyContext consumeConcurrentlyContext) {
    ConsumeResult consumeResult = new ConsumeResult();
    try {
      ConsumeMethodsDTO consumeMethodsDTO = consumeContext.getDTO(ConsumeMethodsDTO.class);
      Object[] args = new Object[consumeMethodsDTO.getParameterSize()];

      for (IProcessMessageConsume processMessageConsume : ProcessCenter
        .getConsumeMessageProcess()) {
        log.debug("IProcessMessageConsume for {} : {} ing", consumeMethodsDTO.getMethod().getName(),
          processMessageConsume.getClass().getName());
        int result = processMessageConsume
          .processConsume(consumeContext, messageExt, consumeConcurrentlyContext, args);
        if (result == IProcessMessageConsume.BREAK) {
          log.debug("IProcessMessageConsume for {} : {} BREAK",
            consumeMethodsDTO.getMethod().getName(),
            processMessageConsume.getClass().getName());
          return consumeResult;
        }
        if (result == IProcessMessageConsume.CONSUME) {
          log.debug("IProcessMessageConsume for {} : {} CONSUME",
            consumeMethodsDTO.getMethod().getName(),
            processMessageConsume.getClass().getName());
          break;
        }
      }

      LogUtils.messageLog(messageExt);

      log
        .debug("IProcessMessageConsume for {} invoke", consumeMethodsDTO.getMethod().getName());
      Object returnObject = consumeMethodsDTO.getMethod()
        .invoke(consumeMethodsDTO.getObject(), args);
      if (returnObject != null && returnObject instanceof ConsumeResult) {
        consumeResult = (ConsumeResult) returnObject;
      }
    } catch (Throwable e) {
      log.error("method.invoke is error:", e);
      consumeResult.setConsumeConcurrentlyStatus(ConsumeConcurrentlyStatus.RECONSUME_LATER);
    }
    return consumeResult;
  }

  public ConsumeContext getConsumeContext() {
    return consumeContext;
  }

  public void setConsumeContext(ConsumeContext consumeContext) {
    this.consumeContext = consumeContext;
  }
}