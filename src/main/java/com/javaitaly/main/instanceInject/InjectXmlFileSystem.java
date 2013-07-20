package com.javaitaly.main.instanceInject;

import java.io.File;
import java.lang.reflect.Field;

import com.javaitaly.main.annotation.XMLDocument;

public class InjectXmlFileSystem<X> extends InjectXmlScheleton<X> {
 
   private PrefixProvider prefixProvider;

    public InjectXmlFileSystem(PrefixProvider prefixProvider) {
	super();
	this.prefixProvider = prefixProvider;
}

	public File getFile(final Field field) {     
        String prefix=prefixProvider.getPrefixForFile(field);
        final XMLDocument xmlAnnotation = field.getAnnotation(XMLDocument.class);
        final String path = prefix+xmlAnnotation.path() + ".xml";
        return new File(path);
    }
    
}