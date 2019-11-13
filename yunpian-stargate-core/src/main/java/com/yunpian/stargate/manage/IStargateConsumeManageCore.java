package com.yunpian.stargate.manage;

import com.yunpian.stargate.manage.dto.StargateConsumeManageDTO;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/31 Time:11:26 AM
 */
public interface IStargateConsumeManageCore extends IStargateConsumeManage {

  List<StargateConsumeManageDTO> consumeList();

  StargateConsumeManageDTO getConsumeByKey(String key);

  boolean stopConsumeByKey(String key);

  boolean startConsumeByKey(String key);

  boolean suspendConsumeByKey(String key);

  boolean resumeConsumeByKey(String key);

  DefaultMQPushConsumer getConsumeByGroup(String group);

  boolean updateThreadSize(String key, Integer data);
}
