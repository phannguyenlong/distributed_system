package hw4_rmi_server.hw_11_05;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void addMedia(ServerService stub, Scanner scanner) throws RemoteException {
        System.out.print("What type (1-book, 2-newspaper): \nYour option");
        // Scanner scanner = new Scanner(System.in);
        String op = scanner.nextLine();
        if (op.equals("1")) {
            System.out.print("Input name: ");
            String name = scanner.nextLine();
            System.out.print("Input author: ");
            String author = scanner.nextLine();
            stub.createMedia("book", name, author);
            
        } else if (op.equals("2")) {
            System.out.print("Input name: ");
            String name = scanner.nextLine();
            System.out.print("Input type : ");
            String type = scanner.nextLine();
            stub.createMedia("newspaper", name, type);
        }
        // scanner.close();
    }
    
    public static void getMedia(ServerService stub, Scanner scanner) throws RemoteException {
        System.out.print("What type (1-book, 2-newspaper): \nYour option: ");
        // Scanner scanner = new Scanner(System.in);
        String op = scanner.nextLine();
        List<Media> medias = new ArrayList<>();

        if (op.equals("1")) {
            medias = stub.getMedia("book");
            
        } else if (op.equals("2")) {
            medias = stub.getMedia("newspaper");
        }
        // scanner.close();

        for (Media media : medias)
            System.out.println(media);
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ServerService stub = (ServerService) registry.lookup("ServerService"); // same name when server register to RMI server

            Scanner scanner = new Scanner(System.in);
            String option = null;            
            do {
                System.out.print("What is your option: \n1. Add media\n2. Get media\n3. exit\nYour option: ");
                option = scanner.nextLine();
                if (option.equals("1"))
                    addMedia(stub, scanner);
                else if (option.equals("2"))
                    getMedia(stub, scanner);
                else
                    System.out.println("Wrong option");

                // System.out.println("OPTIN" + option);
            } while (!option.equals("3"));

            System.out.println("RUNNNNNNNNN");
            scanner.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
