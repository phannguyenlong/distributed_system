package xml_java.hw01_06.with_socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Server {

    public static Employee xml2Object(String xml) throws Exception {
        Employee e = null;
        System.out.println(xml);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(new InputSource(new StringReader(xml)));

             // get root element
            Element rootElement = (Element) doc.getDocumentElement();
            NodeList company = rootElement.getChildNodes();

            // loop through child
            for (int i = 0; i < company.getLength(); i++) {
                Element employee = (Element) company.item(i);

                // create employee
                e = new Employee(
                    Integer.parseInt(employee.getElementsByTagName("id").item(0).getTextContent()),
                    employee.getElementsByTagName("name").item(0).getTextContent(),
                    Integer.parseInt(employee.getElementsByTagName("age").item(0).getTextContent())

                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return e;
    }
    public static void main(String[] args) {
        int port = 9999;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("Server running on port: " + port);

            while (true) {
                Socket client = server.accept();

                // get stream
                DataInputStream in = new DataInputStream(client.getInputStream());

                // process data
                Employee e = xml2Object(in.readUTF());
                System.out.println(e);

                // close clent;
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    
}
