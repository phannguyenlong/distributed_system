package sample.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String HOST = "localhost";
        int PORT = 9999;

        Socket socket = null;
        try {
            // Connect client with server
            socket = new Socket(HOST, PORT);
            
            // Get input and output Stream
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // read data from server
            System.out.print("Data from server: ");
            System.out.println(in.readUTF());

            // send data to server
            out.writeUTF("Hello from client");

            // close stream
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the socket
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
