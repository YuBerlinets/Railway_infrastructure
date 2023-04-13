import RailroadCars.RailroadCar;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Map<Integer, Map<Integer, Runnable>> menu;
    private Scanner scanner;
    Map<String, Trainset> menuTrain = new HashMap<>();

    public Menu(Map<String, Trainset> menuTrain) {
        this.menu = new HashMap<>();
        this.menuTrain = menuTrain;
        // Category A functions
        Map<Integer, Runnable> trains = new HashMap<>();
        trains.put(1, this::addCar);
        trains.put(2, this::deleteCars);
        trains.put(3, this::trainInformation);
        menu.put(1, trains);

        //Category B functions
        Map<Integer, Runnable> cars = new HashMap<>();
        cars.put(1, this::functionA2);
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

        while (choice != 0) {
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

            // Call the selected function using the HashMap
            if (choice >= 1 && choice <= 5) {
                Map<Integer, Runnable> category = menu.get(choice);
                System.out.println("Functions:");
                if(choice == 1){
                    System.out.println("1. Add a car");
                    System.out.println("2. Delete cars");
                    System.out.println("3. Train information");
                }else if(choice == 2){
                    System.out.println("1. Create new Car");
                    System.out.println("2. Smth hz");
                    System.out.println("3. Car information");
                }else if(choice == 3){
                    System.out.println("1. Show information about abbreaviation");
                }else if(choice == 4){
                    System.out.println("1. Function D1");
                    System.out.println("2. Function D2");
                    System.out.println("3. Function D3");
                }else if(choice == 5){
                    System.out.println("1. Function E1");
                    System.out.println("2. Function E2");
                    System.out.println("3. Function E3");
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
            } else if (choice != 0) {
                System.out.println("Invalid choice.");
            }
            System.out.println();
        }
    }


    void addCar() {
        System.out.print("Enter the train ID: my here ");
        String trainId = scanner.next();
        Trainset trainset = null;
        for (String id : this.menuTrain.keySet()){
            if(trainId.equals(id))
                trainset = menuTrain.get(id);
        }
        System.out.println(trainset);
        System.out.print("Enter the train ID: my here ");
        String carId = scanner.next();
        RailroadCar railroadCar = null;

    }

    private void functionA2(){
        System.out.println("You've picked func 2");
    }
    private void deleteCars(){
        System.out.println("You've picked func 2");
    }
    private void trainInformation(){
        System.out.print("Enter the train ID: my here ");
        String trainId = scanner.next();
        Trainset trainset = null;
        for (String id : this.menuTrain.keySet()){
            if(trainId.equals(id))
                trainset = menuTrain.get(id);
        }
        System.out.println(trainset);
    }
}