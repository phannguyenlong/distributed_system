package hw_16_04;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.*;

public class Server {
    public static void main(String[] args) {
        String url2 = "jdbc:mysql://localhost:3306/distributed?user=guest&password=password";
        Connection conn2 = null;
        try {
            conn2 = DriverManager.getConnection(url2);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if (conn2 != null) {
            System.out.println("Connected to the database");
        }

        int port = 9999;
        ServerSocket socket = null;
        System.out.println("Server is running on port " + port);
        try {
            socket = new ServerSocket(port);
            while (true) {
                Socket client = socket.accept();
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                Student std = (Student) in.readObject();
                System.out.println(std);

                String query = "INSERT INTO student (name, id, year, gender) VALUE ('" + std.getName() + "', '"
                        + std.getId() + "', '" + std.getYear() + "', '" + std.getGender() + "');";
                Statement statement = conn2.createStatement();
                statement.executeUpdate(query);

                client.close(); // close connecttion with server
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("run this");
                e.printStackTrace();
            }
        }
    }
}