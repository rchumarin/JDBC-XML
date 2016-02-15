package ru.magnit.resource;

import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
/**
 *
 * @author rchumarin
 */
public class ParserXmlFileImpl implements ParserXmlFile {
    
    @Override
    public void parserXmlFile() {
        
        String xmlPath = "./src/main/resources/2.xml";
        
        int count = 0;

        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true); 
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(xmlPath);
            doc.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/entries/entry";            
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
//                System.out.println("\nCurrent Element :" + nNode.getNodeName());                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;                                        
                    count += Integer.parseInt(eElement.getAttribute("field"));                    
                }
            }
            System.out.println("Sum of all values of field : " + count); 
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
