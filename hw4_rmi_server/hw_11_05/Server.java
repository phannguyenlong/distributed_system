package hw4_rmi_server.hw_11_05;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

public class Server implements ServerService {
    Connection databaseConnect;

    public Server() throws Exception {
        this.databaseConnect = connectToDatabase("guest", "password");
        if (databaseConnect != null) {
            System.out.println("Connected to the database");
        } else {
            throw new Exception("Cannot connect to database");
        }
    }

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

    @Override
    public List<Media> getMedia(String type) {
        List<Media> medias = new ArrayList<>();
        String query = type.equals("book") ? "SELECT * FROM book" : "SELECT * FROM newspaper";

        Statement st;
        try {
            st = databaseConnect.createStatement();
            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                if (type.equals("book"))
                    medias.add(new Book(res.getString("name"), res.getString("author")));
                else if (type.equals("newspaper"))
                    medias.add(new Book(res.getString("name"), res.getString("type")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return medias;
    }

    @Override
    public void createMedia(String type, String name, String other) throws RemoteException {
        String query = null;
        if (type.equals("book"))
            query = "INSERT INTO book (name, author) VALUE (?, ?)";
        else if (type.equals("newspaper"))
            query = "INSERT INTO newspaper (name, type) VALUE (?, ?)";

        if (query != null) {
            try {
                PreparedStatement st = databaseConnect.prepareStatement(query);
                st.setString(1, name);
                st.setString(2, other);
                st.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // create instant of implemnetation
        Server obj = new Server();

        ServerService stub = (ServerService) UnicastRemoteObject.exportObject(obj, 1099);

        // Register stub to RMI server
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("ServerService", stub); // ServerSevice will be the name in RMI server when client will lookup

        System.out.println("Server ready");
    }
}
