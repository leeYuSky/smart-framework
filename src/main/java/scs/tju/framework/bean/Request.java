package scs.tju.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Author: liyuze
 * @Description: 封装请求信息
 * @Date: Created in 下午2:32 17/9/21.
 */
public class Request {

    /**
     * @Author: liyuze
     * @Date: 下午2:41 17/9/21
     * @Description: 请求方法
     */
    private String requestMethod;

    /**
     * @Author: liyuze
     * @Date: 下午2:41 17/9/21
     * @Description: 请求路径
     */
    private String requestPath;


    public Request(String requestMethod,String requestPath){
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }


    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this,o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


}
