package main;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jdom.Document;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.javaitaly.main.annotation.XMLDocument;
import com.javaitaly.main.extension.JDomExtension;

@RunWith(Arquillian.class)
public class TestExtension {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
             .addClass(JDomExtension.class)
             .addClass(BeanToInject.class)
             .addClass(XMLDocument.class)
             .addClass(Document.class)
             .addAsResource("META-INF/services/javax.enterprise.inject.spi.Extension")
             .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");       
    }     
    
   @Inject
   BeanToInject bean;  
    
    @Test
    public void test() {
        String root=bean.printMessage();
        Assert.assertEquals("primo",root);
    }
}
