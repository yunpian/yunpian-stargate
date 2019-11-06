package com.yunpian.stargate.test.testbus1;

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
@SpringBootTest(classes = {ApplicationTestsBus.class})// 指定启动类
@Import({StargateConfiguration.class})
@EnableStargate("com.yunpian.stargate.test.testcontent")
public class ApplicationTestsBus {

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

  @Test
  public void testBus() throws InterruptedException {
    while (true) {
      TestVO testVO = new TestVO(UUID.randomUUID().toString(), "f");
      baseProducer.testBus(testVO, "a");
      System.out.println("===发送成功：" + testVO);
      Thread.sleep(1000);
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
