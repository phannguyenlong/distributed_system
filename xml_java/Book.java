package xml_java;

public class Book {
    private String category, author, title;
    private String year, price;

    public Book(String category, String author, String title, String year, String price) {
        this.category = category;
        this.author = author;
        this.title = title;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return "@Book " + category + " | " + author + " | " + title + " | " + year + " | " + price;
    }
}
