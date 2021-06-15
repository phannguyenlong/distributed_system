import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {
    public static void main(String[] args) {
        try {
            // read file to Object
            Object obj = new JSONParser().parse(new FileReader("src/test.json"));
            // covert Object to Json
            JSONObject jo = (JSONObject) obj;

            // get name
            String name = jo.get("lastName").toString() + " " + jo.get("firstName").toString();
            System.out.println("Name: " + name);

            // get age
            int age = ((Long) jo.get("age")).intValue(); // cause return long => conver to int
            System.out.println("Age: " + age);

            // get address (sub json object)
            JSONObject address = (JSONObject) jo.get("address");
            String city = (String) address.get("city");
            String district = (String) address.get("district");
            String street = (String) address.get("street");
            System.out.println(String.format("Address: %s, %s, %s", street, district, city));

            // get contact (array of Object)
            JSONArray contact = (JSONArray) jo.get("contact");
            for (int i = 0 ; i < contact.size(); i++) {
                JSONObject info = (JSONObject) contact.get(i);
                String type = (String) info.get("type");
                String number = (String) info.get("number");
                System.out.println(String.format("Contact%d: %s (%s)", i + 1, number, type));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
