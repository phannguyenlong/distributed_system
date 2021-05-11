package hw4_rmi_server.hw_11_05;

import java.io.Serializable;

public class Media implements Serializable {
    private String name;

    public Media(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
