package scs.tju.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scs.tju.framework.annotation.Aspect;
import scs.tju.framework.annotation.Service;
import scs.tju.framework.proxy.AspectProxy;
import scs.tju.framework.proxy.Proxy;
import scs.tju.framework.proxy.ProxyManager;
import scs.tju.framework.proxy.TransactionProxy;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午10:35 17/11/3.
 */
public class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            for(Map.Entry<Class<?>,List<Proxy>> targetEntry : targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure",e);
        }
    }

    /**
     * @Author: liyuze
     * @Date: 下午5:04 17/11/5
     * @Description: 获得含有指定annotation的类的集合
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{

        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }

        return targetClassSet;
    }

    /**
     * @Author: liyuze
     * @Date: 下午5:02 17/11/5
     * @Description: 获得AspectProxy类的子类与其所对应的Aspect值的类的映射
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * @Author: liyuze
     * @Date: 下午10:14 17/11/5
     * @Description: 获得AspectProxy的子类的集合
     */
    private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
        // 获得AspectProxy的子类的集合
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> proxyClass : proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                // 获得Aspect注解中的值对应的类的集合
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                // key : AspectProxy的子类  value : Aspect注解中的值对应的类的集合
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
    }
    /**
     * @Author: liyuze
     * @Date: 下午10:13 17/11/5
     * @Description: 获得事务类代理的集合
     */
    private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class,serviceClassSet);
    }

    /**
     * @Author: liyuze
     * @Date: 下午5:30 17/11/5
     * @Description: 获得一个目标类与所对应的proxy类的对象
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for(Map.Entry<Class<?>,Set<Class<?>>> proxyEntry : proxyMap.entrySet()){
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for(Class<?> targetClass : targetClassSet){
                // 获得自定义proxy类的对象
                Proxy proxy = (Proxy) proxyClass.newInstance();

                // 一个类可能有多个自定义proxy类的对象
                if(targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else{
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass,proxyList);
                }
            }
        }
        return targetMap;
    }
}
