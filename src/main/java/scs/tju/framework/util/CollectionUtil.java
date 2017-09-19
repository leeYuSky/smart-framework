package scs.tju.framework.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午2:03 17/9/19.
 */
public final class CollectionUtil {

    /**
     * @Author: liyuze
     * @Date: 下午2:29 17/9/19
     * @Description: 判断Collection是否为空
     */
    public static <T> boolean isEmpty(Collection<T> collection){
        return CollectionUtils.isEmpty(collection);
    }

/*    public static  boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }*/

    /**
     * @Author: liyuze
     * @Date: 下午2:34 17/9/19
     * @Description: 判断Collection是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * @Author: liyuze
     * @Date: 下午2:36 17/9/19
     * @Description: 判断Map是否为空
     */
    public static <K,V> boolean isEmpty(Map<K,V> map){
        return MapUtils.isEmpty(map);
    }


    /**
     * @Author: liyuze
     * @Date: 下午2:36 17/9/19
     * @Description: 判断Map是否非空
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }

}
