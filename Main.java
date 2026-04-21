import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        Library.loadBooks();
        Students.loadStudents();
        MenuManager.mainMenu();
        
        int choice = MenuManager.menuChoiceHandler(br);
        while(choice != 6) {
            switch(choice) {
                case 1:
                    Book b = InputHandler.inputBook(br);
                    Library.addBook(b);
                    choice = MenuManager.menuChoiceHandler(br);
                    break;
                case 2: 
                    Student s = InputHandler.inputStudent(br);
                    Students.addStudent(s);
                    choice = MenuManager.menuChoiceHandler(br);
                    break;
                case 3:
                    Integer rollnum = InputHandler.inputStudentRollNum(br);
                    Integer bookUUID = InputHandler.inputBookUUID(br);     
                    Library.issueBook(rollnum, bookUUID);  
                    choice = MenuManager.menuChoiceHandler(br);
                    break;
                case 4:
                    // Code to return book
                    choice = MenuManager.menuChoiceHandler(br);
                    break;
                case 5:
                    MenuManager.showBooks();
                    choice = MenuManager.menuChoiceHandler(br);
                    break;  
                default:
                    System.out.println("Invalid choice. Please try again.");
                    choice= MenuManager.menuChoiceHandler(br);
            }
        }
        System.out.println("Exiting the system. Goodbye!");
    }
}