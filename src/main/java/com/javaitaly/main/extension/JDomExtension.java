package com.javaitaly.main.extension;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import org.jdom.Document;

import com.javaitaly.main.annotation.XMLDocument;
import com.javaitaly.main.instanceInject.InjectXmlFileSystem;
import com.javaitaly.main.instanceInject.PathProvider;

public class JDomExtension<X> implements Extension {

    private final Set<String> pathValue = new HashSet<String>();


    public void addDocumentBean(@Observes AfterBeanDiscovery afterBeanDiscovery, final BeanManager beanManager) {
        final InjectionTarget<Document> injectionTarget = beanManager.createInjectionTarget(beanManager
                .createAnnotatedType(Document.class));
        for (String path : pathValue) {
            System.out.println(path);
            afterBeanDiscovery.addBean(new DocumentBean<Document>(injectionTarget, path, Document.class));
        }
    }

    public void processAnnotatedType(@Observes ProcessInjectionTarget<X> pit) {
        pit.setInjectionTarget(new InjectionTargetWrapped<X>(pit.getInjectionTarget(), new InjectXmlFileSystem<X>(new PathProvider())));
        for (InjectionPoint in : getInjectionPoints(pit)) {
            addXmlPathFromQualifiers(in.getQualifiers());
        }
    }

    private Set<InjectionPoint> getInjectionPoints(ProcessInjectionTarget<X> pit) {
        return pit.getInjectionTarget().getInjectionPoints();
    }

    private void addXmlPathFromQualifiers(Set<Annotation> qualifiers) {
        for (Annotation ann : qualifiers) {
            if (isXMLDocumentAnnotation(ann)) {
                final XMLDocument dodxml = (XMLDocument) ann;
                pathValue.add(dodxml.path());
                System.out.println(dodxml.path());
            }
        }
    }

    private boolean isXMLDocumentAnnotation(Annotation ann) {
        return ann.annotationType().equals(XMLDocument.class);
    }
}
