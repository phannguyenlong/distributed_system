package xml_java;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadXML {
    public static void main(String[] args) {
        try {
            File file = new File("D:/Long_Document/VGU-CS/excercise/distributed_system/xml_java/bookstore.xml");

            // get the file
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            // Get book list
            Element rootElement = (Element) doc.getDocumentElement(); // get rootELement (which is <store>)
            NodeList books = rootElement.getElementsByTagName("book");

            // loop thorugh child
            for (int i = 0; i < books.getLength(); i++) { 
                Element book = (Element) books.item(i);
                Book b = new Book(
                                book.getAttribute("category"), 
                                book.getElementsByTagName("title").item(0).getTextContent(),
                                book.getElementsByTagName("author").item(0).getTextContent(),
                                book.getElementsByTagName("year").item(0).getTextContent(),
                                book.getElementsByTagName("price").item(0).getTextContent()
                );
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}