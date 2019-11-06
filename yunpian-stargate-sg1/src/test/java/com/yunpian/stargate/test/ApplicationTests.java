package com.yunpian.stargate.test;

import com.yunpian.stargate.manage.StargateInstance;
import com.yunpian.stargate.springboot.annotation.EnableStargate;
import com.yunpian.stargate.test.testcontent.consumer.BaseConsumer;
import com.yunpian.stargate.test.testcontent.producer.BaseProducer;
import com.yunpian.stargate.test.testcontent.vo.TestVO;
import java.util.UUID;
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
  public void testBus() throws InterruptedException {
    System.out.println("一次基本测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
    baseProducer.testBus(testVO, "a");
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    Thread.sleep(10000);
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
  }

  @Test(timeout = 30000)
  public void stargateTopicFormat() throws InterruptedException {
    System.out.println("一次StargateTopicFormat1测试:");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
    baseProducer.stargateTopicFormat(testVO, 1);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
    System.out.println("=======================================================================\n");
    System.out.println("一次StargateTopicFormat2测试:");
    baseConsumer.semaphore.acquire();
    testVO = new TestVO(UUID.randomUUID().toString(), "f");
    baseProducer.stargateTopicFormat(testVO, 2);
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.equals(baseConsumer.testVO)) {
      throw new RuntimeException("消息不相等");
    }
  }

  @Test(timeout = 30000)
  public void tagparamA() throws InterruptedException {
    System.out.println("一次测试:tagparam");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO("", "AAS");
    baseProducer.tagparam(testVO, "AAS");
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.getName().equals(baseConsumer.testVO.getString())) {
      throw new RuntimeException("TAG不相等");
    }
  }

  @Test(timeout = 30000)
  public void tagparamB() throws InterruptedException {
    System.out.println("一次测试:tagparam");
    baseConsumer.semaphore.acquire();
    TestVO testVO = new TestVO("", "BBS");
    baseProducer.tagparam(testVO, "BBS");
    System.out.println("发送成功：" + testVO);
    baseConsumer.semaphore.acquire();
    baseConsumer.semaphore.release();
    if (!testVO.getName().equals(baseConsumer.testVO.getString())) {
      throw new RuntimeException("TAG不相等");
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
