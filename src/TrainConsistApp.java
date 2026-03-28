import java.util.*;

public class TrainConsistApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // UC1: Initialization
        System.out.println("=== Train Consist Management App ===");

        List<String> passengerBogies = new ArrayList<>();
        Set<String> bogieIds = new HashSet<>();

        System.out.println("Train initialized successfully.");
        System.out.println("Initial bogie count: " + passengerBogies.size());

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. View Bogies");
            System.out.println("4. Check Bogie Exists");
            System.out.println("5. Add Bogie ID (Unique)");
            System.out.println("6. View All Bogie IDs");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    // Add passenger bogie
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
                    // UC3: Add Bogie ID (Unique)
                    System.out.println("Enter Bogie ID (e.g., BG101): ");
                    String id = sc.nextLine();

                    if (bogieIds.add(id)) {
                        System.out.println("Bogie ID added successfully.");
                    } else {
                        System.out.println("Duplicate ID! Not added.");
                    }
                    break;

                case 6:
                    // Display unique IDs
                    System.out.println("Unique Bogie IDs: " + bogieIds);
                    break;

                case 7:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}