package sample.socket.withObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Server {
    // Connect with database
    public static Connection connectToDatabase(String user, String password) {
        // connect to database
        String databaseUrl = "jdbc:mysql://localhost:3306/distributed?user=" + user + "&password=" + password;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return conn;
    }

    public static boolean updateDatabase(Connection conn, String name, int age) {
        String query = "INSERT INTO student1(name, age) VALUE (?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query); // create prepared statement
            st.setString(1, name);
            st.setInt(2, age);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static void main(String[] args) {
        ServerSocket server = null;

        // Create Object Student
        Student std = new Student("Server", 2001);
        try {
            // init and config server run port 9999
            int PORT = 9999;
            server = new ServerSocket(PORT);
            System.out.println("Server runing on port " + PORT);

            while (true) {
                Socket client = server.accept(); // accept connect from client
                System.out.println(std);

                // Get input stream of Client
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                // write data to client
                out.writeObject(std);

                // Get output stream
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                // recice data from client
                System.out.print("Object from client: ");
                Student recieve_std = (Student) in.readObject();
                System.out.println(recieve_std.toString());
                
                // Save student to database
                Connection conn = connectToDatabase("guest", "password");
                if (updateDatabase(conn, recieve_std.getName(), recieve_std.getAge())) {
                    System.out.println("Success update database");
                } else {
                    System.out.println("Cannot add student to database");
                }

                // close connect with client
                client.close();

                // close stream
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close connect with servewr
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
