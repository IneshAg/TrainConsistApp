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

        // UC5
        LinkedHashSet<String> trainFormation = new LinkedHashSet<>();

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
            System.out.println("8. Manage Train Formation (UC5)");
            System.out.println("9. Exit");

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
                    manageTrainOrder(trainOrder);
                    break;

                case 8:
                    manageTrainFormation(trainFormation);
                    break;

                case 9:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // UC4: LinkedList operations
    public static void manageTrainOrder(LinkedList<String> trainOrder) {

        trainOrder.clear();

        trainOrder.add("Engine");
        trainOrder.add("Sleeper");
        trainOrder.add("AC");
        trainOrder.add("Cargo");
        trainOrder.add("Guard");

        System.out.println("\nInitial Train Order: " + trainOrder);

        trainOrder.add(2, "Pantry Car");
        System.out.println("After adding Pantry Car: " + trainOrder);

        trainOrder.removeFirst();
        trainOrder.removeLast();

        System.out.println("Final Train Order: " + trainOrder);
    }

    // UC5: LinkedHashSet operations
    public static void manageTrainFormation(LinkedHashSet<String> trainFormation) {

        trainFormation.clear();

        // Add bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        // Attempt duplicate
        trainFormation.add("Sleeper");

        System.out.println("\nTrain Formation (Ordered & Unique):");
        System.out.println(trainFormation);
    }
}