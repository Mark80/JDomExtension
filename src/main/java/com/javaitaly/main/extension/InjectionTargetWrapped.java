package com.javaitaly.main.extension;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import org.jdom.Document;

import com.javaitaly.main.annotation.XMLDocument;
import com.javaitaly.main.documentCreator.DocumentCreator;
import com.javaitaly.main.fileProvider.FileProvider;
import com.javaitaly.main.instanceInject.InjectFieldObject;


class InjectionTargetWrapped<X> implements InjectionTarget<X> {

    private final InjectionTarget<X> wrappedInjectionTarget;
    private final InjectFieldObject<X> injector;
    private final DocumentCreator documentCreator;
    private final FileProvider fileProvider;

    InjectionTargetWrapped(final InjectionTarget<X> wrappedInjectionTarget,final InjectFieldObject<X> injector,DocumentCreator documentCreator,FileProvider fileProvider) {
        super();
        this.wrappedInjectionTarget = wrappedInjectionTarget;
        this.injector=injector;
        this.documentCreator=documentCreator;
        this.fileProvider=fileProvider;
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
        	 if(isDocumentInjectableInto(field)) {
            File filexml=fileProvider.getFile(field);
            Document document=documentCreator.createJDocument(filexml);          
            injector.setFieldObjectInstance(field, instance, document);
            }
        }
    }
    
    private boolean isDocumentInjectableInto(final Field field) {
        return field.isAnnotationPresent(XMLDocument.class) && field.getType().isAssignableFrom(Document.class);
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
