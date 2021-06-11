import org.apache.xmlrpc.*;

public class Server {
    public int sum(int x, int y) {
        return x + y;
    }
    public int minus(int x, int y) {
        return x - y;
    }
    public int devide(int x, int y) {
        return x / y;
    }
    public int multiply(int x, int y) {
        return x * y;
    }

    public static void main(String[] args) {
        int port = 9999;
        try {
            WebServer webServer = new WebServer(port);
            webServer.addHandler("server", new Server());
            webServer.start();

            System.out.println("Server running on port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
