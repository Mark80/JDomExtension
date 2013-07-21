package com.javaitaly.main.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import com.javaitaly.main.instanceInject.InjectXmlFileSystem;
import com.javaitaly.main.instanceInject.PathProvider;

public class JDomExtension<X> implements Extension {


    public void processAnnotatedType(@Observes ProcessInjectionTarget<X> pit) {
        pit.setInjectionTarget(new InjectionTargetWrapped<X>(pit.getInjectionTarget(), new InjectXmlFileSystem<X>(new PathProvider())));

    }
}
