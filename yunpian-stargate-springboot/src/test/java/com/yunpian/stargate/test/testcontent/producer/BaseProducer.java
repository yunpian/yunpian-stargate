package com.yunpian.stargate.test.testcontent.producer;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateDelayLevel;
import com.yunpian.stargate.annotation.StargateDelayMsec;
import com.yunpian.stargate.annotation.StargateDelayParam;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.annotation.StargateProducer;
import com.yunpian.stargate.annotation.StargateVipChannel;
import com.yunpian.stargate.test.plusmodel.TestModel;
import com.yunpian.stargate.test.testcontent.vo.TestVO;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/15 Time:3:26 PM
 */
@StargateProducer
@StargateMapper(topic = "baseConsumer")
@StargateVipChannel(false)
public interface BaseProducer {

  @StargateMapper("baseConsumer-test1")
  void test1(@StargateBody TestVO testVO);

  default void test1default(TestVO testVO) {
    test1(testVO);
  }

  @StargateMapper("testPullSize")
  void testPullSize(@StargateBody TestVO testVO);

  @StargateMapper("testPullNull")
  void testPullNull();

  @StargateMapper("testplusmodel")
  @TestModel("这个是自定义模块测试的内容！！！")
  void testplusmodel();

  @StargateMapper("testdLtime")
  @StargateDelayLevel(1)
  void testdLtime(@StargateBody TestVO testVO);

  @StargateMapper("testdtime")
  @StargateDelayMsec(16000)
  void testdtime(@StargateBody TestVO testVO);

  @StargateMapper("testdPtime")
  void testdPtime(@StargateBody TestVO testVO, @StargateDelayParam long time);
}
