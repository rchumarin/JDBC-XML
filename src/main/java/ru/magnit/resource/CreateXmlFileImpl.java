package ru.magnit.resource;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.magnit.model.Field;

/**
 *
 * @author rchumarin
 */
public class CreateXmlFileImpl implements CreateXmlFile{

    @Override
    public void createXmlFile(List<Field> listField) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();            
            // root element
            Element rootElement = doc.createElement("entries");
            doc.appendChild(rootElement);            
            for(Field field : listField) {
                //append child elements to root element
                rootElement.appendChild(getEntry(doc, field.getField()));
            }    
            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
//            StreamResult console = new StreamResult(System.out);
            StreamResult console = new StreamResult(new File("./src/main/resources/1.xml"));
            transformer.transform(source, console);

            System.out.println("\nXML DOM Created Successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getEntry(Document doc, int value) {
        Element entry = doc.createElement("entry");
        entry.appendChild(getEntryElements(doc, entry, "field", value));
        return entry;
    }

    // utility method to create text node
    private static Node getEntryElements(Document doc, Element element, String name, int value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(Integer.toString(value)));
        return node;
    }
}