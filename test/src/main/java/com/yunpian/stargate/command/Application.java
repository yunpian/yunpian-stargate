package com.yunpian.stargate.command;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.qipeng.QipengApplication;
import com.yunpian.stargate.command.config.EnableStargateCommand;
import com.yunpian.stargate.springboot.annotation.EnableStargate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;


/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/10/23 Time:5:26 PM
 */
@SpringBootApplication
@EnableApolloConfig
@EnableAspectJAutoProxy
@ImportResource(locations = {"classpath:framework.xml"})
@EnableStargateCommand
@EnableStargate
public class Application {

  public static void main(String[] args) {
    QipengApplication springApplication = new QipengApplication(Application.class);
    ConfigurableApplicationContext run = springApplication.run(args);
  }
}
