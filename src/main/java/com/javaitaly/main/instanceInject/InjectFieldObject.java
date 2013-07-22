package com.javaitaly.main.instanceInject;

import java.lang.reflect.Field;


public final  class InjectFieldObject<X> {
        
    public final void  setFieldObjectInstance(final Field field, final X instance,Object object) {
        field.setAccessible(true);
            try {
                field.set(instance, object);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }       
    }
}