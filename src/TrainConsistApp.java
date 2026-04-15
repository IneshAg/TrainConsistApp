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

    // UC16
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

    // UC18
    public static int linearSearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }

    // ✅ UC19 Binary Search
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
            System.out.println("22. Linear Search (UC18)");
            System.out.println("23. Binary Search (UC19)");

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
                    if (bogieIds.isEmpty()) {
                        System.out.println("No bogie IDs available!");
                        break;
                    }

                    String[] idArray1 = bogieIds.toArray(new String[0]);

                    System.out.print("Enter Bogie ID to search: ");
                    String key1 = sc.nextLine();

                    int index1 = linearSearch(idArray1, key1);

                    if (index1 != -1)
                        System.out.println("Found at position: " + index1);
                    else
                        System.out.println("Not Found");
                    break;

                // ✅ UC19
                case 23:
                    if (bogieIds.isEmpty()) {
                        System.out.println("No bogie IDs available!");
                        break;
                    }

                    String[] idArray2 = bogieIds.toArray(new String[0]);

                    // Step 1: Sort
                    Arrays.sort(idArray2);

                    System.out.println("Sorted IDs: " + Arrays.toString(idArray2));

                    // Step 2: Search
                    System.out.print("Enter Bogie ID to search: ");
                    String key2 = sc.nextLine();

                    int index2 = binarySearch(idArray2, key2);

                    if (index2 != -1)
                        System.out.println("✅ Found at index: " + index2);
                    else
                        System.out.println("❌ Not Found");
                    break;

                case 17:
                    System.exit(0);
            }
        }
    }
}