package scs.tju.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午2:02 17/9/21.
 */
public final class ArrayUtil {

    /**
     * @Author: liyuze
     * @Date: 下午2:04 17/9/21
     * @Description: 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * @Author: liyuze
     * @Date: 下午2:05 17/9/21
     * @Description: 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
}
