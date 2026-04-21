import java.util.ArrayList;
import java.util.Arrays;

public class Student{
    public String name;
    public Integer rollnum;
    public String branch;
    public ArrayList<Integer> issuedBooks = new ArrayList<Integer>(Arrays.asList(0,0,0));

    Student(String name, Integer rollnum, String branch) {
        this.name = name;
        this.rollnum = rollnum;
        this.branch = branch;
    }

    public String toString() {
        return this.name + "," + this.rollnum + "," + this.branch + "," + this.issuedBooks.toString();
    }

    public static Student toStudent(String studentString) {
        String[] details = studentString.split(",");
        Student student = new Student(details[0], Integer.parseInt(details[1]), details[2]);
        student.issuedBooks = new ArrayList<>();
        for(int i=3; i<details.length; i++) {
            student.issuedBooks.add(Integer.parseInt(details[i].replaceAll("\\[|\\]", "").trim()));
        }
        return student;
    }
}
