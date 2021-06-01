package xml_java.hw01_06;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XML2Object {
    public static void main(String[] args) {
        try {
            File file = new File("xml_java/hw01_06/books.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(file);

            // get root element
            Element rootElement = (Element) doc.getDocumentElement();
            NodeList books = rootElement.getChildNodes();

            // loop through child
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                // get author
                Element authorElement = (Element) book.getElementsByTagName("author").item(0);
                Author author = new Author(
                    authorElement.getElementsByTagName("name").item(0).getTextContent(),
                    Integer.parseInt(authorElement.getElementsByTagName("age").item(0).getTextContent())
                );
                // create book
                Book b = new Book(
                    book.getElementsByTagName("title").item(0).getTextContent(),
                    book.getElementsByTagName("publisher").item(0).getTextContent(),
                    author
                );

                System.out.println(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
