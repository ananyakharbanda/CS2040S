import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Student {
    public String name;
    public int rollNo;
    public int age;   
    
    public Student(String name, int rollNo, int age) {
        this.name = name;
        this.rollNo = rollNo;
        this.age = age;
    }
    
    @Override
    public String toString() {
        String toReturn = this.name + " " + Integer.toString(this.rollNo) + " " + Integer.toString(this.age);
        return toReturn;
    }
}

class Classroom {
    public static void main (String[] args) {
        List<Student> l = new ArrayList<>();
        Student s1 = new Student("aa", 11, 19);
        Student s2 = new Student("nn", 2, 20);
        Student s3 = new Student("k vyi", 3, 21);
        l.add(s1);
        l.add(s2);
        l.add(s3);
    
        l.sort(Comparator.comparingInt(student -> student.age));
        
        for (Student s : l) {
            System.out.println(s);
        } 
        l.sort(Comparator.comparingInt((Student student) -> student.rollNo).reversed());
 
        for (Student s : l) {
            System.out.println(s);
        }
        l.sort(Comparator.comparing(student -> student.name));
        
        for (Student s : l) {
            System.out.println(s);
        }
    }
}
