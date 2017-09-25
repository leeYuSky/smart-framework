package scs.tju.framework;

import scs.tju.framework.helper.BeanHelper;
import scs.tju.framework.helper.ClassHelper;
import scs.tju.framework.helper.ControllerHelper;
import scs.tju.framework.helper.IocHelper;
import scs.tju.framework.util.ClassUtil;

/**
 * @Author: liyuze
 * @Description: 加载相应的 Helper 类
 * @Date: Created in 下午3:47 17/9/21.
 */
public final class HelperLoader {

    public static void init(){
        Class<?>[] classlist = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for(Class<?> cls : classlist){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }

}
