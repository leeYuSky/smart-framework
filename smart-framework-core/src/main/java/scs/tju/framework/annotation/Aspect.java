package scs.tju.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午10:12 17/11/2.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();
}
