package org.jxjz.framework.aop.annotation;


import java.lang.annotation.*;
/**
 * 【功能说明】：自定义验证重复提交注解
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckReqNo {

    String desc() default "";
}
