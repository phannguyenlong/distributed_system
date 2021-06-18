import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import service.UserService;

public class Client {
    public static void main(String[] args) {
        String WS_URL = "http://localhost:8080/ws/users" + "?wsdl"; // the url that server has register
        try {
            // Create URL of .wsdl file
            URL wsdlURL = new URL(WS_URL);

            // Create Qname for query (input http://package_name, class_name + Service)
            // or can acc the url above to see the name
            QName qname = new QName("http://service/", "UserServiceImpleService");

            // look up for service and get the Service at type Service
            Service service = Service.create(wsdlURL, qname);

            // Pass the interface and model beans to client to know how to use
            UserService userService = service.getPort(UserService.class);

            // use Server service
            int sum = userService.sum(10, 20);
            System.out.println("Sum is: " + sum);

            int minus = userService.minus(10, 20);
            System.out.println("Minus is: " + minus);

            int multiply = userService.multiply(10, 20);
            System.out.println("Multiply is: " + multiply);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
