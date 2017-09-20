package scs.tju.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;



/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午10:06 17/9/20.
 */
public final class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * @Author: liyuze
     * @Date: 下午10:17 17/9/20
     * @Description: 获取类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * @Author: liyuze
     * @Date: 下午10:26 17/9/20
     * @Description: 加载类
     */
    public static Class<?> loadClass(String classname,boolean isInitialized){

     /* isInitialized : 是否初始化参数
     *  这里的初始化是指是否执行类的静态代码块
     *  为了提高加载类的性能, 可将 isInitialized 设置为 false
     * */

        Class<?> cls;

        try{
            cls = Class.forName(classname,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }

        return cls;
    }

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));

            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null){
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath().replaceAll("%20"," ");
//                        addClass(classSet,packagePath,packageName);
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("get class set failure",e);
            throw new RuntimeException(e);
        }

        return classSet;
    }
}
