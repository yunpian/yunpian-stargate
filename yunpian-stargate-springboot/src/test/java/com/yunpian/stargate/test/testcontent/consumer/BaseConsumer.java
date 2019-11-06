package com.yunpian.stargate.test.testcontent.consumer;

import com.yunpian.stargate.annotation.StargateBody;
import com.yunpian.stargate.annotation.StargateConsumer;
import com.yunpian.stargate.annotation.StargateMapper;
import com.yunpian.stargate.annotation.StargatePullBatchSize;
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

  @StargatePullBatchSize(22)
  @StargateMapper("testPullSize")
  public void testPullSize(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }

  @StargatePullBatchSize(22)
  @StargateMapper("testPullNull")
  public void testPullNull() {
    System.out.println("接收成功：" + null);
    semaphore.release();
  }

  @StargateMapper("testplusmodel")
  public void testplusmodel(@StargateBody String v) {
    this.v = v;
    System.out.println("接收成功：" + v);
    semaphore.release();
  }

  @StargateMapper("testdLtime")
  public void testdLtime(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper("testdtime")
  public void testdtime(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }

  @StargateMapper("testdPtime")
  public void testdPtime(@StargateBody TestVO testVO) {
    this.testVO = testVO;
    System.out.println("接收成功：" + testVO);
    semaphore.release();
  }
}
