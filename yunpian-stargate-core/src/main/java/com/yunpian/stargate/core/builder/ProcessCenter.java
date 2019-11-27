package com.yunpian.stargate.core.builder;

import com.yunpian.stargate.core.annotation.IProcessAnnotationConsume;
import com.yunpian.stargate.core.annotation.IProcessAnnotationProducer;
import com.yunpian.stargate.core.annotation.impl.MethodsProcessConsumeAnnotation;
import com.yunpian.stargate.core.annotation.impl.MethodsProcessProducerAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateBaseProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateBodyProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateBroadcastingProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateDecodeProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateDelayLevelProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateDelayMsecProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateDelayParamProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateEncodProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateFromWhereProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateMapperProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateNameServerGroupProcess;
import com.yunpian.stargate.core.annotation.impl.StargateNameServerProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateOneWayProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargatePullBatchSizeProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateThreadSizeProcessAnnotation;
import com.yunpian.stargate.core.annotation.impl.StargateVipChannelProcessAnnotation;
import com.yunpian.stargate.core.client.IProcessClientConsume;
import com.yunpian.stargate.core.client.IProcessClientProducer;
import com.yunpian.stargate.core.client.impl.ConsumeBaseProcessClientConsume;
import com.yunpian.stargate.core.client.impl.MapperProcessClientConsume;
import com.yunpian.stargate.core.client.impl.StargateVipChannelProcessClient;
import com.yunpian.stargate.core.client.impl.ThreadSizeClientConsume;
import com.yunpian.stargate.core.message.IProcessMessageConsume;
import com.yunpian.stargate.core.message.IProcessMessageProducer;
import com.yunpian.stargate.core.message.impl.CoreProcessMessageConsume;
import com.yunpian.stargate.core.message.impl.CoreProcessMessageProducer;
import com.yunpian.stargate.core.utils.SortIndexComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/11/23 Time:2:10 PM
 */
public class ProcessCenter {

  private static final ProcessCenter processCenter = new ProcessCenter();
  private List<IProcessAnnotationConsume> processAnnotationConsumes = Collections
    .synchronizedList(new ArrayList<>());
  private List<IProcessAnnotationProducer> processAnnotationProducers = Collections
    .synchronizedList(new ArrayList<>());

  private List<IProcessClientConsume> processClientConsumes = Collections
    .synchronizedList(new ArrayList<>());
  private List<IProcessClientProducer> processClientProducers = Collections
    .synchronizedList(new ArrayList<>());

  private List<IProcessMessageConsume> processMessageConsumes = Collections
    .synchronizedList(new ArrayList<>());
  private List<IProcessMessageProducer> processMessageProducers = Collections
    .synchronizedList(new ArrayList<>());

  private ProcessCenter() {
    processAnnotationConsumes.add(new MethodsProcessConsumeAnnotation());
    processAnnotationConsumes.add(new StargateMapperProcessAnnotation());
    processAnnotationConsumes.add(new StargateNameServerProcessAnnotation());
    processAnnotationConsumes.add(new StargateFromWhereProcessAnnotation());
    processAnnotationConsumes.add(new StargateBodyProcessAnnotation());
    processAnnotationConsumes.add(new StargateDecodeProcessAnnotation());
    processAnnotationConsumes.add(new StargateThreadSizeProcessAnnotation());
    processAnnotationConsumes.add(new StargateBroadcastingProcessAnnotation());
    processAnnotationConsumes.add(new StargatePullBatchSizeProcessAnnotation());
    processAnnotationConsumes.add(new StargateBaseProcessAnnotation());
    processAnnotationConsumes.add(new StargateVipChannelProcessAnnotation());
    processAnnotationConsumes.add(new StargateNameServerGroupProcess());

    processAnnotationProducers.add(new MethodsProcessProducerAnnotation());
    processAnnotationProducers.add(new StargateMapperProcessAnnotation());
    processAnnotationProducers.add(new StargateNameServerProcessAnnotation());
    processAnnotationProducers.add(new StargateBodyProcessAnnotation());
    processAnnotationProducers.add(new StargateEncodProcessAnnotation());
    processAnnotationProducers.add(new StargateOneWayProcessAnnotation());
    processAnnotationProducers.add(new StargateDelayMsecProcessAnnotation());
    processAnnotationProducers.add(new StargateDelayLevelProcessAnnotation());
    processAnnotationProducers.add(new StargateDelayParamProcessAnnotation());
    processAnnotationProducers.add(new StargateBaseProcessAnnotation());
    processAnnotationProducers.add(new StargateVipChannelProcessAnnotation());
    processAnnotationProducers.add(new StargateNameServerGroupProcess());

    processClientConsumes.add(new ConsumeBaseProcessClientConsume());
    processClientConsumes.add(new MapperProcessClientConsume());
    processClientConsumes.add(new ThreadSizeClientConsume());
    processClientConsumes.add(new StargateVipChannelProcessClient());

    processClientProducers.add(new StargateVipChannelProcessClient());

    processMessageConsumes.add(new CoreProcessMessageConsume());

    processMessageProducers.add(new CoreProcessMessageProducer());
  }

  public static void sortByIndex() {
    SortIndexComparator sortIndexComparator = new SortIndexComparator();
    Collections.sort(getConsumeAnnotationProcess(), sortIndexComparator);
    Collections.sort(getProducersAnnotationProcess(), sortIndexComparator);
    Collections.sort(getConsumeClientProcess(), sortIndexComparator);
    Collections.sort(getProducersClientProcess(), sortIndexComparator);
    Collections.sort(getConsumeMessageProcess(), sortIndexComparator);
    Collections.sort(getProducersMessageProcess(), sortIndexComparator);
  }

  public static ProcessCenter getInstance() {
    return processCenter;
  }

  public static List<IProcessAnnotationConsume> getConsumeAnnotationProcess() {
    return getInstance().processAnnotationConsumes;
  }

  public static List<IProcessAnnotationProducer> getProducersAnnotationProcess() {
    return getInstance().processAnnotationProducers;
  }

  public static List<IProcessClientConsume> getConsumeClientProcess() {
    return getInstance().processClientConsumes;
  }

  public static List<IProcessClientProducer> getProducersClientProcess() {
    return getInstance().processClientProducers;
  }

  public static List<IProcessMessageConsume> getConsumeMessageProcess() {
    return getInstance().processMessageConsumes;
  }

  public static List<IProcessMessageProducer> getProducersMessageProcess() {
    return getInstance().processMessageProducers;
  }

}
