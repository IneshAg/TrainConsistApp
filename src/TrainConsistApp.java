import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main class
public class TrainConsistApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Welcome message
        System.out.println("=== Train Consist Management App ===");

        // Initialize empty consist (dynamic list)
        List<String> trainConsist = new ArrayList<>();

        // Display initial count
        System.out.println("Train initialized successfully.");
        System.out.println("Initial bogie count: " + trainConsist.size());

        // Simple interaction loop (basic UC1 extension)
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Bogie");
            System.out.println("2. View Bogie Count");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.println("Enter bogie type (Sleeper / AC Chair / First Class / Goods):");
                    String bogie = sc.nextLine();

                    trainConsist.add(bogie);
                    System.out.println("Bogie added successfully.");
                    break;

                case 2:
                    System.out.println("Current bogie count: " + trainConsist.size());
                    break;

                case 3:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}