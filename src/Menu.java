import RailroadCars.PassengerRailroadCar;
import RailroadCars.RailroadCar;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    private Map<Integer, Map<Integer, Runnable>> menu;
    private Scanner scanner;
    Map<String, Trainset> menuTrain = new HashMap<>();
    Map<String, RailroadCar> menuCar = new HashMap<>();
    private boolean programIsWorking = true;

    public Menu(Map<String, Trainset> menuTrain, Map<String, RailroadCar> menuCar) {
        this.menu = new HashMap<>();
        this.menuTrain = menuTrain;
        this.menuCar = menuCar;
        // Category A functions
        Map<Integer, Runnable> trains = new HashMap<>();
        trains.put(1, this::addCar);
        trains.put(2, this::deleteCars);
        trains.put(3, this::trainInformation);
        menu.put(1, trains);

        //Category B functions
        Map<Integer, Runnable> cars = new HashMap<>();
        cars.put(1, this::createCar);
        cars.put(2, this::functionA2);
        cars.put(3, this::functionA2);
        menu.put(2, cars);

        // Category C functions
        Map<Integer, Runnable> routes = new HashMap<>();
        routes.put(1, this::functionA2);
        routes.put(2, this::functionA2);
        routes.put(3, this::functionA2);
        menu.put(3, routes);

        // Category D functions
        Map<Integer, Runnable> info = new HashMap<>();
        info.put(1, this::functionA2);
        info.put(2, this::functionA2);
        info.put(3, this::functionA2);
        menu.put(4, info);

        // Category E functions
        Map<Integer, Runnable> categoryE = new HashMap<>();
        categoryE.put(1, this::functionA2);
        categoryE.put(2, this::functionA2);
        categoryE.put(3, this::functionA2);
        menu.put(5, categoryE);

        this.scanner = new Scanner(System.in);
    }

    public void display() {
        int choice = -1;

        while (programIsWorking) {

            // Display the menu
            System.out.println("Choose the option:");
            System.out.println(
                    "1. Trains\n" +
                            "2. Cars\n" +
                            "3. Route\n" +
                            "4. Abbreviation\n" +
                            "5. Category E\n" +
                            "0. Exit");
            System.out.println();

            // Get the user's choice
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            if (choice == 0) {
                exitProgram();
            }
            // Call the selected function using the HashMap
            if (choice >= 1 && choice <= 3) {
                Map<Integer, Runnable> category = menu.get(choice);
                System.out.println("Functions:");
                if (choice == 1) {
                    System.out.println("1. Add a car");
                    System.out.println("2. Delete cars");
                    System.out.println("3. Train information");
                } else if (choice == 2) {
                    System.out.println("1. Create new Car");
                    System.out.println("2. Edit car");
                    System.out.println("3. Car information");
                } else if (choice == 3) {
                    System.out.println("1. smth about route");
                    System.out.println("2. smth about route");
                    System.out.println("3. smth about route");
                }
                System.out.println();
                System.out.print("Enter your choice of function: ");
                int functionChoice = scanner.nextInt();
                if (functionChoice >= 1 && functionChoice <= 3) {
                    Runnable function = category.get(functionChoice);
                    function.run();
                } else {
                    System.out.println("Invalid choice.");
                }
            } else if (choice == 4) {
                System.out.println(abbreviationInfo());
            } else if (choice != 0) {
                System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }

    private void createCar() {
        System.out.println(abbreviationInfo());
        System.out.println("Enter the type of Car");
        String id = scanner.next();
        switch (id) {
            case "prc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the class of car (1 or 2):");
                int carClass = scanner.nextInt();
                System.out.println("Enter number of seats( > 0):");
                int numSeats = scanner.nextInt();
                PassengerRailroadCar prc = new PassengerRailroadCar(shipper, netWeight, carClass, numSeats);
                menuCar.put("prc3", prc);
                System.out.println(prc);
                break;
            }
            case "porc": {
                //fa
                break;
            }
            case "bmrc": {
                //fafadsf
                break;
            }
            case "rrc": {
                //fafad
                break;
            }
            case "bfrc": {
                //fafadsffdas
                break;
            }
            default:
                System.out.println("Incorrect input");
                break;
        }
    }

    private void addCar() {
        System.out.print("Enter the train ID: ");
        String trainId = scanner.next();
        Trainset trainset = null;
        for (String id : this.menuTrain.keySet()) {
            if (trainId.equals(id))
                trainset = menuTrain.get(id);
        }
        //System.out.println(trainset);
        System.out.print("Enter the Car ID: ");
        String carId = scanner.next();
        RailroadCar railroadCar = null;
        for (String id : this.menuCar.keySet()) {
            if (carId.equals(id)) {
                railroadCar = menuCar.get(id);
            }
        }
        if (railroadCar != null) {
            //System.out.println(railroadCar);
            trainset.addCar(railroadCar);
            System.out.println("The railroad car " + railroadCar.getId() + " has been attached to trainset " + trainset.getId());
        } else
            System.out.println("Railroad Car " + railroadCar.getId() + " haven't been found");
    }

    private void exitProgram() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread item : threadSet)
            item.interrupt();
        this.programIsWorking = false;
        System.out.println("Exiting the program...");
    }

    private void functionA2() {
        System.out.println("You've picked func 2");
    }

    private void deleteCars() {
        System.out.println("You've picked func 2");
    }

    private void trainInformation() {
        System.out.print("Enter the train ID: my here ");
        String trainId = scanner.next();
        Trainset trainset = null;
        for (String id : this.menuTrain.keySet()) {
            if (trainId.equals(id))
                trainset = menuTrain.get(id);
        }
        System.out.println(trainset);
    }

    private static String abbreviationInfo() {
        return "Here is the explanation of the trains' abbreviation\n" +
                "prc - Passenger Railroad Car\n" +
                "porc - Post Office Railroad Car\n" +
                "bmrc - Baggage and Mail Railroad Car\n" +
                "rrc - Restaurant Railroad Car\n" +
                "bfrc - Basic Freight Railroad Car\n" +
                "hrfc - Heavy Freight Railroad Car\n" +
                "rfrc - Refrigerated Railroad Car\n" +
                "lrc - Liquid Railroad Car\n" +
                "grc - Gaseous Railroad Car\n" +
                "erc - Explosive Railroad Car\n" +
                "trc - Toxic Railroad Car\n" +
                "ltrc - Liquid Toxic Railroad Car\n";
    }
}