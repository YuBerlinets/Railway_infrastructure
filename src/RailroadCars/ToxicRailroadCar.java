package RailroadCars;

import java.util.Random;
import java.util.Scanner;

public class ToxicRailroadCar extends RailroadCar implements HeavyFreightRailroadCar, Service {
    private String toxicMaterialsType;
    private boolean storingDiffTypes;
    private double cargoWeight;
    public static int count = 1;

    public ToxicRailroadCar(String shipper, double netWeight, String toxicMaterialsType) {
        super(shipper, netWeight);
        this.id = "trc" + count++;//trc stands for ToxicRailroadCar
        this.toxicMaterialsType = toxicMaterialsType;
        Random random = new Random();
        this.storingDiffTypes = random.nextBoolean();
    }

    public String toString() {
        return super.toString() + " | Toxic materials type: **SECRET**" +
                " | Store Diff Materials: " + (isStoringDiffTypes() ? "Yes" : "No");
    }

    //in order to check type of materials on this car, user needs to write a password
    public void checkMaterialType() {
        String password = "check";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password to check content of " + getId() + " car");
        String attempt = sc.next();
        if (attempt.equals(password))
            System.out.println(getToxicMaterialsType());
        else
            System.out.println("Access denied");
    }

    //adding different materials
    public void addMaterial(String material) {
        if(!this.service) {
            if (this.storingDiffTypes)
                this.toxicMaterialsType += "," + material;
            else
                System.out.println("This car " + getId() + " is not allowed to store different materials at the same time");
        }else
            System.out.println("New materials can't be added,since car is on service");
    }

    public String getToxicMaterialsType() {
        return toxicMaterialsType;
    }

    public boolean isStoringDiffTypes() {
        return storingDiffTypes;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    @Override
    public void addCargo(String cargoType, double cargoWeight) {
        if (this.service) {
            System.out.println("Cargo can't be added, because car is on service");
        } else {
            if (this.storingDiffTypes)
                this.toxicMaterialsType += ("," + cargoType);
            this.cargoWeight += cargoWeight;
        }
    }

    @Override
    public void checkPressure() {
        if (this.service)
            System.out.println("Pressure can't be measured,because car is on service");
        else
            System.out.println("Pressure is not measured in this car " + getId());
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
