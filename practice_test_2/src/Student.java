public class Student {
    private int id;
    float grade;
    private String name;

    public Student(int id, float grade, String name) {
        this.id = id;
        this.grade = grade;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getGrade() {
        return grade;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
