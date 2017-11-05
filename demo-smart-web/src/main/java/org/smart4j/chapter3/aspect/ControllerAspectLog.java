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
 * @Date: Created in 下午5:33 17/11/5.
 */
@Aspect(Controller.class)
public class ControllerAspectLog extends AspectProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspectLog.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        super.before(cls, method, params);
        LOGGER.debug("---------------------------Log begin---------------------------");

    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        super.after(cls, method, params, result);
        LOGGER.debug("---------------------------Log end ---------------------------");
    }
}
