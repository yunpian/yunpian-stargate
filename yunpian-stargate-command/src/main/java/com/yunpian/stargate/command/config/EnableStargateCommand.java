package com.yunpian.stargate.command.config;

import com.yunpian.stargate.springboot.annotation.EnableStargate;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/15 Time:下午7:32
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@EnableStargate({"com.yunpian.stargate.command"})
@ComponentScan({"com.yunpian.stargate.command"})
public @interface EnableStargateCommand {

}
