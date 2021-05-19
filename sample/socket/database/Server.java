package sample.socket.database;

import java.io.*;
import java.net.*;
import java.sql.*;

public class Server {
	public static Connection connectToDatabase(String user, String password) {
		String databaseUrl = "jdbc:mysql://localhost:3306/distributed?user=" + user + "&password=" + password;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		try {
			Connection databaseConnect = connectToDatabase("guest", "password");
			int port = 9999;

			ServerSocket socket = new ServerSocket(port);
			System.out.println("Server run on port " + port);

			Socket client = socket.accept();
			DataInputStream in = new DataInputStream(client.getInputStream());

			String name = in.readUTF();
			int age = Integer.parseInt(in.readUTF());

			try {
				PreparedStatement st = databaseConnect.prepareStatement("INSERT INTO student1 VALUES (?,?)");
				st.setString(1, name);
				st.setInt(2, age);
				st.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
