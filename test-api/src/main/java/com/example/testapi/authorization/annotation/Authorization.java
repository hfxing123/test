package com.example.testapi.authorization.annotation;

import java.lang.annotation.Documented;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 类说明 </p>
 * @author hfx
 * @date 2019年10月11日16:40:52
 * @version 1.01
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented
@Inherited
public @interface Authorization {
    String description()  default "token认证开始...";
}
