package com.javaitaly.main.instanceInject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.javaitaly.main.annotation.XMLDocument;

public class InjectXmlFileSystem<X> extends InjectXmlScheleton<X> {
 
    @Override
    public Document getJDomDocumentForField(final Field field) {
        final String path = getPath(field);
        final SAXBuilder builder = new SAXBuilder();
        Document docResult = null;
        try {
            docResult = builder.build(new File(path));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docResult;
    }

    private String getPath(final Field field) {
        final XMLDocument xmlAnnotation = field.getAnnotation(XMLDocument.class);
        final String path = xmlAnnotation.path() + ".xml";
        return path;
    }
}
