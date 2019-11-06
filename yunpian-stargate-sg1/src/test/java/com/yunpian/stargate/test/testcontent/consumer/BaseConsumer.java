package com.yunpian.stargate.test.testcontent.consumer;

import com.yunpian.extend.bus.StargateMsgBus;
import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateConsumer;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.test.testcontent.vo.TestVO;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/3/15 Time:3:26 PM
 */
@StargateConsumer
@StargateMapper(topic = "baseConsumer")
public class BaseConsumer {

  public Semaphore semaphore = new Semaphore(1);
  public TestVO testVO;
  public String v;

  @StargateMapper("baseConsumer-test1")
  public void test1(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper("testplusmodel")
  public void testplusmodel(@StargateBody String v) {
    this.v = v;
    System.out.println("接收成功：" + v);
    semaphore.release();
  }

  @StargateMsgBus(value = "testBus", pip = "a")
  public void testBus(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper("StargateTopicFormat1")
  public void stargateTopicFormat1(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("StargateTopicFormat1接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper("StargateTopicFormat2")
  public void stargateTopicFormat2(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("StargateTopicFormat2接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper(group = "baseConsumer-tagparam-AAS", topic = "baseConsumer-tagparam", tag = "AAS")
  public void tagparamA(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    testVO.setString("AAS");
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper(group = "baseConsumer-tagparam-BBS", topic = "baseConsumer-tagparam", tag = "BBS")
  public void tagparamB(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    testVO.setString("BBS");
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }
}
