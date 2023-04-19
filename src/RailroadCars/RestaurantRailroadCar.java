package RailroadCars;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RestaurantRailroadCar extends RailroadCar implements ElectricalGrid, Service {
    private int staffMembers;
    private String cuisine;
    private final int tables;
    private int availableTables;
    private Map<String, String> reservations;
    public static int count = 1;

    public RestaurantRailroadCar(String shipper, double netWeight, int staffMembers, int tables) {
        super(shipper, netWeight);
        this.id = "rrc" + count++;//rrc stands for RestaurantRailroadCar
        this.staffMembers = staffMembers;
        this.tables = tables;
        this.availableTables = tables;
        this.cuisine = generateCuisine();
        this.reservations = new HashMap<>();
    }

    public void releaseTable(){
        Scanner sc = new Scanner(System.in);
        if((this.availableTables + 1) <= this.tables){
            System.out.println("Enter name of reserver:");
            String name = sc.next();
            if(this.reservations.containsKey(name)) {
                this.reservations.remove(name);
                System.out.println("Table successfully released");
            }else
                System.out.println("This no reservation for that name");
        }else
            System.out.println("All tables are available");
    }
    //booking table for some amount of people
    public void bookTable(){
        Scanner sc = new Scanner(System.in);
        if((this.availableTables - 1) >= 0) {
            System.out.println("Enter your name:");
            String name = sc.next();
            System.out.println("Enter time and date:");
            String timeDate = sc.next();
            System.out.println("Enter how many people:");
            int ppl = sc.nextInt();
            String reservation = "Name: " + name + " TimeDate: " +timeDate + " People: " + ppl + "\n";
            this.reservations.put(name,reservation);
            this.availableTables -= 1;
        }
        else
            System.out.println("There is no available tables");
    }
    //changing cuisine in the restaurant
    public void changeCuisine(){
        Random random = new Random();
        String[] cuisineArr = {"Asian", "American", "Ukrainian","Polish","Italian"};
        int cuisineIndex = random.nextInt(cuisineArr.length);
        if(!cuisineArr[cuisineIndex].equals(this.cuisine))
            this.cuisine = cuisineArr[cuisineIndex];
        else
            changeCuisine();
    }
    //assigns cuisine to the restaurant while creating a car
    public String generateCuisine() {
        Random random = new Random();
        String[] cuisineArr = {"Asian", "American", "Ukrainian","Polish","Italian"};
        int cuisineIndex = random.nextInt(cuisineArr.length);
        return cuisineArr[cuisineIndex];
    }

    @Override
    public String toString() {
        return super.toString() + " | Cuisine: " + getCuisine() + " | Available tables: " + getAvailableTables();
    }

    public int getTables() {
        return tables;
    }

    public int getAvailableTables() {
        return availableTables;
    }

    public Map<String, String> getReservations() {
        return reservations;
    }
    public int getStaffMembers() {
        return staffMembers;
    }

    public String getCuisine() {
        return cuisine;
    }

    @Override
    public void connectToElectricalGrid() {
        super.connectedToElectricalGrid = true;
    }

    @Override
    public void disconnectFromElectricalGrid() {
        super.connectedToElectricalGrid = false;
    }

    @Override
    public void takeCarToService() {
        super.service = true;
    }

    @Override
    public void takeCarOffService() {
        super.service = false;
    }
}
