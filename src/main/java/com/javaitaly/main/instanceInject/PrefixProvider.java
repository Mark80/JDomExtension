package com.javaitaly.main.instanceInject;

import java.lang.reflect.Field;

import com.javaitaly.main.annotation.PathPrefix;

public class PrefixProvider {

    public String getPrefixForFile(final Field field){
        PathPrefix pathPrefix=field.getDeclaringClass().getAnnotation(PathPrefix.class);
        String prefix="";
        if(pathPrefix!=null){            
            prefix=prefix+pathPrefix.prefix();
        }       
        return prefix;
    }
}
