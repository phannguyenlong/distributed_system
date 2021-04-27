package hw_27_04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class Client {
    public static void main(String[] args) {
        int serverPort = 8080;
        String host = "127.0.0.1";
        Socket server = null;

        try {
            server = new Socket(host, serverPort);
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            Scanner reader = new Scanner(System.in);

            Thread output = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        System.out.println(in.readUTF());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            output.start();

            String message = "";
            while (!message.equals("bye")) {
                message = reader.nextLine();
                out.writeUTF(message);
                // System.out.println(in.readUTF());
            }

            in.close();
            out.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}