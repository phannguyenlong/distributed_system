package hw2;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSON {
    public static void main(String[] args) {
        // create customer
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(12, 2000));
        accounts.add(new Account(13, 3000));

        Customer customer = new Customer(1, 20, "Long", accounts);

        JSONObject jo = new JSONObject();

        // add id
        jo.put("id", customer.getId());

        // add age
        jo.put("age", customer.getAge());

        // add names
        jo.put("name", customer.getName());

        // add account
        JSONArray accountbArr = new JSONArray();
        for (Account account : customer.getAccounts()) {
            JSONObject accountObj = new JSONObject();
            accountObj.put("accountID", account.getAccountID());
            accountObj.put("balance", account.getBalance());

            // add to accoutArr
            accountbArr.add(accountObj);
        }
        jo.put("accounts", accountbArr);

        System.out.println(jo.toJSONString());
        
        // write to File
        try {
            PrintWriter printwWriter = new PrintWriter("src/hw2/customer.json");
            printwWriter.write(jo.toJSONString());
            printwWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
