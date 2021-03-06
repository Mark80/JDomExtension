package com.javaitaly.main.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE})
public @interface PathPrefix {
     
    @Nonbinding  public String prefix() default ""; 
}
