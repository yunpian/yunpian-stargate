package com.yunpian.stargate.manage;

import com.yunpian.stargate.core.builder.StargateFactory;
import com.yunpian.stargate.core.consume.ConsumeFactory;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.dto.StargateBaseDTO;
import com.yunpian.stargate.core.exception.StargateException;
import com.yunpian.stargate.core.utils.StringUtils;
import com.yunpian.stargate.manage.dto.StargateConsumeManageDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.ServiceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/12 Time:1:28 PM
 *
 * @since 1.4
 */
public class StargateConsumeManageImpl implements IStargateConsumeManageCore {

  private static final Logger LOGGER = LoggerFactory.getLogger(StargateConsumeManageImpl.class);

  private volatile boolean isAllClose = false;
  private Map<String, StargateConsumeManageDTO> stargateConsumeManageDTOMap
    = new ConcurrentHashMap<String, StargateConsumeManageDTO>();
  private Map<String, String> oldGroupToKey
    = new ConcurrentHashMap<String, String>();

  public void add(StargateConsumeManageDTO stargateConsumeManageDTO) {
    ConsumeContext consumeContext = stargateConsumeManageDTO.getConsumeContext();
    StargateBaseDTO stargateBaseDTO = consumeContext.getDTO(StargateBaseDTO.class);
    String key = stargateBaseDTO.getId();
    stargateConsumeManageDTO.setKey(key);

    String group = consumeContext.getGroup();
    oldGroupToKey.put(group, key);
    stargateConsumeManageDTOMap.put(key, stargateConsumeManageDTO);
  }

  @Override
  public List<StargateConsumeManageDTO> consumeList() {
    return new ArrayList<>(stargateConsumeManageDTOMap.values());
  }

  @Override
  public StargateConsumeManageDTO getConsumeByKey(String key) {
    return stargateConsumeManageDTOMap.get(key);
  }

  @Override
  public boolean stopConsumeByKey(String key) {
    StargateConsumeManageDTO consumeByKey = getConsumeByKey(key);
    if (consumeByKey == null || consumeByKey.getConsumeContext() == null) {
      LOGGER.info("consumeByKey==null||consumeByKey.getConsumeContext()==null");
      return false;
    }
    DefaultMQPushConsumer mqConsumer = consumeByKey.getConsumeContext().getMqConsumer();
    if (mqConsumer.getDefaultMQPushConsumerImpl().getServiceState()
      .equals(ServiceState.CREATE_JUST)) {
      return true;
    } else if (mqConsumer.getDefaultMQPushConsumerImpl().getServiceState()
      .equals(ServiceState.RUNNING)) {
      mqConsumer.shutdown();
    }
    consumeByKey.getConsumeContext().setMqConsumer(null);
    DefaultMQPushConsumer consumer = null;
    try {
      consumer = StargateFactory
        .createMQConsumer(consumeByKey.getConsumeContext());
    } catch (Throwable throwable) {
      LOGGER.error("throwable:", throwable);
      return false;
    }
    ConsumeFactory
      .registerMessageListener(consumer, consumeByKey.getConsumeContext().getMessageListener());
    consumeByKey.getConsumeContext().setMqConsumer(consumer);
    return true;
  }

  @Override
  public boolean startConsumeByKey(String key) {
    StargateConsumeManageDTO consumeByKey = getConsumeByKey(key);
    if (consumeByKey == null || consumeByKey.getConsumeContext() == null) {
      LOGGER.info("consumeByKey==null||consumeByKey.getConsumeContext()==null");
      return false;
    }
    DefaultMQPushConsumer mqConsumer = consumeByKey.getConsumeContext().getMqConsumer();
    if (mqConsumer.getDefaultMQPushConsumerImpl().getServiceState()
      .equals(ServiceState.CREATE_JUST)) {
      boolean b = ConsumeFactory.startMQConsumer(consumeByKey.getConsumeContext().getMqConsumer());
      return b;
    }
    return false;
  }

  @Override
  public boolean suspendConsumeByKey(String key) {
    StargateConsumeManageDTO consumeByKey = getConsumeByKey(key);
    if (consumeByKey == null || consumeByKey.getConsumeContext() == null) {
      LOGGER.info("consumeByKey==null||consumeByKey.getConsumeContext()==null");
      return false;
    }
    DefaultMQPushConsumer mqConsumer = consumeByKey.getConsumeContext().getMqConsumer();
    mqConsumer.suspend();
    return true;
  }

  @Override
  public boolean resumeConsumeByKey(String key) {
    StargateConsumeManageDTO consumeByKey = getConsumeByKey(key);
    if (consumeByKey == null || consumeByKey.getConsumeContext() == null) {
      LOGGER.info("consumeByKey==null||consumeByKey.getConsumeContext()==null");
      return false;
    }
    DefaultMQPushConsumer mqConsumer = consumeByKey.getConsumeContext().getMqConsumer();
    mqConsumer.resume();
    return true;
  }

  @Override
  public DefaultMQPushConsumer getConsumeByGroup(String group) {
    String key = oldGroupToKey.get(group);
    if (StringUtils.isEmpty(key)) {
      return null;
    }
    StargateConsumeManageDTO consumeByKey = getConsumeByKey(key);
    if (consumeByKey == null || consumeByKey.getConsumeContext() == null) {
      return null;
    }
    return consumeByKey.getConsumeContext().getMqConsumer();
  }

  @Override
  public boolean updateThreadSize(String key, Integer data) {
    if (data == null || data < 1 || data > 999) {
      return false;
    }
    boolean b = stopConsumeByKey(key);
    if (!b) {
      return b;
    }
    StargateConsumeManageDTO consumeByKey = getConsumeByKey(key);
    if (consumeByKey == null || consumeByKey.getConsumeContext() == null) {
      LOGGER.info("consumeByKey==null||consumeByKey.getConsumeContext()==null");
      return false;
    }
    DefaultMQPushConsumer mqConsumer = consumeByKey.getConsumeContext().getMqConsumer();
    mqConsumer.setConsumeThreadMin(data);
    mqConsumer.setConsumeThreadMax(data);
    b = startConsumeByKey(key);
    if (!b) {
      return b;
    }
    return true;
  }

  @Override
  public boolean startAll() {
    for (String key : oldGroupToKey.keySet()) {
      LOGGER.info("startMQConsumer {}", key);
      boolean b = ConsumeFactory.startMQConsumer(key);
      if (!b) {
        throw new StargateException("startAll error group:" + key);
      }
    }
    return true;
  }

  @Override
  public void closeAll() {
    new Thread() {
      @Override
      public void run() {
        for (StargateConsumeManageDTO stargateConsumeManageDTO : stargateConsumeManageDTOMap
          .values()) {
          LOGGER.info("shutdown mqPushConsumer");
          //这种情况理论上不会出现
          if (stargateConsumeManageDTO == null
            || stargateConsumeManageDTO.getConsumeContext() == null) {
            LOGGER.info(
              "stargateConsumeManageDTO==null||stargateConsumeManageDTO.getConsumeContext()==null");
            continue;
          }
          DefaultMQPushConsumer mqPushConsumer = stargateConsumeManageDTO.getConsumeContext()
            .getMqConsumer();
          mqPushConsumer.shutdown();
        }
        isAllClose = true;
      }
    }.start();
  }

  @Override
  public boolean isAllClose() {
    LOGGER.info("isAllClose {}", isAllClose);
    return isAllClose;
  }

}
