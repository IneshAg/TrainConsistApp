import java.util.*;
import java.util.regex.*;
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

        // UC1–UC6 Structures
        List<String> passengerBogies = new ArrayList<>();
        Set<String> bogieIds = new HashSet<>();
        LinkedList<String> trainOrder = new LinkedList<>();
        LinkedHashSet<String> trainFormation = new LinkedHashSet<>();
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
            System.out.println("10. Sort Bogies (UC7)");
            System.out.println("11. Filter Bogies (UC8)");
            System.out.println("12. Group Bogies (UC9)");
            System.out.println("13. Total Capacity (UC10)");
            System.out.println("14. Validate Train & Cargo (UC11)");
            System.out.println("15. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter bogie: ");
                    passengerBogies.add(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter bogie to remove: ");
                    if (passengerBogies.remove(sc.nextLine()))
                        System.out.println("Removed");
                    else
                        System.out.println("Not found");
                    break;

                case 3:
                    System.out.println(passengerBogies);
                    break;

                case 4:
                    System.out.print("Enter bogie to check: ");
                    System.out.println(passengerBogies.contains(sc.nextLine()));
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    if (!bogieIds.add(sc.nextLine()))
                        System.out.println("Duplicate ID!");
                    else
                        System.out.println("ID Added");
                    break;

                case 6:
                    System.out.println(bogieIds);
                    break;

                case 7:
                    manageOrder(trainOrder);
                    break;

                case 8:
                    manageFormation(trainFormation);
                    break;

                case 9:
                    manageCapacity(bogieCapacity);
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
                    validateRegex(sc);
                    break;

                case 15:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // UC4
    static void manageOrder(LinkedList<String> list) {
        list.clear();
        list.addAll(Arrays.asList("Engine", "Sleeper", "AC", "Cargo", "Guard"));
        System.out.println("Initial Order: " + list);

        list.add(2, "Pantry");
        list.removeFirst();
        list.removeLast();

        System.out.println("Final Order: " + list);
    }

    // UC5
    static void manageFormation(LinkedHashSet<String> set) {
        set.clear();
        set.add("Engine");
        set.add("Sleeper");
        set.add("Cargo");
        set.add("Guard");
        set.add("Sleeper"); // duplicate ignored

        System.out.println("Formation (Ordered + Unique): " + set);
    }

    // UC6
    static void manageCapacity(HashMap<String, Integer> map) {
        map.clear();
        map.put("Sleeper", 72);
        map.put("AC Chair", 60);
        map.put("First Class", 40);

        System.out.println("Bogie Capacities:");
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

        System.out.println("Sorted Bogies (High → Low):");
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

        System.out.println("Filtered Bogies (>60):");
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

        System.out.println("Grouped Bogies:");
        grouped.forEach((k, v) -> System.out.println(k + " → " + v));
    }

    // UC10
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

    // UC11
    static void validateRegex(Scanner sc) {

        System.out.print("Enter Train ID (TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code (PET-AB): ");
        String cargo = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargo);

        System.out.println("\nValidation Results:");

        if (trainMatcher.matches())
            System.out.println("Train ID is VALID");
        else
            System.out.println("Train ID is INVALID");

        if (cargoMatcher.matches())
            System.out.println("Cargo Code is VALID");
        else
            System.out.println("Cargo Code is INVALID");
    }
}