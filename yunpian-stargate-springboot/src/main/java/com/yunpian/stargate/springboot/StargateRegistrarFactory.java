package com.yunpian.stargate.springboot;

import com.yunpian.stargate.core.builder.ProcessCenter;
import com.yunpian.stargate.core.builder.StargateFactory;
import com.yunpian.stargate.core.context.ConsumeContext;
import com.yunpian.stargate.core.context.ProducerContext;
import com.yunpian.stargate.core.dto.StargateConsumeBaseDTO;
import com.yunpian.stargate.core.dto.StargateDecodeDTO;
import com.yunpian.stargate.core.dto.StargateEncodDTO;
import com.yunpian.stargate.core.dto.StargateMapperDTO;
import com.yunpian.stargate.core.dto.StargateNameServerDTO;
import com.yunpian.stargate.core.dto.StargateThreadSizeDTO;
import com.yunpian.stargate.core.dto.StargateVipChannelDTO;
import com.yunpian.stargate.core.process.IStargateClientDecode;
import com.yunpian.stargate.core.process.IStargateClientEncod;
import com.yunpian.stargate.core.utils.DelayUtils;
import com.yunpian.stargate.core.utils.EncodAndDncodeFactory;
import com.yunpian.stargate.core.utils.StargateEnvironment;
import com.yunpian.stargate.core.utils.StringUtils;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/12/5 Time:9:24 PM
 */
public class StargateRegistrarFactory {

  @Autowired
  private StargateProperties stargateProperties;
  @Autowired(required = false)
  private StargateConfig stargateConfig;

  public void init() throws Throwable {
    if (stargateConfig == null) {
      stargateConfig = new StargateConfig();
      if (stargateProperties.getThreadSize() != null) {
        stargateConfig.setThreadSize(stargateProperties.getThreadSize());
      }
      if (stargateProperties.getNamesrvDefault() != null) {
        stargateConfig.setNamesrvDefault(stargateProperties.getNamesrvDefault());
      }
      if (stargateProperties.getEnv() != null) {
        stargateConfig.setEnv(stargateProperties.getEnv());
      }
      if (stargateProperties.getDelayMsecSwitch() != null) {
        stargateConfig.setDelayMsecSwitch(stargateProperties.getDelayMsecSwitch());
      }
      if (stargateProperties.getVipChannel() != null) {
        stargateConfig.setVipChannel(stargateProperties.getVipChannel());
      }
      if (stargateProperties.getNamesrvAddr() != null && !stargateProperties.getNamesrvAddr()
        .isEmpty()) {
        stargateConfig.setNamesrvAddr(stargateProperties.getNamesrvAddr());
      }
      if (!StringUtils.isBlank(stargateProperties.getDecodeClassName())) {
        stargateConfig.setDecodeClass(Class.forName(stargateProperties.getDecodeClassName()));
      }
      if (!StringUtils.isBlank(stargateProperties.getEncodClassName())) {
        stargateConfig.setEncodClass(Class.forName(stargateProperties.getEncodClassName()));
      }
      if (stargateProperties.getDelayLevel() != null) {
        stargateConfig.setDelayLevel(stargateProperties.getDelayLevel());
      }
    }
    for (String key : stargateConfig.getNamesrvAddr().keySet()) {
      String namesrvAddr = stargateConfig.getNamesrvAddr().get(key);
      namesrvAddr = namesrvAddr.replaceAll(",", ";");
      namesrvAddr = namesrvAddr.trim();
      stargateConfig.getNamesrvAddr().put(key, namesrvAddr);
    }
    if (stargateConfig.getNamesrvAddr().get(stargateConfig.getNamesrvDefault()) == null) {
      stargateConfig.getNamesrvAddr().put(stargateConfig.getNamesrvDefault(), "127.0.0.1:9876");
    }
    refresh(stargateConfig);
  }

  private void refresh(StargateConfig stargateConfig) {
    StargateEnvironment.setDelayMsecSwitch(stargateConfig.isDelayMsecSwitch());
    StargateEnvironment.setNamesrvAddr(stargateConfig.getNamesrvAddr());
    StargateEnvironment.setThreadSize(stargateConfig.getThreadSize());
    StargateEnvironment.setDecodeClass(stargateConfig.getDecodeClass());
    StargateEnvironment.setEncodClass(stargateConfig.getEncodClass());
    StargateEnvironment.setNamesrvDefault(stargateConfig.getNamesrvDefault());

    ProcessCenter.getConsumeAnnotationProcess()
      .addAll(stargateConfig.getProcessAnnotationConsumes());
    ProcessCenter.getProducersAnnotationProcess()
      .addAll(stargateConfig.getProcessAnnotationProducers());
    ProcessCenter.getConsumeClientProcess().addAll(stargateConfig.getProcessClientConsumes());
    ProcessCenter.getProducersClientProcess().addAll(stargateConfig.getProcessClientProducers());
    ProcessCenter.getConsumeMessageProcess().addAll(stargateConfig.getProcessMessageConsumes());
    ProcessCenter.getProducersMessageProcess().addAll(stargateConfig.getProcessMessageProducers());

    long[] delayLevel = stargateConfig.getDelayLevel();
    if (delayLevel != null) {
      DelayUtils.delayLevel = new long[delayLevel.length + 1];
      DelayUtils.delayLevel[0] = 0;
      for (int i = 0; i < delayLevel.length; ++i) {
        DelayUtils.delayLevel[i + 1] = delayLevel[i];
      }
    }
  }

  public <T> T createProducer(Class<T> clazz) throws Throwable {
    ProducerContext producerContextDefault = new ProducerContext();
    if (!StringUtils.isBlank(stargateConfig.getEnv())) {
      StargateMapperDTO stargateMapperDTO = producerContextDefault
        .getDTOOrNew(StargateMapperDTO.class);
      stargateMapperDTO.setGroup(stargateConfig.getEnv());
      stargateMapperDTO.addTopic(stargateConfig.getEnv());
    }
    StargateVipChannelDTO stargateVipChannelDTO = producerContextDefault
      .getDTOOrNew(StargateVipChannelDTO.class);
    StargateEncodDTO stargateEncodDTO = producerContextDefault.getDTOOrNew(StargateEncodDTO.class);
    StargateNameServerDTO stargateNameServerDTO = producerContextDefault
      .getDTOOrNew(StargateNameServerDTO.class);
    stargateEncodDTO.setRocketClientEncod((IStargateClientEncod) EncodAndDncodeFactory.
      getEncodOrDncod(stargateConfig.getEncodClass()));
    stargateVipChannelDTO.setVipChannel(stargateConfig.isVipChannel());
    stargateNameServerDTO.setNamesrvAddr(stargateConfig.getNamesrvDefault());
    return StargateFactory.createProducer(clazz, producerContextDefault);
  }

  public <T> T createConsume(Class<T> clazz) throws Throwable {
    ConsumeContext consumeContextDefault = new ConsumeContext();
    if (!StringUtils.isBlank(stargateConfig.getEnv())) {
      StargateMapperDTO stargateMapperDTO = consumeContextDefault
        .getDTOOrNew(StargateMapperDTO.class);
      stargateMapperDTO.setGroup(stargateConfig.getEnv());
      stargateMapperDTO.addTopic(stargateConfig.getEnv());
    }
    StargateDecodeDTO stargateDecodeDTO = consumeContextDefault
      .getDTOOrNew(StargateDecodeDTO.class);
    StargateVipChannelDTO stargateVipChannelDTO = consumeContextDefault
      .getDTOOrNew(StargateVipChannelDTO.class);
    StargateNameServerDTO stargateNameServerDTO = consumeContextDefault
      .getDTOOrNew(StargateNameServerDTO.class);
    StargateConsumeBaseDTO stargateConsumeBaseDTO = consumeContextDefault.getDTOOrNew(
      StargateConsumeBaseDTO.class);
    StargateThreadSizeDTO stargateThreadSizeDTO = consumeContextDefault.getDTOOrNew(
      StargateThreadSizeDTO.class);
    stargateDecodeDTO.setRocketClientDecode((IStargateClientDecode) EncodAndDncodeFactory.
      getEncodOrDncod(stargateConfig.getDecodeClass()));
    stargateVipChannelDTO.setVipChannel(stargateConfig.isVipChannel());
    stargateNameServerDTO.setNamesrvAddr(stargateConfig.getNamesrvDefault());
    stargateConsumeBaseDTO.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    stargateConsumeBaseDTO.setPullBatchSize(32);
    stargateConsumeBaseDTO.setBroadcasting(false);
    stargateThreadSizeDTO.setThreadSize(stargateConfig.getThreadSize());
    return StargateFactory.createConsume(clazz, consumeContextDefault);
  }
}
