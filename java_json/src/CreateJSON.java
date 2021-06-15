import java.io.PrintWriter;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSON {
    public static void main(String[] args) {       
        // cauase type of it is HashMap
        JSONObject rootObject = new JSONObject();

        // add data to hashMap
        rootObject.put("firstName", "Long");
        rootObject.put("lastName", "Phan Nguyen");
        rootObject.put("age", 25);

        // add suboject (address)
        JSONObject address = new JSONObject();
        address.put("city", "HCM");
        address.put("district", "Thu Duc");
        address.put("street", "Kha Van Can");
        // add to rootObject
        rootObject.put("address", address);

        // sub array of object (contact)
        JSONArray contact = new JSONArray();
        // first info
        HashMap<String, Object> info1 = new HashMap<>();
        info1.put("type", "home");
        info1.put("number", "0000111122222");
        // second info
        HashMap<String, Object> info2 = new HashMap<>();
        info2.put("type", "fax");
        info2.put("number", "0000111122222");
        // add to infos
        contact.add(info1);
        contact.add(info2);

        // add to rootObject
        rootObject.put("contact", contact);


        // print out
        System.out.println(rootObject.toJSONString());
        
        // write to File
        try {
            PrintWriter printwWriter = new PrintWriter("src/test.json");
            printwWriter.write(rootObject.toJSONString());
            printwWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
