package scs.tju.framework.helper;

import scs.tju.framework.annotation.Action;
import scs.tju.framework.bean.Handler;
import scs.tju.framework.bean.Request;
import scs.tju.framework.util.ArrayUtil;
import scs.tju.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: liyuze
 * @Description: 控制器助手类
 * @Date: Created in 下午3:00 17/9/21.
 */
public final class ControllerHelper {

    private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();

    static {
        // 获取所有的 Controller 类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            // 遍历这些 Controller 类
            for(Class<?> controllerClass : controllerClassSet){
                // 获取 Controller 类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    // 遍历这些 Controller 类中的方法
                    for(Method method : methods){
                        // 判断当前方法是否带有 Action 注解
                        if(method.isAnnotationPresent(Action.class)){
                            // 从 Action 注解中获取 URL 映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证 URL 映射规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(array) && array.length == 2){
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    // 初始化 Action Map
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @Author: liyuze
     * @Date: 下午3:47 17/9/21
     * @Description: 获取 Handler
     */
    public static Handler getHandler(String requestMethod,String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }

}
