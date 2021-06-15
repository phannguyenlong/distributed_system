package hw1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJSON {
    public static void main(String[] args) {
        try {
            // read file to Object
            Object obj = new JSONParser().parse(new FileReader("src/hw1/customer.json"));
            // covert Object to Json
            JSONObject jo = (JSONObject) obj;

            // read id
            int id = ((Long) jo.get("id")).intValue();
            // read age
            int age = ((Long) jo.get("age")).intValue();
            
            // read name
            List<String> names = new ArrayList<>();
            JSONArray nameArr = (JSONArray) jo.get("names");
            for (int i = 0; i < nameArr.size(); i++) {
                names.add((String) nameArr.get(i));
            }

            //read addr
            List<String> addresses = new ArrayList<>();
            JSONArray addressArr = (JSONArray) jo.get("names");
            for (int i = 0; i < addressArr.size(); i++) {
                addresses.add((String) addressArr.get(i));
            }

            // create customer
            Customer customer = new Customer(id, age, names, addresses);
            System.out.println(customer);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
