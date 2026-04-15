import java.util.*;

// UC14
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// UC15
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// ✅ UC20: Custom Exception
class EmptyTrainException extends Exception {
    public EmptyTrainException(String message) {
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

// UC15
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

    // UC18
    public static int linearSearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }

    // UC19
    public static int binarySearch(String[] arr, String key) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int cmp = arr[mid].compareToIgnoreCase(key);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Set<String> bogieIds = new HashSet<>();

        System.out.println("=== Train Consist Management App ===");

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("5. Add Bogie ID");
            System.out.println("6. View Bogie IDs");
            System.out.println("22. Linear Search (UC18)");
            System.out.println("23. Binary Search (UC19)");
            System.out.println("24. Safe Search (UC20)");
            System.out.println("17. Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 5:
                    System.out.print("Enter bogie ID: ");
                    bogieIds.add(sc.nextLine());
                    break;

                case 6:
                    System.out.println(bogieIds);
                    break;

                // UC18
                case 22:
                    String[] arr1 = bogieIds.toArray(new String[0]);
                    System.out.print("Enter ID: ");
                    String key1 = sc.nextLine();

                    int res1 = linearSearch(arr1, key1);
                    System.out.println(res1 != -1 ? "Found" : "Not Found");
                    break;

                // UC19
                case 23:
                    String[] arr2 = bogieIds.toArray(new String[0]);
                    Arrays.sort(arr2);

                    System.out.print("Enter ID: ");
                    String key2 = sc.nextLine();

                    int res2 = binarySearch(arr2, key2);
                    System.out.println(res2 != -1 ? "Found" : "Not Found");
                    break;

                // ✅ UC20 IMPLEMENTATION
                case 24:
                    try {
                        // Fail-fast validation
                        if (bogieIds.isEmpty()) {
                            throw new EmptyTrainException(
                                    "🚫 Cannot perform search: No bogies in train!");
                        }

                        String[] arr = bogieIds.toArray(new String[0]);

                        System.out.print("Enter ID to search: ");
                        String key = sc.nextLine();

                        int result = linearSearch(arr, key);

                        if (result != -1)
                            System.out.println("✅ Found at index: " + result);
                        else
                            System.out.println("❌ Not Found");

                    } catch (EmptyTrainException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;

                case 17:
                    System.exit(0);
            }
        }
    }
}