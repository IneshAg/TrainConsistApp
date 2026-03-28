import java.util.*;
import java.util.stream.Collectors;

// Bogie Class (UC7–UC10)
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
            System.out.println("13. Total Seating Capacity (UC10)");
            System.out.println("14. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter bogie type: ");
                    String bogie = sc.nextLine();
                    passengerBogies.add(bogie);
                    System.out.println("Bogie added.");
                    break;

                case 2:
                    System.out.println("Enter bogie to remove: ");
                    String remove = sc.nextLine();
                    if (passengerBogies.remove(remove))
                        System.out.println("Removed.");
                    else
                        System.out.println("Not found.");
                    break;

                case 3:
                    System.out.println("Passenger Bogies: " + passengerBogies);
                    break;

                case 4:
                    System.out.println("Enter bogie to check: ");
                    String check = sc.nextLine();
                    System.out.println(passengerBogies.contains(check) ? "Exists" : "Not found");
                    break;

                case 5:
                    System.out.println("Enter Bogie ID: ");
                    String id = sc.nextLine();
                    if (bogieIds.add(id))
                        System.out.println("Added.");
                    else
                        System.out.println("Duplicate ID!");
                    break;

                case 6:
                    System.out.println("Unique IDs: " + bogieIds);
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
                    sortBogies();
                    break;

                case 11:
                    filterBogies();
                    break;

                case 12:
                    groupBogies();
                    break;

                case 13:
                    totalCapacity();
                    break;

                case 14:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // UC4
    static void manageTrainOrder(LinkedList<String> list) {
        list.clear();
        list.add("Engine");
        list.add("Sleeper");
        list.add("AC");
        list.add("Cargo");
        list.add("Guard");

        System.out.println("Initial: " + list);

        list.add(2, "Pantry");
        list.removeFirst();
        list.removeLast();

        System.out.println("Final: " + list);
    }

    // UC5
    static void manageTrainFormation(LinkedHashSet<String> set) {
        set.clear();
        set.add("Engine");
        set.add("Sleeper");
        set.add("Cargo");
        set.add("Guard");
        set.add("Sleeper"); // duplicate

        System.out.println("Formation: " + set);
    }

    // UC6
    static void manageBogieCapacity(HashMap<String, Integer> map) {
        map.clear();
        map.put("Sleeper", 72);
        map.put("AC Chair", 60);
        map.put("First Class", 40);

        map.forEach((k, v) -> System.out.println(k + " → " + v));
    }

    // UC7
    static void sortBogies() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 40)
        );

        list.sort((a, b) -> b.capacity - a.capacity);

        System.out.println("Sorted:");
        list.forEach(System.out::println);
    }

    // UC8
    static void filterBogies() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 40)
        );

        List<Bogie> filtered = list.stream()
                .filter(b -> b.capacity > 60)
                .toList();

        System.out.println("Filtered:");
        filtered.forEach(System.out::println);
    }

    // UC9
    static void groupBogies() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 40)
        );

        Map<String, List<Bogie>> grouped =
                list.stream().collect(Collectors.groupingBy(b -> b.name));

        grouped.forEach((k, v) -> System.out.println(k + " → " + v));
    }

    // UC10 🔥
    static void totalCapacity() {
        List<Bogie> list = Arrays.asList(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 60),
                new Bogie("First Class", 40)
        );

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seating Capacity: " + total);
    }
}