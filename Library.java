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

    public static void issueBook(Student st, Integer bookUUID) {
        if(st.issuedBooks.size() < 3) {
            st.issuedBooks.add(bookUUID);
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Student has already issued 3 books. Cannot issue more.");
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Book added successfully.");
    }

}
