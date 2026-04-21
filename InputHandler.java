import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler {
    public static Student inputStudent(BufferedReader br) {
        System.out.println("Enter student details (name, rollnum, branch): ");
        Student student = null;
        try {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) {
                System.out.println("Input cannot be empty.");
                return null;
            }

            String[] details = line.split(",");
            if (details.length != 3) {
                System.out.println("Provide all fields.");
                return null;
            }

            String name = details[0].trim();
            int rollNum = Integer.parseInt(details[1].trim());
            String branch = details[2].trim();

            student = new Student(name, rollNum, branch);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Roll number should be an integer.");
        }
        catch (IOException e) {
            System.out.println("Invalid input. Please enter the details in the correct format.");        
        }
        return student;
    }

    public static Book inputBook(BufferedReader br) {
        System.out.println("Enter book details (author, title, publisher, isbn, uuid): ");
        Book book = null;
        try {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) {
                System.out.println("Input cannot be empty.");
                return null;
            }

            String[] details = line.split(",");
            if (details.length != 5) {
                System.out.println("Provide all fields.");
                return null;
            }

            String author = details[0].trim();
            String title = details[1].trim();
            String publisher = details[2].trim();
            String isbn = details[3].trim();
            int uuid = Integer.parseInt(details[4].trim());

            book = new Book(author, title, publisher, isbn, uuid);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. UUID should be an integer.");
        } catch (IOException e) {
            System.out.println("Invalid input. Please enter the details in the correct format.");
        }
        return book;
    }

}
