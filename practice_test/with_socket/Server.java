package practice_test.with_socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 9999;
        ServerSocket socket = null;
        System.out.println("Server is running on port " + port);
        try {
            socket = new ServerSocket(port);
            ServerImp obj = new ServerImp();
            while (true) {
                Socket client = socket.accept();

                Thread t = new Thread(new ServerThread(obj, client));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
