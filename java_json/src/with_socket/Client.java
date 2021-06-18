package with_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.json.simple.JSONObject;

public class Client {
    public static String Object2Json(Book book) {
        JSONObject bookObj = new JSONObject();
        // add title
        bookObj.put("title", book.getTitle());
        // add publisher
        bookObj.put("publisher", book.getPublisher());
        
        // add author
        Author author = book.getAuthor();
        JSONObject authorObj = new JSONObject();
        // add name
        authorObj.put("name", author.getName());
        authorObj.put("age", author.getAge());

        bookObj.put("author", authorObj);

        return bookObj.toJSONString();
    }
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;

        try {
            Socket server = new Socket(host, port);

            // get Stream
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            // create book
            Author author = new Author("Long", 21);
            Book book = new Book("The end of the world", "Depression", author);

            System.out.println(in.readUTF());
            out.writeUTF(Object2Json(book));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
