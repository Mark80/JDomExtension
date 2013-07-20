package main;

import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;

import com.javaitaly.main.annotation.PathPrefix;
import com.javaitaly.main.annotation.XMLDocument;

@Named
@PathPrefix(prefix="xml/")
public class BeanToInject {
    
    @Inject
    @XMLDocument(path = "xmlTest")
    Document documento;
    
    @Inject
    @XMLDocument(path = "xmlTest2")
    Document documento2;

    
 
    public String printMessage(){
        if(documento!=null) System.out.println("NOT NULL");
        return documento.getRootElement().getName();
    }
    
    public String printMessage2(){
        if(documento2!=null) System.out.println("NOT NULL");
        return documento2.getRootElement().getName();
    }
    
}
