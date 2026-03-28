import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

// Passenger Bogie (UC7–UC10)
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

// Goods Bogie (UC12)
class GoodsBogie {
    String type;   // Cylindrical / Open / Box
    String cargo;  // Petroleum / Coal / Grain

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
            System.out.println("13. Total Capacity (UC10)");
            System.out.println("14. Validate Regex (UC11)");
            System.out.println("15. Safety Check (UC12)");
            System.out.println("16. Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    passengerBogies.add(sc.nextLine());
                    break;

                case 2:
                    passengerBogies.remove(sc.nextLine());
                    break;

                case 3:
                    System.out.println(passengerBogies);
                    break;

                case 4:
                    System.out.println(passengerBogies.contains(sc.nextLine()));
                    break;

                case 5:
                    if (!bogieIds.add(sc.nextLine()))
                        System.out.println("Duplicate!");
                    break;

                case 6:
                    System.out.println(bogieIds);
                    break;

                case 7:
                    trainOrder.clear();
                    trainOrder.addAll(Arrays.asList("Engine","Sleeper","AC","Cargo","Guard"));
                    trainOrder.add(2,"Pantry");
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
                    bogieCapacity.put("Sleeper",72);
                    bogieCapacity.put("AC Chair",60);
                    bogieCapacity.put("First Class",40);
                    bogieCapacity.forEach((k,v)->System.out.println(k+"→"+v));
                    break;

                case 10:
                    List<Bogie> sortList = Arrays.asList(
                            new Bogie("Sleeper",72),
                            new Bogie("AC Chair",60),
                            new Bogie("First Class",40)
                    );
                    sortList.sort((a,b)->b.capacity-a.capacity);
                    sortList.forEach(System.out::println);
                    break;

                case 11:
                    List<Bogie> filterList = Arrays.asList(
                            new Bogie("Sleeper",72),
                            new Bogie("AC Chair",60),
                            new Bogie("First Class",40)
                    );
                    filterList.stream()
                            .filter(b->b.capacity>60)
                            .forEach(System.out::println);
                    break;

                case 12:
                    List<Bogie> groupList = Arrays.asList(
                            new Bogie("Sleeper",72),
                            new Bogie("Sleeper",72),
                            new Bogie("AC Chair",60)
                    );
                    Map<String,List<Bogie>> grouped =
                            groupList.stream().collect(Collectors.groupingBy(b->b.name));
                    grouped.forEach((k,v)->System.out.println(k+"→"+v));
                    break;

                case 13:
                    List<Bogie> capList = Arrays.asList(
                            new Bogie("Sleeper",72),
                            new Bogie("AC Chair",60),
                            new Bogie("First Class",40)
                    );
                    int total = capList.stream()
                            .map(b->b.capacity)
                            .reduce(0,Integer::sum);
                    System.out.println("Total="+total);
                    break;

                case 14:
                    System.out.print("Train ID: ");
                    String t = sc.nextLine();
                    System.out.print("Cargo Code: ");
                    String c = sc.nextLine();

                    boolean validT = Pattern.matches("TRN-\\d{4}", t);
                    boolean validC = Pattern.matches("PET-[A-Z]{2}", c);

                    System.out.println(validT ? "Valid Train ID" : "Invalid Train ID");
                    System.out.println(validC ? "Valid Cargo" : "Invalid Cargo");
                    break;

                case 15:
                    List<GoodsBogie> goods = Arrays.asList(
                            new GoodsBogie("Cylindrical","Petroleum"),
                            new GoodsBogie("Open","Coal"),
                            new GoodsBogie("Box","Grain")
                    );

                    boolean safe = goods.stream()
                            .allMatch(g ->
                                    !g.type.equals("Cylindrical") ||
                                            g.cargo.equals("Petroleum")
                            );

                    System.out.println("Goods Bogies:");
                    goods.forEach(System.out::println);

                    System.out.println(safe ? "Train is SAFE" : "Train is UNSAFE");
                    break;

                case 16:
                    return;

                default:
                    System.out.println("Invalid");
            }
        }
    }
}