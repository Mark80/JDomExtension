package com.javaitaly.main.instanceInject;

import java.lang.reflect.Field;

import com.javaitaly.main.annotation.PathPrefix;
import com.javaitaly.main.annotation.XMLDocument;

public class PathProvider {
	
	private String getPathPrefixFile(final Field field){
        PathPrefix pathPrefix=field.getDeclaringClass().getAnnotation(PathPrefix.class);
        String prefix="";
        if(pathPrefix!=null){            
            prefix=prefix+pathPrefix.prefix();
        }       
        return prefix;
    }
    
    public String getFilePath(final Field field){
    	final XMLDocument xmlAnnotation = field.getAnnotation(XMLDocument.class);
    	String prefix= getPathPrefixFile(field);
        final String path = prefix+xmlAnnotation.path() + ".xml";
        return path;
    }
}
