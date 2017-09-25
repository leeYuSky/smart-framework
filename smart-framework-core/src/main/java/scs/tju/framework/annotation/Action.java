package scs.tju.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 上午11:21 17/9/21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /*
    * 请求类型与路径
    * */
    String value();
}
