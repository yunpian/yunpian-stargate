package com.yunpian.stargate.test;

import com.yunpian.stargate.manage.StargateCoreManageInstance;
import com.yunpian.stargate.manage.StargateInstance;
import com.yunpian.stargate.springboot.annotation.EnableStargate;
import com.yunpian.stargate.test.testcontent.consumer.BaseConsumer;
import com.yunpian.stargate.test.testcontent.producer.BaseProducer;
import com.yunpian.stargate.test.testcontent.vo.TestVO;
import java.util.UUID;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTests.class})// 指定启动类
@Import({StargateConfiguration.class})
@EnableStargate("com.yunpian.stargate.test.testcontent")
public class ApplicationTests {

  @Autowired
  private BaseProducer baseProducer;
  @Autowired
  private BaseConsumer baseConsumer;

  @Before
  public void testBefore() {
    System.out.println("\n=======================================================================");
  }

  @After
  public void testAfter() {
    System.out.println("=======================================================================\n");
  }

  @Test(timeout = 30000)
  public void atestStart() {
    System.out.println("测试启动:");
    if (StargateInstance.getStargateInstance().isInit()) {
      StargateInstance.getStargateInstance().getStargateConsumeManage().startAll();
    } else {
      throw new RuntimeException("错误，未初始化");
    }
  }

  @Test(timeout = 30000)
  public void testBase() throws InterruptedException {
    System.out.println("一次基本测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
    baseProducer.test1(testVO);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
  }

  @Test(timeout = 30000)
  public void testdefault() throws InterruptedException {
    System.out.println("一次基本测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "test1default");
    baseProducer.test1default(testVO);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
  }

  @Test(timeout = 30000)
  public void testPullSize() throws InterruptedException {
    System.out.println("拉取大小测试:");
    DefaultMQPushConsumer mqPushConsumer = StargateCoreManageInstance.getStargateConsumeCoreManage()
      .getConsumeByGroup(StargateConfiguration.ENV + "-testPullSize-yunpian");
    if (!(22 == mqPushConsumer.getPullBatchSize())) {
      throw new RuntimeException("拉取大小设置错误");
    } else {
      System.out.println("设置正常");
    }
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "d");
    baseProducer.testPullSize(testVO);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
  }

  @Test(timeout = 30000)
  public void testPullNull() throws InterruptedException {
    System.out.println("一次基本测试:");
    baseConsumer.semaphore.acquire();
    baseProducer.testPullNull();
    System.out.println("发送成功：" + null);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    System.out.println("测试成功");
  }

  @Test(timeout = 30000)
  public void testplusmodel() throws InterruptedException {
    System.out.println("一次自定义模块测试:");
    baseConsumer.semaphore.acquire();
    baseProducer.testplusmodel();
    System.out.println("发送成功：" + "这个是自定义模块测试的内容！！！");
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!"这个是自定义模块测试的内容！！！".equals(baseConsumer.v)) {
      throw new RuntimeException("消息不相等");
    }
    System.out.println("测试成功");
  }

  @Test(timeout = 30000)
  public void testdLtime() throws InterruptedException {
    System.out.println("一次延时消息测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
    long time = System.currentTimeMillis();
    baseProducer.testdLtime(testVO);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    time = System.currentTimeMillis() - time;
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
    System.out.println("延时了：" + time + "毫秒");
    if (time < 10000 || time > 130000) {
      throw new RuntimeException("延时不正确");
    }
  }

  @Test(timeout = 30000)
  public void testdtime() throws InterruptedException {
    System.out.println("一次延时消息测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
    long time = System.currentTimeMillis();
    baseProducer.testdtime(testVO);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    time = System.currentTimeMillis() - time;
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
    System.out.println("延时了：" + time + "毫秒");
    if (time < 10000 || time > 130000) {
      throw new RuntimeException("延时不正确");
    }
  }

  @Test(timeout = 30000)
  public void testdPtime() throws InterruptedException {
    System.out.println("一次延时消息测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
    long time = System.currentTimeMillis();
    baseProducer.testdPtime(testVO, 17000);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    time = System.currentTimeMillis() - time;
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
    System.out.println("延时了：" + time + "毫秒");
    if (time < 10000 || time > 130000) {
      throw new RuntimeException("延时不正确");
    }
  }

  @Test(timeout = 30000)
  public void ztestShutdown() {
    System.out.println("测试关闭");
    StargateInstance.getStargateInstance().getStargateConsumeManage().closeAll();
    while (!StargateInstance.getStargateInstance().getStargateConsumeManage().isAllClose()) {
      System.out.println("等待关闭");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("已经关闭");
  }
}
