package hw_16_04;
import java.io.Serializable;

/**
 * Testing class (not for main app)
 */
public class Student implements Serializable {
    private String id, name, gender, year;

    public Student(String id, String name, String gender, String year) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.year = year;
    }
    

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return id + "|" + name + "|" + gender + "|" + year; 
    }
}
