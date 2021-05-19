package sample.socket.multithread;
import java.io.DataOutputStream;
import java.net.*;
public class ServerThread implements Runnable{
	Socket client = null;
	// ArrayList<Socket> clientlist;
	
	public ServerThread(Socket client) {
		this.client=client;
	}
	
	@Override
	public void run() {
		System.out.println("Connect from client ");
		try {
			while(true) {
				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				out.writeUTF("hello");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
