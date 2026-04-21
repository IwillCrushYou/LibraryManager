import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Students {
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static void loadStudents() throws IOException,FileNotFoundException{
        try (FileReader fileReader = new FileReader("students.txt")) {
            List<String> lines = fileReader.readAllLines();
            for(String line:lines){
                Student s = Student.toStudent(line);
                students.add(s);
            }
        }
    }

    public static void addStudent(Student st) {
        students.add(st);
        try (FileWriter fileWriter = new FileWriter("students.txt",true)) {
            fileWriter.append(st.toString());
            fileWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Student added successfully.");
    }

}
