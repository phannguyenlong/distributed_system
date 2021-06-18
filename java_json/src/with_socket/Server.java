package with_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Server {
    public static Book JSON2Object(String json) {
        try {
            Object obj = new JSONParser().parse(json);
            JSONObject jo = (JSONObject) obj;

            // get title
            String title = (String) jo.get("title");

            // get publisher
            String publisher = (String) jo.get("publisher");

            // get author
            JSONObject authorObj = (JSONObject) jo.get("author");
            // get name
            String name = (String) authorObj.get("name");
            //get age
            int age = ((Long) authorObj.get("age")).intValue();

            //create author
            Author author = new Author(name, age);
            //create book
            Book book = new Book(title, publisher, author);

            return book;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        int port = 9999;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("Server run on port " + port);

            while (true) {
                Socket client = server.accept();
                
                // get stream
                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                out.writeUTF("hello");
                String json = in.readUTF();
                System.out.println(json);
                Book book = JSON2Object(json);
                System.out.println(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    
}
