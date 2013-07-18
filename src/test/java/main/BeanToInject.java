package main;

import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;

import com.javaitaly.main.annotation.XMLDocument;

@Named
public class BeanToInject {
    
    @Inject
    @XMLDocument(path = "xmlTest")
    Document documento;

    
 
    public String printMessage(){
        if(documento!=null) System.out.println("NOT NULL");
        return documento.getRootElement().getName();
    }
    
}