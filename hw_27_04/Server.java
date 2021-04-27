package hw_27_04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int port = 8080;
    ServerSocket socket = null;

    public Server() throws Exception {
        this.socket = new ServerSocket(port);
        System.out.println("Server is run on port " + port);
    }
    
    public ServerSocket getSocket() {
        return socket;
    }

    public static void main(String[] args) {
        ArrayList<Socket> clientList = new ArrayList<>();
        try {
            ServerSocket socket = (new Server()).getSocket();
            while (true) {
                Socket client = socket.accept();
                clientList.add(client);
                Thread worker = new Thread(new ServerThread(client, clientList, "Client " + clientList.size()));
                worker.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
