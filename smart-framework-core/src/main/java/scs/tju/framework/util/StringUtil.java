package scs.tju.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: liyuze
 * @Description: 字符串工具类
 * @Date: Created in 下午1:18 17/9/19.
 */
public final class StringUtil {

    /**
     * @Author: liyuze
     * @Date: 下午1:43 17/9/19
     * @Description: 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }

        return StringUtils.isEmpty(str);
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:44 17/9/19
     * @Description: 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
