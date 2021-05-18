package sample.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            // init and config server run port 9999
            int PORT = 9999;
            server = new ServerSocket(PORT);
            System.out.println("Server runing on port " + PORT);

            while (true) {
                Socket client = server.accept(); // accept connect from client
                // Get input and output stream of Client
                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                // write data to client
                out.writeUTF("Hello from server");

                // recice data from client
                System.out.print("Message from client: ");
                System.out.println(in.readUTF());

                // close connect with client
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connect with servewr
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
