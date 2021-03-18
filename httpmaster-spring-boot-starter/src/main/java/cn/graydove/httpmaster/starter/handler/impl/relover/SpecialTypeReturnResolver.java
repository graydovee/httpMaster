package cn.graydove.httpmaster.starter.handler.impl.relover;

import cn.graydove.httpmaster.core.common.Singleton;
import cn.hutool.core.util.ClassUtil;

public abstract class SpecialTypeReturnResolver implements ReturnResolver {

    private Singleton<Class<?>[]> supportClasses = Singleton.of(this::supportClass);

    @Override
    public boolean isSupport(Class<?> returnType) {
        for (Class<?> supportClass : getSupportClasses()) {
            if (ClassUtil.isAssignable(supportClass, returnType)) {
                return true;
            }
        }
        return false;
    }

    protected abstract Class<?>[] supportClass();

    private Class<?>[] getSupportClasses() {
        return supportClasses.get();
    }
}
