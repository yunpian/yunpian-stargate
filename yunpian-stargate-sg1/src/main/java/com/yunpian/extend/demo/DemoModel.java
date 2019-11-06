package com.yunpian.extend.demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/4/25 Time:4:16 PM 测试自定义模块 将内容替换成String
 * 这是一个扩展的示例 功能是把发送消息的内容全部替换成注解里的内容 具体的功能实现
 *
 * @see DemoMessage 注解处理的实现
 * @see DemoAnnotation DemoModelDTO 用来做数据传输
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoModel {

  String value() default "";
}
