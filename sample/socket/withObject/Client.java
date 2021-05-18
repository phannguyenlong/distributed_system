package sample.socket.withObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String HOST = "localhost";
        int PORT = 9999;

        Socket socket = null;

        // Create Student
        Student std = new Student("Client", 2000);
        try {
            // Connect client with server
            socket = new Socket(HOST, PORT);
            // Get output Stream
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // read data from server
            System.out.print("Data from server: ");
            Student recieve_std = (Student) in.readObject();
            System.out.println(recieve_std.toString());

            // Get input stream
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // send data to server
            out.writeObject(std);

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
