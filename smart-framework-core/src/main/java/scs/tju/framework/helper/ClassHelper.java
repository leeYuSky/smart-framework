package scs.tju.framework.helper;

import scs.tju.framework.annotation.Controller;
import scs.tju.framework.annotation.Service;
import scs.tju.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liyuze
 * @Description: 类操作助手类
 * @Date: Created in 下午12:38 17/9/21.
 */
public final class ClassHelper {

    /**
     * @Author: liyuze
     * @Date: 下午12:41 17/9/21
     * @Description: 定义类集合(用于存放所加载的类)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:42 17/9/21
     * @Description: 获取应用包名下的所有类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:45 17/9/21
     * @Description: 获取应用包名下所有 Service 类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classset = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classset.add(cls);
            }
        }
        return classset;
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:52 17/9/21
     * @Description: 获取应用包名下所有 Controller 类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classset = new HashSet<Class<?>>();

        for(Class<?> cls:CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classset.add(cls);
            }
        }

        return classset;
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:55 17/9/21
     * @Description: 获取应用包名下所有 bean 类 (包括: Service , Controller 等)
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }


    /**
     * @Author: liyuze
     * @Date: 下午9:59 17/11/3
     * @Description: 获取应用包名下某父类(或接口)的所有子类(或实现类)
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            // isAssignableFrom 用来判断superClass是否和cls相同或superClass是否是cls的父类
            if(superClass.isAssignableFrom(cls) && !superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * @Author: liyuze
     * @Date: 下午10:03 17/11/3
     * @Description: 获取应用包名下带有某个注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

}
