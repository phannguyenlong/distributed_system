
import java.util.Scanner;
import java.util.Vector;

import org.apache.xmlrpc.*;

public class Client {
    public static void main(String[] args) {
        String[] options = { "sum", "minus", "devide", "multiply" };
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:9999"); // connect to webserver on port 9999
            Scanner scanner = new Scanner(System.in);

            String option = "";
            while (!option.equals("5")) {
                System.out.print("1. plus\n2. minus\n3. devide\n4. multipy\n5. exit\nYour option: ");
                option = scanner.nextLine();

                System.out.print("Input first number: ");
                int x = Integer.parseInt(scanner.nextLine());

                System.out.print("Input second number: ");
                int y = Integer.parseInt(scanner.nextLine());

                // params
                Vector<Integer> params = new Vector<>();
                params.addElement(x);
                params.addElement(y);

                // call function on server (which is "register_name".function_name)
                int res = (Integer) server.execute("server." + options[Integer.parseInt(option) - 1], params); // return Object => cast to Integer
                System.out.println("Result is: " + res);
            }
            
            scanner.close();

        } catch (Exception e) {
        }
    }    
}
