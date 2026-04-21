import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static Student findStudent(Integer rollnum) {
        for(Student s:students) {
            if(s.rollnum.equals(rollnum)) {
                return s;
            }
        }
        System.out.println("Student with the given roll number not found.");
        return null;
    }

    // // Updates full bracket values: [a, b, c]
    // public static boolean updateIssuedBooks(Student s) throws IOException {
    //     Path path = Path.of("students.txt");
    //     List<String> lines = Files.readAllLines(path);
    //     List<String> updated = new ArrayList<>();

    //     boolean changed = false;
    //     String keyPrefix = s.name + "," + s.rollnum + "," + s.branch + ",";
    //     String newBracket = "[" + s.issuedBooks.at + ", " + b + ", " + c + "]";

    //     for (String line : lines) {
    //         if (!changed && line.startsWith(keyPrefix)) {
    //             String newLine = line.replaceFirst("\\[[^\\]]*\\]", newBracket);
    //             updated.add(newLine);
    //             changed = true;
    //         } else {
    //             updated.add(line);
    //         }
    //     }

    //     if (changed) {
    //         Files.write(path, updated);
    //     }
    //     return changed;
    // }

    // Updates a single slot inside [x, y, z]
    public static boolean updateIssuedBookAtIndex(String fileName, String name, int roll, String branch, int index, int value) throws IOException {
        if (index < 0 || index > 2) {
            throw new IllegalArgumentException("index must be 0, 1, or 2");
        }

        Path path = Path.of(fileName);
        List<String> lines = Files.readAllLines(path);
        List<String> updated = new ArrayList<>();

        boolean changed = false;
        String keyPrefix = name + "," + roll + "," + branch + ",";

        for (String line : lines) {
            if (!changed && line.startsWith(keyPrefix)) {
                int left = line.indexOf('[');
                int right = line.indexOf(']');
                if (left == -1 || right == -1 || right <= left) {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }

                String inside = line.substring(left + 1, right);
                String[] parts = inside.split(",");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Expected 3 values in brackets: " + line);
                }

                int[] arr = new int[3];
                arr[0] = Integer.parseInt(parts[0].trim());
                arr[1] = Integer.parseInt(parts[1].trim());
                arr[2] = Integer.parseInt(parts[2].trim());

                arr[index] = value;
                String newBracket = "[" + arr[0] + ", " + arr[1] + ", " + arr[2] + "]";
                String newLine = line.substring(0, left) + newBracket + line.substring(right + 1);

                updated.add(newLine);
                changed = true;
            } else {
                updated.add(line);
            }
        }

        if (changed) {
            Files.write(path, updated);
        }
        return changed;
    }

}
