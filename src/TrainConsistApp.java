import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

// ✅ UC14: Custom Exception
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

class Bogie {
    String name;
    int capacity;

    // ✅ UC14: Validate capacity
    public Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

class GoodsBogie {
    String type;
    String cargo;

    public GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public String toString() {
        return type + " carrying " + cargo;
    }
}

public class TrainConsistApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> passengerBogies = new ArrayList<>();
        Set<String> bogieIds = new HashSet<>();
        LinkedList<String> trainOrder = new LinkedList<>();
        LinkedHashSet<String> trainFormation = new LinkedHashSet<>();
        HashMap<String, Integer> bogieCapacity = new HashMap<>();

        System.out.println("=== Train Consist Management App ===");

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. View Passenger Bogies");
            System.out.println("4. Check Bogie Exists");
            System.out.println("5. Add Bogie ID");
            System.out.println("6. View Bogie IDs");
            System.out.println("7. Train Order (UC4)");
            System.out.println("8. Train Formation (UC5)");
            System.out.println("9. Capacity Map (UC6)");
            System.out.println("10. Sort (UC7)");
            System.out.println("11. Filter (UC8)");
            System.out.println("12. Group (UC9)");
            System.out.println("13. Performance Comparison (UC13)");
            System.out.println("14. Validate Regex (UC11)");
            System.out.println("15. Safety Check (UC12)");
            System.out.println("16. (Reserved)");
            System.out.println("17. Exit");
            System.out.println("18. Add Validated Bogie (UC14)");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    System.out.print("Enter passenger bogie: ");
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
                    System.out.print("Enter bogie ID: ");
                    if (!bogieIds.add(sc.nextLine()))
                        System.out.println("Duplicate ID!");
                    else
                        System.out.println("ID added.");
                    break;

                case 6:
                    System.out.println(bogieIds);
                    break;

                case 7:
                    trainOrder.clear();
                    trainOrder.addAll(Arrays.asList("Engine", "Sleeper", "AC", "Cargo", "Guard"));
                    trainOrder.add(2, "Pantry");
                    trainOrder.removeFirst();
                    trainOrder.removeLast();
                    System.out.println(trainOrder);
                    break;

                case 8:
                    trainFormation.clear();
                    trainFormation.add("Engine");
                    trainFormation.add("Sleeper");
                    trainFormation.add("Cargo");
                    trainFormation.add("Guard");
                    trainFormation.add("Sleeper");
                    System.out.println(trainFormation);
                    break;

                case 9:
                    bogieCapacity.put("Sleeper", 72);
                    bogieCapacity.put("AC Chair", 60);
                    bogieCapacity.put("First Class", 40);
                    bogieCapacity.forEach((k, v) -> System.out.println(k + " → " + v));
                    break;

                case 10:
                    try {
                        List<Bogie> sortList = Arrays.asList(
                                new Bogie("Sleeper", 72),
                                new Bogie("AC Chair", 60),
                                new Bogie("First Class", 40)
                        );
                        sortList.sort((a, b) -> b.capacity - a.capacity);
                        sortList.forEach(System.out::println);
                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 11:
                    try {
                        List<Bogie> filterList = Arrays.asList(
                                new Bogie("Sleeper", 72),
                                new Bogie("AC Chair", 60),
                                new Bogie("First Class", 40)
                        );
                        filterList.stream()
                                .filter(b -> b.capacity > 60)
                                .forEach(System.out::println);
                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 12:
                    try {
                        List<Bogie> groupList = Arrays.asList(
                                new Bogie("Sleeper", 72),
                                new Bogie("Sleeper", 72),
                                new Bogie("AC Chair", 60)
                        );

                        Map<String, List<Bogie>> grouped = groupList.stream()
                                .collect(Collectors.groupingBy(b -> b.name));

                        grouped.forEach((k, v) -> System.out.println(k + " → " + v));
                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 13:
                    try {
                        List<Bogie> bigList = new ArrayList<>();

                        for (int i = 0; i < 100000; i++) {
                            bigList.add(new Bogie("Sleeper", 72));
                            bigList.add(new Bogie("AC Chair", 60));
                            bigList.add(new Bogie("First Class", 40));
                        }

                        long startLoop = System.nanoTime();
                        List<Bogie> loopResult = new ArrayList<>();

                        for (Bogie b : bigList) {
                            if (b.capacity > 60) {
                                loopResult.add(b);
                            }
                        }
                        long endLoop = System.nanoTime();

                        long startStream = System.nanoTime();
                        List<Bogie> streamResult = bigList.stream()
                                .filter(b -> b.capacity > 60)
                                .toList();
                        long endStream = System.nanoTime();

                        System.out.println("Loop Time: " + (endLoop - startLoop));
                        System.out.println("Stream Time: " + (endStream - startStream));

                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // ✅ UC14 IMPLEMENTATION
                case 18:
                    try {
                        System.out.print("Enter Bogie Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Capacity: ");
                        int cap = sc.nextInt();
                        sc.nextLine();

                        Bogie b = new Bogie(name, cap);
                        System.out.println("Bogie Created: " + b);

                    } catch (InvalidCapacityException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 17:
                    System.exit(0);
            }
        }
    }
}