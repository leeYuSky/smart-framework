package scs.tju.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by liyuze on 17/9/19.
 */

public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);


    /**
     * @Author: liyuze
     * @Date: 上午11:25 17/9/19
     * @Description: 根据文件名称加载properties
     */
    public static Properties loadProps(String filename){

        Properties props = null;

        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);

            if(is == null){
                throw new FileNotFoundException(filename + " file is not found!");
            }

            props = new Properties();
            props.load(is);

        } catch (IOException e) {
            LOGGER.error("load properties file failure",e);
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure",e);
                }
            }
        }

        return props;

    }

    /**
     * @Author: liyuze
     * @Date: 上午11:27 17/9/19
     * @Description: 获取字符型属性(默认值为空字符串)
     */
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }

    /**
     * @Author: liyuze
     * @Date: 上午11:30 17/9/19
     * @Description: 获取字符型属性(可指定默认值)
     */
    public static String getString(Properties props,String key,String defaultValue){
        String value = defaultValue;
        if(props.containsKey(key)){
            value = props.getProperty(key);
        }

        return value;
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:26 17/9/19
     * @Description: 获取数值型属性(默认值为0)
     */
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:31 17/9/19
     * @Description: 获取数值型属性(可指定默认值)
     */
    public static int getInt(Properties props,String key,int defaultValue){
        int value = defaultValue;
        if(props.containsKey(key)){
            value = Integer.parseInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * @Author: liyuze
     * @Date: 下午12:32 17/9/19
     * @Description: 获取布尔型属性(默认值为false)
     */
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }


    /**
     * @Author: liyuze
     * @Date: 下午12:35 17/9/19
     * @Description: 获取布尔型属性(可指定默认值)
     */
    public static boolean getBoolean(Properties props,String key,boolean defaultValue){
        boolean value = defaultValue;
        if(props.containsKey(key)){
            value = Boolean.parseBoolean(props.getProperty(key));
        }
        return value;
    }
}
