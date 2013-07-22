package com.javaitaly.main.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import com.javaitaly.main.documentCreator.DocumentCreator;
import com.javaitaly.main.fileProvider.FileProvider;
import com.javaitaly.main.fileProvider.FileSystemProviderImpl;
import com.javaitaly.main.fileProvider.PathProvider;
import com.javaitaly.main.fileProvider.PathProviderFileSystem;
import com.javaitaly.main.instanceInject.InjectFieldObject;


public class JDomExtension<X> implements Extension {

    public void processAnnotatedType(@Observes ProcessInjectionTarget<X> pit) {
        final InjectionTarget<X> injectionTarget=pit.getInjectionTarget();
        final InjectFieldObject<X> injector=new InjectFieldObject<X>();
        final DocumentCreator documentCreator=new DocumentCreator();
        final PathProvider pathProvider=new PathProviderFileSystem();
        final FileProvider fileProvider=new FileSystemProviderImpl(pathProvider);   
        final InjectionTarget<X> injectionTargetWrapped=new InjectionTargetWrapped<X>(injectionTarget, injector,documentCreator,fileProvider);
        pit.setInjectionTarget(injectionTargetWrapped);
    }
}
