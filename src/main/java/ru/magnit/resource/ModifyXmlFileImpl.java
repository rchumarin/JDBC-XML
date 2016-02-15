package ru.magnit.resource;

import java.io.File;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author rchumarin
 */
public class ModifyXmlFileImpl implements ModifyXmlFile{
    
    public void modifyXmlFile() {
        
        String xslPath = "./src/main/resources/modify.xsl";
        String xmlPath = "./src/main/resources/1.xml";        
        try {
            TransformerFactory fact = new net.sf.saxon.TransformerFactoryImpl();
            Transformer transformer = fact.newTransformer(new StreamSource(new File(xslPath)));            
//            transformer.transform(new StreamSource(new File(xmlPath)), new StreamResult(System.out));
            transformer.transform(new StreamSource(new File(xmlPath)), new StreamResult(new File("./src/main/resources/2.xml"))); 
            System.out.println("Modify Xml File Successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
