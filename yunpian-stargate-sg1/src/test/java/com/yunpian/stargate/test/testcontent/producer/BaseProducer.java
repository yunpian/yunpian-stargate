package com.yunpian.stargate.test.testcontent.producer;

import com.yunpian.extend.bus.StargateMsgBus;
import com.yunpian.extend.bus.StargateMsgBusPip;
import com.yunpian.extend.demo.DemoModel;
import com.yunpian.extend.tagparam.StargateTagParam;
import com.yunpian.extend.topicformat.StargateTopicFormat;
import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.annotation.StargateProducer;
import com.yunpian.stargate.test.testcontent.vo.TestVO;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/15 Time:3:26 PM
 */
@StargateProducer
@StargateMapper(topic = "baseConsumer")
public interface BaseProducer {

  @StargateMapper("baseConsumer-test1")
  void test1(@StargateBody TestVO testVO);

  @StargateMapper("testplusmodel")
  @DemoModel("这个是自定义模块测试的内容！！！")
  void testplusmodel();

  @StargateMsgBus(value = "testBus", pip = "a")
  void testBus(@StargateBody TestVO testVO, @StargateMsgBusPip String pip);

  @StargateMapper(group = "StargateTopicFormat_code", topic = "StargateTopicFormat#{code}")
  void stargateTopicFormat(@StargateBody TestVO testVO, @StargateTopicFormat("code") int code);

  @StargateMapper("baseConsumer-tagparam")
  void tagparam(@StargateBody TestVO testVO, @StargateTagParam String tag);
}
