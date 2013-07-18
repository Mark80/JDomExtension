package com.javaitaly.main.extension;

import java.lang.reflect.Field;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import com.javaitaly.main.instanceInject.InjectXmlInstance;

class InjectionTargetWrapped<X> implements InjectionTarget<X> {

    private final InjectionTarget<X> wrappedInjectionTarget;
    private final InjectXmlInstance<X> injector;

    InjectionTargetWrapped(final InjectionTarget<X> wrappedInjectionTarget,final InjectXmlInstance<X> injector) {
        super();
        this.wrappedInjectionTarget = wrappedInjectionTarget;
        this.injector=injector;
    }

    @Override
    public void dispose(X instance) {
        wrappedInjectionTarget.dispose(instance);
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return wrappedInjectionTarget.getInjectionPoints();
    }

    public X produce(CreationalContext<X> ctx) {
        return wrappedInjectionTarget.produce(ctx);
    }

    @Override
    public void inject(X instance, CreationalContext<X> ctx) {
        wrappedInjectionTarget.inject(instance, ctx);
        final Class<? extends Object> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            injector.setFieldDocumentInstance(field, instance);
        }
    }

    @Override
    public void postConstruct(X instance) {
        wrappedInjectionTarget.postConstruct(instance);
    }

    @Override
    public void preDestroy(X instance) {
        wrappedInjectionTarget.preDestroy(instance);
    }

}
