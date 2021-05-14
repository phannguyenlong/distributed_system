package practice_test.with_socket;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerImp implements ServerService, Serializable {
    private Game game;
    private int numerOfClient = 0;
    private int clientNumber;
    private String[] clientSymbol = { "X", "O" };
    Connection databaseConnect;

    public ServerImp() {
        this.databaseConnect = connectToDatabase("guest", "password");
        game = new Game();
    }

    public static Connection connectToDatabase(String user, String password) {
        String databaseUrl = "jdbc:mysql://localhost:3306/distributed?user=" + user + "&password=" + password;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        System.out.println("Connected to database");
        return conn;
    }

    public boolean authentication(String username, String password) {
        try {
            PreparedStatement st = databaseConnect
                    .prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?");
            st.setString(1, username);
            st.setString(2, password);
            System.out.println(st);
            ResultSet res = st.executeQuery();

            if (res.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean createNewAccount(String username, String password) {
        try {
            PreparedStatement st = databaseConnect
                    .prepareStatement("INSERT INTO login (username, password) VALUE (?, ?);");
            st.setString(1, username);
            st.setString(2, password);
            System.out.println(st);
            st.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean chooseMove(int x, int y, String player) throws RemoteException {
        return game.setMove(x, y, player);
    }

    @Override
    public String isWin() {
        return game.checkWin();
    }

    @Override
    public String display() throws RemoteException {
        return game.toString();
    }

    @Override
    public String getClientToken() throws RemoteException {
        clientNumber = numerOfClient;
        numerOfClient++;
        return clientSymbol[clientNumber];
    }
}
