package com.javaitaly.main.fileProvider;

import java.lang.reflect.Field;

public interface PathProvider {

    public String getFilePath(final Field field);
}
