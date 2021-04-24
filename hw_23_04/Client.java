import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        Scanner reader = null;
        int serverPort = 9999;
        try {
            socket = new Socket("127.0.0.1", serverPort);
            reader = new Scanner(System.in);
            // Read data from server
            System.out.println("===============Current Data==============");
            FileWriter writer = new FileWriter(new File("D:/Long_Document/VGU-CS/excercise/distributed_system/hw_23_04/advertising_client.csv"));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String line = null;
            boolean done = false;
            while (!done) {
                try {
                    line = in.readUTF();
                    if ((done = line.equals("bye")) == false) {
                        writer.write(line + "\n");
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    done = true;
                }
            }
            writer.close();
            System.out.print("Input data (seprate by ','): ");
            line = reader.nextLine();
            out.writeUTF(line);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("ALready closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
