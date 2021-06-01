package xml_java.hw01_06;

public class Book {
    private String title;
    private String publisher;
    private Author author;

    public Book(String title, String publisher, Author author) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
    }

    @Override
    public String toString() {
        return "@Book " + title + " | " + publisher + "\n" + author;
    }
}
