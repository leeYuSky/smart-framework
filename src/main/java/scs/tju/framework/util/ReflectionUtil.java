package scs.tju.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: liyuze
 * @Description: 反射工具类
 * @Date: Created in 下午12:58 17/9/21.
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * @Author: liyuze
     * @Date: 下午1:05 17/9/21
     * @Description: 创建实例
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try{
            instance = cls.newInstance();
        } catch (Exception e){
            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:09 17/9/21
     * @Description: 调用方法
     */
    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(obj,args);
        } catch (Exception e){
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:12 17/9/21
     * @Description: 设置成员变量的值
     */
    public static void setField(Object obj,Field field,Object value){
        try{
            field.setAccessible(true);
            field.set(obj,value);
        } catch (Exception e){
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}
