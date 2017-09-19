package scs.tju.framework.util;

/**
 * @Author: liyuze
 * @Description: 转型操作工具类
 * @Date: Created in 下午1:05 17/9/19.
 */
public final class CastUtil {

    /**
     * @Author: liyuze
     * @Date: 下午1:06 17/9/19
     * @Description: 转为String型(默认值为空字符串)
     */
    public static String castString(Object obj){
        return castString(obj,"");
    }


    /**
     * @Author: liyuze
     * @Date: 下午1:08 17/9/19
     * @Description: 转为String型(可提供默认值)
     */
    public static String castString(Object obj,String defaultvalue){
        return obj != null ? String.valueOf(obj) : defaultvalue;
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:16 17/9/19
     * @Description: 转为double类型(默认值为0)
     */
    public static double castDouble(Object obj){
        return castDouble(obj,0);
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:47 17/9/19
     * @Description: 转为double类型(可提供默认值)
     */
    public static double castDouble(Object obj,double defaultvalue){
        double doubleValue = defaultvalue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue));{
                try{
                    doubleValue = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    doubleValue = defaultvalue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:48 17/9/19
     * @Description: 转为long型(默认值为0)
     */
    public static long castLong(Object obj){
        return castLong(obj,0);
    }


    /**
     * @Author: liyuze
     * @Date: 下午1:54 17/9/19
     * @Description: 转为long型(可提供默认值)
     */
    public static long castLong(Object obj,long defaultvalue){
        long longValue = defaultvalue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    longValue = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue = defaultvalue;
                }
            }
        }
        return longValue;
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:56 17/9/19
     * @Description: 转为int型(默认值为0)
     */
    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    /**
     * @Author: liyuze
     * @Date: 下午1:58 17/9/19
     * @Description: 转为int型(可提供默认值)
     */
    public static int castInt(Object obj,int defaultValue){
        int intValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * @Author: liyuze
     * @Date: 下午2:00 17/9/19
     * @Description: 转为boolean类型(默认值为false)
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    /**
     * @Author: liyuze
     * @Date: 下午2:02 17/9/19
     * @Description: 转为boolean类型(可提供默认值)
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj != null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}
