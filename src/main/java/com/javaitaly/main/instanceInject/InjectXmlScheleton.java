package com.javaitaly.main.instanceInject;

import java.lang.reflect.Field;

import org.jdom.Document;

import com.javaitaly.main.annotation.XMLDocument;


public abstract class InjectXmlScheleton<X> implements InjectXmlInstance<X> {

    @Override
    public final void  setFieldDocumentInstance(final Field field, final X instance) {
        field.setAccessible(true);
        if (isDocumentInjectableInto(field)) {
            final Document document = getJDomDocumentForField(field);
            try {
                field.set(instance, document);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean isDocumentInjectableInto(final Field field) {
        return field.isAnnotationPresent(XMLDocument.class) && field.getType().isAssignableFrom(Document.class);
    }

}