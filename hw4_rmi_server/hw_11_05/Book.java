package hw4_rmi_server.hw_11_05;

public class Book extends Media {
    private String author;

    public Book(String name, String author) {
        super(name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return super.getName() + " | " + this.author;
    }
}
