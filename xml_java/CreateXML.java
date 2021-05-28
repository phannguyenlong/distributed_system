package xml_java;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // create root elemet
            Element rootElement = doc.createElement("users");

            // create subelemtn
            Element user = doc.createElement("user");

            Element id = (doc.createElement("id"));
            id.appendChild(doc.createTextNode("123"));
            user.appendChild(id);

            Element name= (doc.createElement("name"));
            name.appendChild(doc.createTextNode("John"));
            user.appendChild(name);

            Element age = (doc.createElement("age"));
            age.appendChild(doc.createTextNode("30"));
            user.appendChild(age);

            // add to root
            rootElement.appendChild(user);
            // add to doc
            doc.appendChild(rootElement);

            // convert doc to xml stirng
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            // write resuolt to screen
            StreamResult consolResult = new StreamResult(System.out);
            transformer.transform(source, consolResult);

            // or write result to file
            StreamResult fileResult = new StreamResult(new File("xml_java/user.xml"));
            transformer.transform(source, fileResult);
            
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
