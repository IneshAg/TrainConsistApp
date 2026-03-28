import java.util.*;
import java.util.stream.Collectors;

// Bogie Class (UC7, UC8, UC9)
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

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

        // UC6
        HashMap<String, Integer> bogieCapacity = new HashMap<>();

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
            System.out.println("9. Manage Bogie Capacity (UC6)");
            System.out.println("10. Sort Bogies by Capacity (UC7)");
            System.out.println("11. Filter Bogies (UC8)");
            System.out.println("12. Group Bogies by Type (UC9)");
            System.out.println("13. Exit");

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
                    manageBogieCapacity(bogieCapacity);
                    break;

                case 10:
                    sortBogiesByCapacity();
                    break;

                case 11:
                    filterBogiesUsingStreams();
                    break;

                case 12:
                    groupBogiesByType();
                    break;

                case 13:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // UC4
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

    // UC5
    public static void manageTrainFormation(LinkedHashSet<String> trainFormation) {

        trainFormation.clear();

        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        trainFormation.add("Sleeper"); // duplicate ignored

        System.out.println("\nTrain Formation (Ordered & Unique):");
        System.out.println(trainFormation);
    }

    // UC6
    public static void manageBogieCapacity(HashMap<String, Integer> bogieCapacity) {

        bogieCapacity.clear();

        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 40);

        System.out.println("\nBogie Capacity Mapping:");

        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " → Capacity: " + entry.getValue());
        }
    }

    // UC7
    public static void sortBogiesByCapacity() {

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));

        bogies.sort((b1, b2) -> b2.capacity - b1.capacity);

        System.out.println("\nBogies Sorted by Capacity (High → Low):");

        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }

    // UC8
    public static void filterBogiesUsingStreams() {

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));

        System.out.println("\nOriginal Bogies:");
        bogies.forEach(System.out::println);

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .toList();

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        filtered.forEach(System.out::println);
    }

    // UC9
    public static void groupBogiesByType() {

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 40));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));

        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies by Type:");

        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println("\n" + entry.getKey() + " → " + entry.getValue());
        }
    }
}