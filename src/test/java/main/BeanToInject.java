package main;

import javax.inject.Named;

import org.jdom.Document;

import com.javaitaly.main.annotation.PathPrefix;
import com.javaitaly.main.annotation.XMLDocument;

@Named
@PathPrefix(prefix="xml/")
public class BeanToInject {
    

    @XMLDocument(path = "xmlTest")
    Document documento;
    
    @XMLDocument(path = "xmlTest2")
    Document documento2;

    
 
    public String printMessage(){
        if(documento!=null) System.out.println(documento.getRootElement().getName());
        return documento.getRootElement().getName();
    }
    
    public String printMessage2(){
        if(documento2!=null) System.out.println(documento2.getRootElement().getName());
        return documento2.getRootElement().getName();
    }
    
}
