import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;

        Socket server = null;
        
        try {
            server = new Socket(host, port);
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            out.writeUTF("client");

            System.out.println(in.readUTF());
            out.writeUTF(scanner.nextLine());
            System.out.println(in.readUTF());

            scanner.close();
            out.close();
            in.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
