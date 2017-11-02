package scs.tju.framework.proxy;

/**
 * @Author: liyuze
 * @Description:
 * @Date: Created in 下午10:22 17/11/2.
 */
public interface Proxy {

    /**
     * @Author: liyuze
     * @Date: 下午10:24 17/11/2
     * @Description: 执行链式代理
     */

    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
