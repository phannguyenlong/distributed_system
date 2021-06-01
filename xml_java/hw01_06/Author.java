package xml_java.hw01_06;

public class Author {
    private String name;
    private int age;

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "@Author " + name + " | " + age;
    }
}
