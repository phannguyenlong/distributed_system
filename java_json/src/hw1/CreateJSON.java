package hw1;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSON {
    public static void main(String[] args) {
        // create customer
        List<String> names = new ArrayList<>();
        names.add("Long");
        names.add("007");
        List<String> addresses = new ArrayList<>();
        addresses.add("HCM");
        addresses.add("HN");

        Customer customer = new Customer(1, 20, names, addresses);

        JSONObject jo = new JSONObject();

        // add id
        jo.put("id", customer.getId());

        // add age
        jo.put("age", customer.getAge());

        // add names
        JSONArray nameArr = new JSONArray();
        for (String name : customer.getNames()) {
            nameArr.add(name);
        }
        jo.put("names", nameArr);

        // add names
        JSONArray addressArr = new JSONArray();
        for (String address : customer.getAddresses()) {
            addressArr.add(address);
        }
        jo.put("addresses", addressArr);

        System.out.println(jo.toJSONString());
        
        // write to file
        // write to File
        try {
            PrintWriter printwWriter = new PrintWriter("src/hw1/customer.json");
            printwWriter.write(jo.toJSONString());
            printwWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
