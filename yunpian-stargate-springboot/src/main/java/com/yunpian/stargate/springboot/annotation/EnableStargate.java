package com.yunpian.stargate.springboot.annotation;

import com.yunpian.stargate.springboot.StargateRegistrar;
import com.yunpian.stargate.springboot.StargateProperties;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2018/8/15 Time:下午7:32
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({StargateRegistrar.class, StargateProperties.class})
public @interface EnableStargate {
    String[] value() default {};
}
