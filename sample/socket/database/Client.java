package sample.socket.database;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String[] args) {
		int serverPort = 9999;
		String host = "127.0.0.1";
		Socket server = null;

		try {
			server = new Socket(host, serverPort);
			DataOutputStream out = new DataOutputStream(server.getOutputStream());
			Scanner scanner = new Scanner(System.in);

			System.out.println("Input name: \n");
			String name = scanner.nextLine();
			out.writeUTF(name);
			System.out.println("Input age: \n");
			String age = scanner.nextLine();
			out.writeUTF(age);
			out.close();
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
