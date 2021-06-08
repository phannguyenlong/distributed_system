import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Tools {
    public static String Object2XML(List<Student> students) {
        String xml = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // create root elemet
            Element rootElement = doc.createElement("stduents");

            for (Student std : students) {
                Element student = doc.createElement("student");

                //id
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(std.getId())));
                student.appendChild(id);
                // name
                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(std.getName()));
                student.appendChild(name);
                // grade
                Element grade = doc.createElement("grade");
                grade.appendChild(doc.createTextNode(String.valueOf(std.getGrade())));
                student.appendChild(grade);

                rootElement.appendChild(student);
            }

            // add root to doc
            doc.appendChild(rootElement);

            // convert doc to xml stirng
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            xml = writer.getBuffer().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xml;
    }
    
    public static List<Student> xml2Object(String xml) {
        List<Student> students = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(new InputSource(new StringReader(xml)));

            // get root element
            Element rootElement = (Element) doc.getDocumentElement();
            NodeList stds = rootElement.getChildNodes();

            // loop through child
            for (int i = 0; i < stds.getLength(); i++) {
                Element std = (Element) stds.item(i);

                // create employee
                students.add(new Student(Integer.parseInt(std.getElementsByTagName("id").item(0).getTextContent()),
                        Float.parseFloat(std.getElementsByTagName("grade").item(0).getTextContent()),
                        std.getElementsByTagName("name").item(0).getTextContent()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return students;
    }
    
    public static void synchornize(Connection databaseConnect, String serverName) {
        String host = "127.0.0.1";
        int port = 8888;

        Socket socket = null;
        try {
            // connect to other server
            socket = new Socket(host, port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("server"); // set connect mode to server

            // get data from DB
            PreparedStatement st = databaseConnect.prepareStatement("select * from student");
            ResultSet res = st.executeQuery();
            List<Student> students = new ArrayList<>();
            while (res.next()) {
                students.add(new Student(res.getInt("id"), res.getFloat("grade"), res.getString("name")));
            }

            // sending data over socket
            System.out.println("Sending data...");
            String xml = Tools.Object2XML(students);
            out.writeUTF(xml);

            // reciever data from another server
            System.out.println("Recieving data...");
            xml = in.readUTF();
            List<Student> socket_student = Tools.xml2Object(xml);

            // compare 2 list of data and adding
            System.out.println("Processing...");
            for (Student std : socket_student) {
                boolean isHasStd = false;
                for (Student std_db : students) {
                    if (std_db.getId() == std.getId())
                        isHasStd = true;
                }
                if (!isHasStd) {
                    System.out.println(std.getId());
                    // add to DB
                    st = databaseConnect.prepareStatement("insert into student values (?, ?, ?)");
                    st.setInt(1, std.getId());
                    st.setString(2, std.getName());
                    st.setFloat(3, std.getGrade());
                    System.out.println(st);
                    st.executeUpdate();
                }
            }
            System.out.println("Synchornize finish");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void handleSynchornize(Socket socket, DataInputStream in, DataOutputStream out, Connection databaseConnect, String serverName) throws Exception {
        // recieving data form other server
        System.out.println("Recieving data...");
        String xml = in.readUTF();

        List<Student> students = Tools.xml2Object(xml);

        // get data from database
        PreparedStatement st = databaseConnect.prepareStatement("select * from student");
        ResultSet res = st.executeQuery();
        List<Student> db_student = new ArrayList<>();
        while (res.next()) {
            db_student.add(new Student(res.getInt("id"), res.getFloat("grade"), res.getString("name")));
        }

        // sending data over socket
        System.out.println("Sending data...");
        xml = Tools.Object2XML(db_student);
        out.writeUTF(xml); // write back data
        
        // compare 2 list of data and adding which dont have
        System.out.println("Processing...");
        for (Student std : students) {
            boolean isHasStd = false;
            for (Student std_db : db_student) {
                if (std_db.getId() == std.getId())
                    isHasStd = true;
            }
            if (!isHasStd) {
                System.out.println(std.getId());
                // add to DB
                st = databaseConnect.prepareStatement("insert into student values (?, ?, ?)");
                st.setInt(1, std.getId());
                st.setString(2, std.getName());
                st.setFloat(3, std.getGrade());
                System.out.println(st);
                st.executeUpdate();
            }
        }

        System.out.println("Synchornize done");
    }
}
