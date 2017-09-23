package scs.tju.framework.bean;

import java.util.Map;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午9:24 17/9/23.
 */
public class View {


    /**
     * @Author: liyuze
     * @Date: 下午9:26 17/9/23
     * @Description: 视图路径
     */
    private String path;

    /**
     * @Author: liyuze
     * @Date: 下午9:27 17/9/23
     * @Description: 模型数据
     */
    private Map<String,Object> model;

    public View(String path, Map<String, Object> model) {
        this.path = path;
        this.model = model;
    }

    public View addModel(String key,Object obj){
        model.put(key,obj);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
