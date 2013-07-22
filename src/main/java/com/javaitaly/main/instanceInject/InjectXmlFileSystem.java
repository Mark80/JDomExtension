package com.javaitaly.main.instanceInject;

import java.io.File;
import java.lang.reflect.Field;

import com.javaitaly.main.fileProvider.PathProvider;

public class InjectXmlFileSystem<X> extends InjectXmlScheleton<X> {
 
   private PathProvider pathProvider;

    public InjectXmlFileSystem(PathProvider pathProvider) {
	super();
	this.pathProvider = pathProvider;
}

	public File getFile(final Field field) {    
        final String path =pathProvider.getFilePath(field);
        return new File(path);
    }
    
}