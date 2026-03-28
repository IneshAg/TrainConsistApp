import java.util.*;

public class TrainConsistApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // UC1 + UC2
        List<String> passengerBogies = new ArrayList<>();

        // UC3
        Set<String> bogieIds = new HashSet<>();

        // UC4
        LinkedList<String> trainOrder = new LinkedList<>();

        System.out.println("=== Train Consist Management App ===");
        System.out.println("Train initialized successfully.");
        System.out.println("Initial bogie count: " + passengerBogies.size());

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. View Passenger Bogies");
            System.out.println("4. Check Bogie Exists");
            System.out.println("5. Add Bogie ID (Unique)");
            System.out.println("6. View Unique Bogie IDs");
            System.out.println("7. Manage Train Order (UC4)");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter bogie type (Sleeper / AC Chair / First Class): ");
                    String bogie = sc.nextLine();
                    passengerBogies.add(bogie);
                    System.out.println("Bogie added successfully.");
                    break;

                case 2:
                    System.out.println("Enter bogie type to remove: ");
                    String removeBogie = sc.nextLine();

                    if (passengerBogies.remove(removeBogie)) {
                        System.out.println("Bogie removed successfully.");
                    } else {
                        System.out.println("Bogie not found.");
                    }
                    break;

                case 3:
                    System.out.println("Passenger Bogies: " + passengerBogies);
                    break;

                case 4:
                    System.out.println("Enter bogie type to check: ");
                    String checkBogie = sc.nextLine();

                    if (passengerBogies.contains(checkBogie)) {
                        System.out.println(checkBogie + " exists.");
                    } else {
                        System.out.println(checkBogie + " does NOT exist.");
                    }
                    break;

                case 5:
                    System.out.println("Enter Bogie ID (e.g., BG101): ");
                    String id = sc.nextLine();

                    if (bogieIds.add(id)) {
                        System.out.println("Bogie ID added successfully.");
                    } else {
                        System.out.println("Duplicate ID! Not added.");
                    }
                    break;

                case 6:
                    System.out.println("Unique Bogie IDs: " + bogieIds);
                    break;

                case 7:
                    // UC4 LinkedList operations
                    manageTrainOrder(trainOrder);
                    break;

                case 8:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // UC4 Logic
    public static void manageTrainOrder(LinkedList<String> trainOrder) {

        // Initial formation
        trainOrder.clear();

        trainOrder.add("Engine");
        trainOrder.add("Sleeper");
        trainOrder.add("AC");
        trainOrder.add("Cargo");
        trainOrder.add("Guard");

        System.out.println("\nInitial Train Order: " + trainOrder);

        // Insert Pantry at position 2
        trainOrder.add(2, "Pantry Car");
        System.out.println("After adding Pantry Car at position 2: " + trainOrder);

        // Remove first and last
        trainOrder.removeFirst();
        trainOrder.removeLast();

        System.out.println("After removing first and last bogie:");
        System.out.println("Final Train Order: " + trainOrder);
    }
}