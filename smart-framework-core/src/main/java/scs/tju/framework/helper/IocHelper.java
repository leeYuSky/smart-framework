package scs.tju.framework.helper;

import scs.tju.framework.annotation.Inject;
import scs.tju.framework.util.ArrayUtil;
import scs.tju.framework.util.CollectionUtil;
import scs.tju.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author: liyuze
 * @Description: 依赖注入助手类
 * @Date: Created in 下午2:06 17/9/21.
 */
public final class IocHelper {

    static{
        // 获取所有的 Bean 类与 Bean 实例之间的映射关系 (即 BeanMap)
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();

        if(CollectionUtil.isNotEmpty(beanMap)){

            for(Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
                // 从 BeanMap 中获取 Bean 类与 Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                // 获取 Bean 类定义的所有成员变量 (即 Bean Field)
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){
                    // 遍历 Bean Field
                    for(Field beanField : beanFields){
                        // 判断当前 Bean Field 是否带有 Inject 注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            // 在 BeanMap 中获取 Bean Field 对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                // 通过反射初始化 BeanField 的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }


}
