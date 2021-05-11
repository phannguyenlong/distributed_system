package hw4_rmi_server.sample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ServerService stub = (ServerService) registry.lookup("ServerService"); // same name when server register to RMI server

            System.out.println("Server say: " + stub.sayHello());

            System.out.println("Server caculate sum is: " + stub.sum(10, 15));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
