package com.javaitaly.main.instanceInject;

import java.io.File;
import java.lang.reflect.Field;

public interface InjectXmlInstance<X> {

    public void setFieldDocumentInstance(Field field, X instance);
        
    public File getFile(final Field field);
}
