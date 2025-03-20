public class Student {
    private int id;
    private String name, email, grade;
    private int age;

    public Student(int id, String name, String email, int age, String grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.grade = grade;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
}
