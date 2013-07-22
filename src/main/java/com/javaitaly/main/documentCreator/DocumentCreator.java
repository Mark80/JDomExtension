package com.javaitaly.main.documentCreator;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DocumentCreator {

    public Document createJDocument(final File file) {
        File fileXml = file;
        final SAXBuilder builder = new SAXBuilder();
        Document docResult = null;
        try {
            docResult = builder.build(fileXml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docResult;
    }
}
