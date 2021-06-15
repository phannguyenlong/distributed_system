package hw1;

import java.util.List;

public class Customer {
    private int id, age;
    private List<String> names, addresses;

    public Customer(int id, int age, List<String> names, List<String> addresses) {
        this.id = id;
        this.age = age;
        this.names = names;
        this.addresses = addresses;
    }
    

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public int getAge() {
        return age;
    }



    public void setAge(int age) {
        this.age = age;
    }



    public List<String> getNames() {
        return names;
    }



    public void setNames(List<String> names) {
        this.names = names;
    }



    public List<String> getAddresses() {
        return addresses;
    }



    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }



    @Override
    public String toString() {
        String display = String.format("@Customer\nID: %d\nAge: %d\n", id, age);
        for (String name : names) {
            display += String.format("Name: %s\n", name);
        }
        for (String address : addresses) {
            display += String.format("Address: %s\n", address);
        }
        
        return display;
    }
}
