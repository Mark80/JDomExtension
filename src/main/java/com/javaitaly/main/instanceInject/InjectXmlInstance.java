package com.javaitaly.main.instanceInject;

import java.lang.reflect.Field;

import org.jdom.Document;

public interface InjectXmlInstance<X> {

    public void setFieldDocumentInstance(Field field, X instance);
    
    public Document getJDomDocumentForField(Field field);
}
