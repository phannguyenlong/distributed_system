package sample.socket.multithread;
import java.net.*;
public class Server {
	 private int port = 9999;
	 ServerSocket socket = null;
	 
	 public Server() throws Exception {
		 this.socket = new ServerSocket(port);
		 System.out.println("Connect to: " + port);
	 }
	 
	 public ServerSocket getSocket() {
	        return socket;
	 }
	   public static void main(String[] args) {
	        try {
	            ServerSocket socket = (new Server()).getSocket();
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
