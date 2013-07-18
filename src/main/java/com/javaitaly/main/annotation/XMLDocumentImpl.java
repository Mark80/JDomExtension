package com.javaitaly.main.annotation;

import javax.enterprise.util.AnnotationLiteral;


public class XMLDocumentImpl extends AnnotationLiteral<XMLDocument> implements XMLDocument{

    private static final long serialVersionUID = -1449921788599271826L;

    private final String path;
     
     public XMLDocumentImpl(final String path) {
        this.path = path;
    }

    @Override
    public final  String path() {       
        return path;
    }

}
