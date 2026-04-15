import java.util.*;
import java.util.stream.Collectors;

// ✅ UC14: Custom Checked Exception
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// ✅ UC15: Runtime Exception
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// Passenger Bogie
class Bogie {
    String name;
    int capacity;

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

// Goods Bogie (UC15)
class GoodsBogie {
    String type;
    String cargo;

    public GoodsBogie(String type) {
        this.type = type;
        this.cargo = "Empty";
    }

    public void assignCargo(String cargoType) {
        try {
            if (type.equalsIgnoreCase("Rectangular") &&
                    cargoType.equalsIgnoreCase("Petroleum")) {

                throw new CargoSafetyException(
                        "❌ Unsafe: Petroleum cannot be assigned to Rectangular bogie");
            }

            this.cargo = cargoType;
            System.out.println("✅ Cargo assigned successfully!");

        } catch (CargoSafetyException e) {
            System.out.println("ERROR: " + e.getMessage());

        } finally {
            System.out.println("🔄 Cargo assignment attempt completed.\n");
        }
    }

    public String toString() {
        return type + " bogie carrying: " + cargo;
    }
}

public class TrainConsistApp {

    // ✅ UC16: Bubble Sort Method
    public static void bubbleSortBogies(List<Bogie> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (list.get(j).capacity > list.get(j + 1).capacity) {
                    // Swap
                    Bogie temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

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
            System.out.println("7. Train Order");
            System.out.println("8. Train Formation");
            System.out.println("9. Capacity Map");
            System.out.println("10. Sort (Comparator)");
            System.out.println("11. Filter");
            System.out.println("12. Group");
            System.out.println("13. Performance Comparison");
            System.out.println("17. Exit");
            System.out.println("18. Add Validated Bogie (UC14)");
            System.out.println("19. Assign Cargo (UC15)");
            System.out.println("20. Sort Passenger Bogies (Bubble Sort - UC16)");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    System.out.print("Enter passenger bogie: ");
                    passengerBogies.add(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter bogie to remove: ");
                    passengerBogies.remove(sc.nextLine());
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
                    bogieIds.add(sc.nextLine());
                    break;

                case 6:
                    System.out.println(bogieIds);
                    break;

                case 7:
                    trainOrder.clear();
                    trainOrder.addAll(Arrays.asList("Engine", "Sleeper", "AC", "Cargo", "Guard"));
                    System.out.println(trainOrder);
                    break;

                case 8:
                    trainFormation.clear();
                    trainFormation.add("Engine");
                    trainFormation.add("Sleeper");
                    trainFormation.add("Cargo");
                    System.out.println(trainFormation);
                    break;

                case 9:
                    bogieCapacity.put("Sleeper", 72);
                    bogieCapacity.put("AC Chair", 60);
                    bogieCapacity.put("First Class", 40);
                    bogieCapacity.forEach((k, v) -> System.out.println(k + " → " + v));
                    break;

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
                        System.out.println(e.getMessage());
                    }
                    break;

                case 19:
                    System.out.print("Enter Bogie Type: ");
                    String type = sc.nextLine();

                    GoodsBogie gb = new GoodsBogie(type);

                    System.out.print("Enter Cargo: ");
                    String cargo = sc.nextLine();

                    gb.assignCargo(cargo);
                    System.out.println(gb);
                    break;

                // ✅ UC16 IMPLEMENTATION
                case 20:
                    try {
                        List<Bogie> list = new ArrayList<>();

                        list.add(new Bogie("Sleeper", 72));
                        list.add(new Bogie("AC Chair", 60));
                        list.add(new Bogie("First Class", 40));

                        System.out.println("Before Sorting:");
                        list.forEach(System.out::println);

                        bubbleSortBogies(list);

                        System.out.println("\nAfter Bubble Sort (Ascending Capacity):");
                        list.forEach(System.out::println);

                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 17:
                    System.exit(0);
            }
        }
    }
}