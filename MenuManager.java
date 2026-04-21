import java.io.BufferedReader;
import java.io.IOException;

public class MenuManager {
    public static void mainMenu(){
        System.out.println("Welcome to Library Management System");
        System.out.println("------------------------------------");
        System.out.println("1. Add Book");
        System.out.println("2. Add Student");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Show Books");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------");    
    }
    public static int menuChoiceHandler(BufferedReader br){
        if (br == null) {
            System.out.println("Input source is not available. Exiting.");
            return 6;
        }

        while (true) {
            System.out.println("Enter your choice: ");
            try {
                String input = br.readLine();
                if (input == null) {
                    System.out.println("No input received. Exiting.");
                    return 6;
                }

                int choice = Integer.parseInt(input.trim());
                if (choice >= 1 && choice <= 6) {
                    return choice;
                }

                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            } 
            catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            catch (IOException e) {
                System.out.println("Input error occurred. Exiting.");
                return 6;        
            }
        }
    }

    public static void showBooks(){
        for(Book b:Library.books){
            System.out.println(b.toString());
        }
    }

    public static void splashScreen() {
        System.out.println("░█░░░▀█▀░█▀▄░█▀▄░█▀█░█▀▄░█░█░░░█▄█░█▀█░█▀█░█▀█░█▀▀░█▀▀░█▀▄");
        System.out.println("░█░░░░█░░█▀▄░█▀▄░█▀█░█▀▄░░█░░░░█░█░█▀█░█░█░█▀█░█░█░█▀▀░█▀▄");
        System.out.println("░▀▀▀░▀▀▀░▀▀░░▀░▀░▀░▀░▀░▀░░▀░░░░▀░▀░▀░▀░▀░▀░▀░▀░▀▀▀░▀▀▀░▀░▀");                                                  
    }
}
