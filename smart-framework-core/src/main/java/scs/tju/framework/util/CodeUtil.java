package scs.tju.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * @Author: liyuze
 * @Description: 编码与解码操作工具类
 * @Date: Created in 下午11:13 17/9/23.
 */
public final class CodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeUtil.class);


    /**
     * @Author: liyuze
     * @Date: 下午11:18 17/9/23
     * @Description: URL编码
     */
    public static String encodeURL(String source){
        String target;
        try{
            target = URLEncoder.encode(source,"UTF-8");
        }catch(Exception e){
            LOGGER.error("encode url failure",e);
            throw new RuntimeException(e);
        }

        return target;
    }

    /**
     * @Author: liyuze
     * @Date: 下午11:19 17/9/23
     * @Description: URL解码
     */
    public static String decodeURL(String source){
        String target;
        try{
            target = URLDecoder.decode(source,"UTF-8");
        }catch(Exception e){
            LOGGER.error("encode url failure",e);
            throw new RuntimeException(e);
        }

        return target;
    }

}
