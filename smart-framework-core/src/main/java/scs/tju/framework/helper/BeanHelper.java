package scs.tju.framework.helper;

import scs.tju.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: liyuze
 * @Description: Bean 助手类
 * @Date: Created in 下午1:14 17/9/21.
 */
public final class BeanHelper {

    /**
     * @Author: liyuze
     * @Date: 下午1:24 17/9/21
     * @Description: 定义 bean 映射(用于存放 Bean 类与 Bean 实例的映射关系)
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> cls : beanClassSet){
            Object obj = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls,obj);
        }
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:26 17/9/21
     * @Description: 获取 bean 实例
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:29 17/9/21
     * @Description: 获取 bean 实例
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class : "+ cls);
        }

        return (T) BEAN_MAP.get(cls);
    }
}
