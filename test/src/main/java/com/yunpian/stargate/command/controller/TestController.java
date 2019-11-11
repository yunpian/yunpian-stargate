package com.yunpian.stargate.command.controller;

import com.yunpian.stargate.command.config.Test;
import com.yunpian.stargate.command.service.ITestService;
import com.yunpian.stargate.command.utils.Click;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/5 Time:7:09 PM
 */
@RestController
public class TestController {

  Test test;

  @Autowired
  private ITestService testService;

  @RequestMapping("test")
  public Click test() {
//    AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator ;
//    annotationAwareAspectJAutoProxyCreator.postProcessAfterInitialization()
    testService.test("dhjsjadkas", "dhsaijdhkjas");
    return Click.buildSucc("测试一下");
  }
}
