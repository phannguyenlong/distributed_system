package sample.socket.multithread;
import java.io.DataInputStream;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		 int serverPort = 9999;
	     String host = "127.0.0.1";
	     Socket server = null;
	     
	     try {
	            server = new Socket(host, serverPort);
				DataInputStream in = new DataInputStream(server.getInputStream());

				// countinue recieve from server
	            while (true) {
	                System.out.println(in.readUTF());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }   
	}
