package xml_java.hw01_06;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // create root elemet
            Element rootElement = doc.createElement("books");

            // create sub element
            Element user = doc.createElement("book");

            // title
            Element title = (doc.createElement("title"));
            title.appendChild(doc.createTextNode("The end of the f world"));
            user.appendChild(title);
            
            // publisher
            Element publisher= (doc.createElement("publisher"));
            publisher.appendChild(doc.createTextNode("Netflix"));
            user.appendChild(publisher);

            // create author
            Element author = (doc.createElement("author"));

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode("Long"));

            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode("20"));

            // append to author
            author.appendChild(name);
            author.appendChild(age);

            user.appendChild(author);

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
            StreamResult fileResult = new StreamResult(new File("xml_java/hw01_06/books.xml"));
            transformer.transform(source, fileResult);
            
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
