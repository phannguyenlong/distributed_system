package hw_27_04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {
    Socket client = null;
    ArrayList<Socket> clientList;
    String clientName;

    public ServerThread(Socket client, ArrayList<Socket> clientList, String clientName) {
        this.client = client;
        this.clientName = clientName;
        this.clientList = clientList;
    }
    
    @Override
    public void run() {
        System.out.println("Connect from client " + clientName);
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            // DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String message = "";
            while (!message.equals("Bye")) {
                message = in.readUTF();
                System.out.println(clientName + ": " + message);
                for (Socket otherClient : clientList) {
                    if (otherClient != this.client) {
                        DataOutputStream out = new DataOutputStream(otherClient.getOutputStream());
                        out.writeUTF(clientName + " said: " + message);
                    }
                }
            }

            System.out.println("RUN here?");

            in.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
