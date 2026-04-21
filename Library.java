import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static void loadBooks() throws IOException,FileNotFoundException{
        try (FileReader fileReader = new FileReader("books.txt")) {
            List<String> lines = fileReader.readAllLines();
            for(String line:lines){
                Book b = Book.toBook(line);
                books.add(b);
            }
        }
    }

    public static void issueBook(Integer rollnum, Integer bookUUID) {
        Student st = Students.findStudent(rollnum);
        if(st == null) {
            System.out.println("Student not found. Cannot issue book.");
            return;
        }
        for(Book b:books) {
            if(b.uuid.equals(bookUUID)) {
                if(st.issuedBooks.contains(bookUUID)) {
                    System.out.println("Student has already issued this book.");
                    return;
                }
                for(int i =0;i<3;i++) {
                    if(st.issuedBooks.get(i) == 0) {
                        st.issuedBooks.set(i, bookUUID);
                        break;
                    }
                }
                Students.writeStudents();
                System.out.println("Book issued successfully.");
                return;
            }
        }
        System.out.println("Book with the given UUID not found.");
    }

    public static void returnBook(Student st, Integer bookUUID) {
        if(st.issuedBooks.contains(bookUUID)) {
            st.issuedBooks.remove(bookUUID);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Student has not issued this book.");
        }
    }

    public static void addBook(Book bk) {
        books.add(bk);
        try (FileWriter fileWriter = new FileWriter("books.txt",true)) {
            fileWriter.append(bk.toString());
            fileWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Book added successfully.");
    }

}
