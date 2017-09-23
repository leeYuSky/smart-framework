package scs.tju.framework.bean;

import scs.tju.framework.util.CastUtil;

import java.util.Map;

/**
 * @Author: liyuze
 * @Description: 请求参数对象
 * @Date: Created in 下午9:21 17/9/23.
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * @Author: liyuze
     * @Date: 下午9:23 17/9/23
     * @Description: 根据参数名获取 long 型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(name);
    }


    /**
     * @Author: liyuze
     * @Date: 下午9:22 17/9/23
     * @Description: 获取所有字段信息
     */
    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
