package com.javaitaly.main.fileProvider;

import java.io.File;
import java.lang.reflect.Field;

public interface FileProvider {
     
    public abstract File getFile(final Field field);
}
