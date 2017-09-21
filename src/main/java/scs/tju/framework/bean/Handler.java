package scs.tju.framework.bean;

import java.lang.reflect.Method;

/**
 * @Author: liyuze
 * @Description: 封装 Action 信息
 * @Date: Created in 下午2:54 17/9/21.
 */
public class Handler {


    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
