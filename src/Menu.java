import RailroadCars.*;

import java.util.*;

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

        Map<Integer, Runnable> trains = new HashMap<>();
        trains.put(1, this::addCar);
        trains.put(2, this::removeCar);
        trains.put(3, this::trainInformation);
        menu.put(1, trains);

        Map<Integer, Runnable> cars = new HashMap<>();
        cars.put(1, this::createCar);
        cars.put(2, this::removeCar);
        cars.put(3, this::carInformation);
        menu.put(2, cars);

        Map<Integer, Runnable> routes = new HashMap<>();
        routes.put(1, this::createLocomotive);
        routes.put(2, this::foo);
        routes.put(3, this::foo);
        menu.put(3, routes);


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
                            "3. Locomotive\n" +
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
                    System.out.println("2. Remove car");
                    System.out.println("3. Car information");
                } else if (choice == 3) {
                    System.out.println("1. Create a new locomotive");
                    System.out.println("2. smth about station");
                    System.out.println("3. smth about station");
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
    private void createLocomotive(){
        LocomotiveGenerator locomotiveGenerator = new LocomotiveGenerator();
        System.out.println("Enter the home station:");
        String home = scanner.next();
        System.out.println("Enter the source station:");
        String source = scanner.next();
        System.out.println("Enter the destination station:");
        String destination = scanner.next();
        Locomotive locomotive = locomotiveGenerator.generateLocomotive(home,source,destination);
        System.out.println(locomotive);
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
                menuCar.put(prc.getId(), prc);
                System.out.println(prc);
                break;
            }
            case "porc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the max capacity of boxes:");
                int maxCapacityBoxes = scanner.nextInt();
                System.out.println("Enter is this will be able to store fragile parcels (yes/no)");
                String answer = scanner.next().toLowerCase();
                boolean fragile;
                if (answer.equals("yes"))
                    fragile = true;
                else
                    fragile = false;
                PostOfficeRailroadCar porc = new PostOfficeRailroadCar(shipper, netWeight, maxCapacityBoxes, fragile);
                menuCar.put(porc.getId(), porc);
                System.out.println(porc);
                break;
            }
            case "bmrc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter max capacity of the suitcases");
                int maxNumSuitcase = scanner.nextInt();
                System.out.println("Enter is this will be able to store pets (yes/no)");
                String answer = scanner.next().toLowerCase();
                boolean storingpets;
                if (answer.equals("yes"))
                    storingpets = true;
                else
                    storingpets = false;
                BaggageMailRailroadCar bmrc = new BaggageMailRailroadCar(shipper, netWeight, maxNumSuitcase, storingpets);
                menuCar.put(bmrc.getId(), bmrc);
                System.out.println(bmrc);
                break;
            }
            case "rrc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter number of staff members:");
                int staff = scanner.nextInt();
                System.out.println("Enter number of tables");
                int tables = scanner.nextInt();
                RestaurantRailroadCar rrc = new RestaurantRailroadCar(shipper, netWeight, staff, tables);
                menuCar.put(rrc.getId(), rrc);
                System.out.println(rrc);
                break;
            }
            case "rfrc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the cooling method:");
                String coollingMethod = scanner.next();
                System.out.println("Enter is this car will have humidity control (yes/no)");
                String answer = scanner.next().toLowerCase();
                boolean humidityControl;
                if (answer.equals("yes"))
                    humidityControl = true;
                else
                    humidityControl = false;
                RefrigeratedRailroadCar rfrc = new RefrigeratedRailroadCar(shipper, netWeight, coollingMethod, humidityControl);
                menuCar.put(rfrc.getId(), rfrc);
                System.out.println(rfrc);
                break;
            }
            case "lrc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the type of Liquid:");
                String liquidType = scanner.next();
                LiquidRailroadCar lrc = new LiquidRailroadCar(shipper, netWeight, liquidType);
                menuCar.put(lrc.getId(), lrc);
                System.out.println(lrc);
                break;
            }
            case "grc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the gasType:");
                String gasType = scanner.next();
                System.out.println("Enter the type of gas compressor:");
                String typeGasCompressor = scanner.next();
                GaseousRailroadCar grc = new GaseousRailroadCar(shipper, netWeight, gasType, typeGasCompressor);
                menuCar.put(grc.getId(), grc);
                System.out.println(grc);
                break;
            }
            case "erc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the numbers of security guards");
                int security = scanner.nextInt();
                System.out.println("Enter the type of danger (A, B, C):");
                char dangerType = scanner.next().charAt(0);
                System.out.println("Enter the type of cargo:");
                String cargoType = scanner.next();
                ExplosiveRailroadCar erc = new ExplosiveRailroadCar(shipper, netWeight, security, dangerType, cargoType);
                menuCar.put(erc.getId(), erc);
                System.out.println(erc);
                break;
            }
            case "trc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the type of toxic materials:");
                String materials = scanner.next();
                ToxicRailroadCar trc = new ToxicRailroadCar(shipper, netWeight, materials);
                menuCar.put(trc.getId(), trc);
                System.out.println(trc);
                break;
            }
            case "ltrc": {
                System.out.println("Enter the shipper:");
                String shipper = scanner.next();
                System.out.println("Enter the netweight:");
                double netWeight = scanner.nextDouble();
                System.out.println("Enter the type of cargo:");
                String cargoType = scanner.next();
                LiquidToxicRailroadCar ltrc = new LiquidToxicRailroadCar(shipper, netWeight, cargoType);
                menuCar.put(ltrc.getId(), ltrc);
                System.out.println(ltrc);
                break;
            }
            default:
                System.out.println("Incorrect input");
                break;
        }
    }

    private void carInformation() {
        System.out.println("Enter car's id:");
        String carId = scanner.next();
        if (this.menuCar.containsKey(carId))
            System.out.println(this.menuCar.get(carId));
        else
            System.out.println("Car with " + carId + " id hasn't been found");
    }

    private void removeCar() {
        System.out.println("Enter car's id:");
        String carId = scanner.next();
        RailroadCar car = null;
        for (String item : this.menuCar.keySet()) {
            if (this.menuCar.containsKey(carId))
                car = this.menuCar.get(carId);
            else
                System.out.println("car with " + carId + "hasn't been found");
        }
        if (car != null) {
            for (String item : this.menuTrain.keySet()) {
                List<RailroadCar> cars = this.menuTrain.get(item).railroadCars;
                if (cars.contains(car))
                    cars.remove(car);
            }
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
            System.out.println("The railroad car " + railroadCar.getId() + " has been attached to trainset");
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

    private void foo() {
        System.out.println("You've picked func 2");
    }


    private void trainInformation() {
        System.out.print("Enter the train ID:");
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
                "rfrc - Refrigerated Railroad Car\n" +
                "lrc - Liquid Railroad Car\n" +
                "grc - Gaseous Railroad Car\n" +
                "erc - Explosive Railroad Car\n" +
                "trc - Toxic Railroad Car\n" +
                "ltrc - Liquid Toxic Railroad Car\n";
    }
}