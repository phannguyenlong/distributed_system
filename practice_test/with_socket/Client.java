package practice_test.with_socket;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ServerService stub = (ServerService) registry.lookup("ServerService"); // same name when server register to RMI server

            System.out.print("Input username: ");
            String username = scanner.nextLine();
            System.out.print("Input password: ");
            String password = scanner.nextLine();
            if (!stub.authentication(username, password)) {
                System.out.println("you dont have account");
                
                System.out.println("=========Createing new account=========");
                System.out.print("Input username: ");
                String user = scanner.nextLine();
                System.out.print("Input password: ");
                String pass = scanner.nextLine();
                if (!stub.createNewAccount(user, pass)) {
                    System.out.println("Account exist. Try again");
                }
                System.out.println("Account created. Please login again");
                scanner.close();
                return;
            }

            String clientSymbol = stub.getClientToken();
            System.out.println(clientSymbol);
            
            while (stub.isWin() == null) {
                System.out.print("Player " + clientSymbol + " choose your move[1 - 3]: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (!stub.chooseMove(x, y, clientSymbol)) {
                    System.out.println("Already move Or you must wait for your openent");
                    continue;
                }

                System.out.println(stub.display());
            }
            System.out.println("Game Over. Player " + stub.isWin() + " win!" );

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
