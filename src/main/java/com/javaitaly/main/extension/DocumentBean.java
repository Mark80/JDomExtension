package com.javaitaly.main.extension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import com.javaitaly.main.annotation.XMLDocumentImpl;

 class DocumentBean<T> implements Bean<T> {

    private final InjectionTarget<T> injectionTarget;
    private final String path;
    private Class<T> instanza;

    DocumentBean(InjectionTarget<T> injectionTarget,String path,Class<T> clazz) {
        super();
        this.injectionTarget = injectionTarget;
        this.path = path;
        this.instanza=clazz;
    }

    @Override
    public T create(CreationalContext<T> creationalContext) {
        T instance = injectionTarget.produce(creationalContext);
        injectionTarget.inject((T) instance, creationalContext);
        injectionTarget.postConstruct(instance);
        return instance;
    }

    @Override
    public void destroy(T instance, CreationalContext<T> creationalContext) {
        injectionTarget.preDestroy(instance);
        injectionTarget.dispose(instance);
        creationalContext.release();

    }

    @Override
    public Set<Type> getTypes() {
        Set<Type> types = new HashSet<Type>();
        types.add(instanza);
        return types;
    }

    @Override
    public Set<Annotation> getQualifiers() {
        final Set<Annotation> qualifiers = new HashSet<Annotation>();
        qualifiers.add(new XMLDocumentImpl(path));
        return qualifiers;

    }

    @Override
    public Class<? extends Annotation> getScope() {
        return Dependent.class;
    }

    @Override
    public String getName() {
        return "document";
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public boolean isAlternative() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public Class<?> getBeanClass() {
        return instanza;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return injectionTarget.getInjectionPoints();
    }
}
