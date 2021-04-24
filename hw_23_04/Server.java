import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) {
        int port = 9999;
        String path = "D:/Long_Document/VGU-CS/excercise/distributed_system/hw_23_04/advertising.csv";
        ServerSocket socket = null;
        System.out.println("Server is running on port " + port);
        try {
            socket = new ServerSocket(port);
            while (true) {
                Socket client = socket.accept();
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                DataInputStream in = new DataInputStream(client.getInputStream());

                ArrayList<Advertising> datas = ReadCSVFile.readCsv(path);
                for (Advertising data : datas) {
                    out.writeUTF(data.toString());
                }
                out.writeUTF("bye");

                String line = in.readUTF();
                Advertising advert = new Advertising();
                try {
                    StringTokenizer tokens = new StringTokenizer(line, ",");
                    advert.setTv(Double.parseDouble(tokens.nextToken()));
                    advert.setRadio(Double.parseDouble(tokens.nextToken()));
                    advert.setNewspaper(Double.parseDouble(tokens.nextToken()));
                    advert.setSales(Double.parseDouble(tokens.nextToken()));
                } catch (Exception e) {
                    out.writeUTF("Wrong input syntax");
                }
                System.out.println(advert);
                ReadCSVFile.writeCSV(path, advert);

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
