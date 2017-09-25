package scs.tju.framework.helper;

import scs.tju.framework.ConfigConstant;
import scs.tju.framework.util.PropsUtil;

import java.util.Properties;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午9:45 17/9/20.
 */
public final class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * @Author: liyuze
     * @Date: 下午9:50 17/9/20
     * @Description: 获取 JDBC 驱动
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }

    /**
     * @Author: liyuze
     * @Date: 下午9:57 17/9/20
     * @Description: 获取 JDBC URL
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }

    /**
     * @Author: liyuze
     * @Date: 下午9:58 17/9/20
     * @Description: 获取 JDBC 用户名
     */
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }

    /**
     * @Author: liyuze
     * @Date: 下午9:59 17/9/20
     * @Description: 获取 JDBC 密码
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORK);
    }

    /**
     * @Author: liyuze
     * @Date: 下午10:02 17/9/20
     * @Description: 获取应用基础包名
     */
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * @Author: liyuze
     * @Date: 下午10:03 17/9/20
     * @Description: 获取相应的 JSP 路径
     */
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH,"/WEB-INF/view/");
    }

    /**
     * @Author: liyuze
     * @Date: 下午10:04 17/9/20
     * @Description: 获取应用静态资源路径
     */
    public static String getAppAssertPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSERT_PATH,"/assert/");
    }
}
