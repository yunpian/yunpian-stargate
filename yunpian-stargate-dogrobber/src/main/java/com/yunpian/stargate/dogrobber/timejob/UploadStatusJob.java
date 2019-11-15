package com.yunpian.stargate.dogrobber.timejob;

import com.yunpian.stargate.core.dto.StargateBaseDTO;
import com.yunpian.stargate.core.dto.StargateMapperDTO;
import com.yunpian.stargate.dogrobber.dto.ConsumerClientInfoDTO;
import com.yunpian.stargate.dogrobber.dto.DataUploadDTO;
import com.yunpian.stargate.dogrobber.dto.ProducerClientInfoDTO;
import com.yunpian.stargate.dogrobber.product.DataUploadProduct;
import com.yunpian.stargate.manage.StargateCoreManageInstance;
import com.yunpian.stargate.manage.dto.StargateConsumeManageDTO;
import com.yunpian.stargate.manage.dto.StargateProducerManageDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/30 Time:3:51 PM
 */
public class UploadStatusJob implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadStatusJob.class);
  private static UploadStatusJob uploadStatusJob;
  private DataUploadProduct dataUploadProduct;
  private long delay = 300;
  private ScheduledExecutorService schedule;

  public UploadStatusJob() {
  }

  public UploadStatusJob(DataUploadProduct dataUploadProduct) {
    this.dataUploadProduct = dataUploadProduct;
  }

  public UploadStatusJob(long delay,
    DataUploadProduct dataUploadProduct) {
    this.delay = delay;
    this.dataUploadProduct = dataUploadProduct;
  }

  public DataUploadProduct getDataUploadProduct() {
    return dataUploadProduct;
  }

  public void setDataUploadProduct(
    DataUploadProduct dataUploadProduct) {
    this.dataUploadProduct = dataUploadProduct;
  }

  public long getDelay() {
    return delay;
  }

  public void setDelay(long delay) {
    this.delay = delay;
  }

  public void init() {
    uploadStatusJob = this;
    schedule = new ScheduledThreadPoolExecutor(1);
    schedule.scheduleAtFixedRate(this, 1, delay, TimeUnit.SECONDS);
  }

  public static void sendOnce() {
    if (uploadStatusJob == null) {
      return;
    }
    uploadStatusJob.run();
  }

  @Override
  public synchronized void run() {
    List<DataUploadDTO> dataUploadDTOS = new ArrayList<>();
    List<StargateConsumeManageDTO> stargateConsumeManageDTOS = StargateCoreManageInstance
      .getStargateConsumeCoreManage().consumeList();
    List<StargateProducerManageDTO> stargateProducerManageDTOS = StargateCoreManageInstance
      .getStargateProcessCoreManage().producerList();
    List<DataUploadDTO> collect = stargateConsumeManageDTOS.stream()
      .map(stargateConsumeManageDTO -> {
        DataUploadDTO dataUploadDTO = new DataUploadDTO();
        if (stargateConsumeManageDTO == null) {
          return dataUploadDTO;
        }
        dataUploadDTO.setKey(stargateConsumeManageDTO.getKey());
        if (stargateConsumeManageDTO.getConsumeContext() == null) {
          return dataUploadDTO;
        }
        Map<Class, Object> classObjectMap = stargateConsumeManageDTO.getConsumeContext().getData();
        dataUploadDTO = handleData(dataUploadDTO, classObjectMap);
        StargateBaseDTO stargateBaseDTO = stargateConsumeManageDTO.getConsumeContext()
          .getDTO(StargateBaseDTO.class);
        dataUploadDTO.setIdCard(StargateCoreManageInstance.getIdCard());
        dataUploadDTO.setId(stargateBaseDTO.getId());
        dataUploadDTO.setTags(stargateBaseDTO.getTags());
        dataUploadDTO.setType(0);
        dataUploadDTO.setAppName(stargateBaseDTO.getAppName());
        ConsumerClientInfoDTO consumerClientInfoDTO = new ConsumerClientInfoDTO();
        try {
          BeanUtils.copyProperties(consumerClientInfoDTO,
            stargateConsumeManageDTO.getConsumeContext().getMqConsumer());
        } catch (Exception e) {
          LOGGER.error("Exception:", e);
        }
        DefaultMQPushConsumerImpl defaultMQPushConsumerImpl = stargateConsumeManageDTO
          .getConsumeContext().getMqConsumer().getDefaultMQPushConsumerImpl();
        consumerClientInfoDTO.setServiceState(defaultMQPushConsumerImpl.getServiceState());
        consumerClientInfoDTO.setPause(defaultMQPushConsumerImpl.isPause());
        dataUploadDTO.setConsumerClientInfoDTO(consumerClientInfoDTO);
        return dataUploadDTO;
      }).collect(Collectors.toList());
    dataUploadDTOS.addAll(collect);

    collect = stargateProducerManageDTOS.stream()
      .map(stargateProducerManageDTO -> {
        DataUploadDTO dataUploadDTO = new DataUploadDTO();
        if (stargateProducerManageDTO == null) {
          return dataUploadDTO;
        }
        dataUploadDTO.setKey(stargateProducerManageDTO.getKey());
        if (stargateProducerManageDTO.getProducerContext() == null) {
          return dataUploadDTO;
        }
        Map<Class, Object> objectMap = stargateProducerManageDTO.getProducerContext().getData();
        dataUploadDTO = handleData(dataUploadDTO, objectMap);
        dataUploadDTO.setIdCard(StargateCoreManageInstance.getIdCard());
        StargateBaseDTO stargateBaseDTO = stargateProducerManageDTO.getProducerContext()
          .getDTO(StargateBaseDTO.class);
        dataUploadDTO.setId(stargateBaseDTO.getId());
        dataUploadDTO.setTags(stargateBaseDTO.getTags());
        dataUploadDTO.setAppName(stargateBaseDTO.getAppName());
        dataUploadDTO.setType(1);
        ProducerClientInfoDTO producerClientInfoDTO = new ProducerClientInfoDTO();
        try {
          BeanUtils.copyProperties(producerClientInfoDTO,
            stargateProducerManageDTO.getProducerContext().getMqProducer());
        } catch (Exception e) {
          LOGGER.error("Exception:", e);
        }
        producerClientInfoDTO.setServiceState(
          stargateProducerManageDTO.getProducerContext().getMqProducer().getDefaultMQProducerImpl()
            .getServiceState());
        dataUploadDTO.setProducerClientInfoDTO(producerClientInfoDTO);
        return dataUploadDTO;
      }).collect(Collectors.toList());
    dataUploadDTOS.addAll(collect);
    LOGGER.info("dataUploadDTOS size {}", dataUploadDTOS.size());
    for (DataUploadDTO dataUploadDTO : dataUploadDTOS) {
      dataUploadProduct.send(dataUploadDTO);
    }
  }

  private DataUploadDTO handleData(DataUploadDTO dataUploadDTO, Map<Class, Object> objectMap) {
    Map<String, Object> data = new ConcurrentHashMap<>(objectMap.size());
    for (Class aClass : objectMap.keySet()) {
      data.put(aClass.getName(), objectMap.get(aClass).toString());
    }
    dataUploadDTO.setData(data);
    StargateMapperDTO stargateMapperDTO = (StargateMapperDTO) objectMap
      .get(StargateMapperDTO.class);
    dataUploadDTO.setGroup(stargateMapperDTO.getGroup());
    dataUploadDTO.setTopic(stargateMapperDTO.getTopic());
    dataUploadDTO.setTag(stargateMapperDTO.getTag());
    return dataUploadDTO;
  }
}
