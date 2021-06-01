package xml_java.hw01_06.with_socket;

import java.io.DataOutputStream;
import java.io.StringWriter;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Client {
    public static String convertObject2XMLString(Employee e) {
        String xmlData = null;

        try {
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // create root element
            Element root = doc.createElement("company");

            // create employee
            Element employee = doc.createElement("employee");
            
            // id
            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(String.valueOf(e.getId())));
            employee.appendChild(id);

            // name
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(e.getName()));
            employee.appendChild(name);

            // age
            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode(String.valueOf(e.getAge())));
            employee.appendChild(age);

            // append to root
            root.appendChild(employee);
            // append to doc
            doc.appendChild(root);

            // convert doc to xml stirng
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
            xmlData = writer.getBuffer().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return xmlData;
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;
         
        try {
            Socket server = new Socket(host, port);

            // get stream
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            // data process
            Employee employee = new Employee(123, "John", 30);

            out.writeUTF(convertObject2XMLString(employee));
            // close
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
