package scs.tju.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 上午12:16 17/9/24.
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * @Author: liyuze
     * @Date: 上午12:25 17/9/24
     * @Description: 将 POJO 转为 JSON
     */
    public static <T> String toJson(T obj){
        String json;
        try{
            json = OBJECT_MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            LOGGER.error("convert POJO to JSON failure",e);
            throw new RuntimeException(e);
        }

        return json;
    }

    /**
     * @Author: liyuze
     * @Date: 上午12:28 17/9/24
     * @Description: 将 JSON 转为 POJO
     */
    public static <T> T fromJson(String json,Class<T> type){
        T pojo;
        try{
            pojo = OBJECT_MAPPER.readValue(json,type);
        }catch (Exception e){
            LOGGER.error("convert JSON to POJO failure");
            throw new RuntimeException(e);
        }

        return pojo;
    }
}
