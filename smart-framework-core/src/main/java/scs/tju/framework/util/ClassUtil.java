package scs.tju.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


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

    /**
     * @Author: liyuze
     * @Date: 上午12:12 17/9/26
     * @Description: 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * @Author: liyuze
     * @Date: 上午11:13 17/9/21
     * @Description: 获取 classSet
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        try {
            // 获得当前类包的url
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));

            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null){
                    // 获取当前url的协议
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){

                        String packagePath = url.getPath().replaceAll("%20"," ");
                        addClass(classSet,packagePath,packageName);

                    } else if(protocol.equals("jar")){

                        JarURLConnection jarurlconnection = (JarURLConnection) url.openConnection();

                        if(jarurlconnection != null){
                            JarFile jarfile = jarurlconnection.getJarFile();
                            if(jarfile != null){
                                Enumeration<JarEntry> jarEntries = jarfile.entries();

                                while (jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("get class set failure",e);
            throw new RuntimeException(e);
        }

        return classSet;
    }


    /**
     * @Author: liyuze
     * @Date: 上午10:58 17/9/21
     * @Description: 获得当前目录下所有的子文件和子目录, 并进行加载
     */
    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for(File file : files){
            // 获得文件名称
            String filename = file.getName();
            if(file.isFile()){
                // 如果是文件, 那么获取 class 的名称
                String className = filename.substring(0, filename.lastIndexOf("."));
                if(StringUtil.isNotEmpty(className)){
                    // 完整类名称要加上包名
                    className = packageName + "." + className;
                    doAddClass(classSet,className);
                }

            } else {
                // 获得目录名称
                String subPackagePath = filename;
                if(StringUtil.isNotEmpty(packagePath)){
                    // 子目录路径 = 当前目录路径 + / + 目录名称
                    subPackagePath = packagePath + "/" + subPackagePath;
                }

                String subPackageName = filename;
                if(StringUtil.isNotEmpty(packageName)){
                    // 子目录完整名 = 当前目录完整名 + . + 目录名称
                    subPackageName = packageName + "." + subPackageName;
                }

                // 递归继续加载子目录下的文件
                addClass(classSet,subPackagePath,subPackageName);
            }
        }


    }

    /**
     * @Author: liyuze
     * @Date: 上午10:53 17/9/21
     * @Description: 加载类并将其放在 ClassSet 中
     */
    private static void doAddClass(Set<Class<?>> classSet,String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }
}
