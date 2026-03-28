import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainConsistApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // UC1: Initialization
        System.out.println("=== Train Consist Management App ===");

        List<String> passengerBogies = new ArrayList<>();

        System.out.println("Train initialized successfully.");
        System.out.println("Initial bogie count: " + passengerBogies.size());

        // UC2: Operations Menu
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. View Bogies");
            System.out.println("4. Check Bogie Exists");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    // Add bogies
                    System.out.println("Enter bogie type (Sleeper / AC Chair / First Class): ");
                    String bogie = sc.nextLine();

                    passengerBogies.add(bogie);
                    System.out.println("Bogie added successfully.");
                    break;

                case 2:
                    // Remove bogie
                    System.out.println("Enter bogie type to remove: ");
                    String removeBogie = sc.nextLine();

                    if (passengerBogies.remove(removeBogie)) {
                        System.out.println("Bogie removed successfully.");
                    } else {
                        System.out.println("Bogie not found.");
                    }
                    break;

                case 3:
                    // Display bogies
                    System.out.println("Passenger Bogies: " + passengerBogies);
                    break;

                case 4:
                    // Check existence
                    System.out.println("Enter bogie type to check: ");
                    String checkBogie = sc.nextLine();

                    if (passengerBogies.contains(checkBogie)) {
                        System.out.println(checkBogie + " exists in the train.");
                    } else {
                        System.out.println(checkBogie + " does NOT exist.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}