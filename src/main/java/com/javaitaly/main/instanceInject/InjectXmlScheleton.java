package com.javaitaly.main.instanceInject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

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
    
    
    private Document getJDomDocumentForField(final Field field) {
        final SAXBuilder builder = new SAXBuilder();
        Document docResult = null;
        File fileXml=getFile(field);
        try {
            docResult = builder.build(fileXml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docResult;
    }

}