package org.smart4j.chapter3.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scs.tju.framework.annotation.Aspect;
import scs.tju.framework.annotation.Controller;
import scs.tju.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午11:40 17/11/3.
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        super.before(cls, method, params);
        LOGGER.debug("---------------------------begin---------------------------");
        LOGGER.debug(String.format("class: %s",cls.getName()));
        LOGGER.debug(String.format("method: %s",method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        super.after(cls, method, params, result);
        LOGGER.debug(String.format("time: %dms",System.currentTimeMillis() - begin));
        LOGGER.debug("--------------------------- end ---------------------------");
    }
}
