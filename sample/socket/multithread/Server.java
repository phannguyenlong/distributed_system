package sample.socket.multithread;
import java.net.*;
public class Server {
	   public static void main(String[] args) {
			try {
				int port = 9999;
				ServerSocket socket = new ServerSocket(port);
				System.out.println("Server run on port " + port);
	            while (true) {
	                Socket client = socket.accept();
	                
	                Thread worker = new Thread(new ServerThread(client));
	                worker.start();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	   }
}
