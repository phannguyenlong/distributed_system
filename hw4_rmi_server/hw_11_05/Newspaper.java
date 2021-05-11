package hw4_rmi_server.hw_11_05;

public class Newspaper extends Media {
    private String type;

    public Newspaper(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.getName() + " | " + this.type;
    }
}
