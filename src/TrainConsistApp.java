import java.util.*;

// ✅ UC14
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// ✅ UC15
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

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

// ✅ UC15
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

    // ✅ UC16 Bubble Sort
    public static void bubbleSortBogies(List<Bogie> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).capacity > list.get(j + 1).capacity) {
                    Bogie temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // ✅ UC18 Linear Search
    public static int linearSearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> passengerBogies = new ArrayList<>();
        Set<String> bogieIds = new HashSet<>();

        System.out.println("=== Train Consist Management App ===");

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Passenger Bogie");
            System.out.println("2. Remove Passenger Bogie");
            System.out.println("3. View Passenger Bogies");
            System.out.println("4. Check Bogie Exists");
            System.out.println("5. Add Bogie ID");
            System.out.println("6. View Bogie IDs");
            System.out.println("17. Exit");
            System.out.println("18. Add Validated Bogie (UC14)");
            System.out.println("19. Assign Cargo (UC15)");
            System.out.println("20. Bubble Sort (UC16)");
            System.out.println("21. Arrays.sort (UC17)");
            System.out.println("22. Search Bogie ID (Linear Search - UC18)");

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

                // UC14
                case 18:
                    try {
                        System.out.print("Enter Bogie Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Capacity: ");
                        int cap = sc.nextInt();
                        sc.nextLine();

                        Bogie b = new Bogie(name, cap);
                        System.out.println("Created: " + b);

                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // UC15
                case 19:
                    System.out.print("Enter Bogie Type: ");
                    String type = sc.nextLine();

                    GoodsBogie gb = new GoodsBogie(type);

                    System.out.print("Enter Cargo: ");
                    String cargo = sc.nextLine();

                    gb.assignCargo(cargo);
                    System.out.println(gb);
                    break;

                // UC16
                case 20:
                    try {
                        List<Bogie> list = new ArrayList<>();
                        list.add(new Bogie("Sleeper", 72));
                        list.add(new Bogie("AC Chair", 60));
                        list.add(new Bogie("First Class", 40));

                        bubbleSortBogies(list);

                        list.forEach(System.out::println);

                    } catch (InvalidCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // UC17
                case 21:
                    String[] bogieNames = {"Sleeper", "AC Chair", "First Class"};
                    Arrays.sort(bogieNames);
                    System.out.println(Arrays.toString(bogieNames));
                    break;

                // ✅ UC18
                case 22:
                    if (bogieIds.isEmpty()) {
                        System.out.println("No bogie IDs available!");
                        break;
                    }

                    String[] idArray = bogieIds.toArray(new String[0]);

                    System.out.print("Enter Bogie ID to search: ");
                    String key = sc.nextLine();

                    int index = linearSearch(idArray, key);

                    if (index != -1) {
                        System.out.println("✅ Bogie ID FOUND at position: " + index);
                    } else {
                        System.out.println("❌ Bogie ID NOT FOUND");
                    }
                    break;

                case 17:
                    System.exit(0);
            }
        }
    }
}