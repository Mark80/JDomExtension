package com.javaitaly.main.fileProvider;

import java.io.File;
import java.lang.reflect.Field;

public class FileSystemProviderImpl implements FileProvider {
    
    private final PathProvider pathProvider;

    public FileSystemProviderImpl(PathProvider pathProvider) {
        super();
        this.pathProvider=pathProvider;
    }
    
    @Override
    public File getFile(final Field field) {    
        final String path =pathProvider.getFilePath(field);
        return new File(path);
    }
    

}
