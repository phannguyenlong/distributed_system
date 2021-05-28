package xml_java.hw28_02;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ReadStaff {
    public static void main(String[] args) {
        try {
            // read the file
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("xml_java/hw28_02/staff.xml"));

            // get staft
            Element rootElement = (Element) doc.getDocumentElement();
            NodeList staffs = rootElement.getElementsByTagName("staff");

            // loop thorugh child
            for (int i = 0; i < staffs.getLength(); i++) {
                Element staff = (Element) staffs.item(i);

                // dipslay content 
                System.out.println("@Staff " + staff.getAttribute("id"));
                System.out.println("Name: " + staff.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Salary: " + staff.getElementsByTagName("salary").item(0).getTextContent());
                System.out.println("Nickname: " + staff.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("salary: " + staff.getElementsByTagName("salary").item(0).getTextContent());
                System.out.println("currency: " + ((Element) staff.getElementsByTagName("salary").item(0)).getAttribute("currency") );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
