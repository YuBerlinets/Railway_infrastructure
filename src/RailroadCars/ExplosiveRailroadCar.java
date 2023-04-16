package RailroadCars;

import java.util.Random;

public class ExplosiveRailroadCar extends RailroadCar implements HeavyFreightRailroadCar, Service {
    private String cargoType;
    private double cargoWeight;
    private int securityGuard;
    private char dangerType; //from 'C' to 'A', where 'A' is the most dangerous
    private boolean monitoring;
    public static int count = 1;


    public ExplosiveRailroadCar(String shipper, double netWeight, int securityGuard, char dangerType, String cargoType) {
        super(shipper, netWeight);
        this.id = "erc" + count++;//erc stands for ExplosiveRailroadCar
        this.securityGuard = securityGuard;
        this.dangerType = dangerType;
        this.cargoType = cargoType;
        if(dangerType == 'B' || dangerType == 'A')
            this.monitoring = true;
        else
            this.monitoring = false;
    }

    public String toString() {
        return super.toString() + " | Security Guards: " + getSecurityGuard() + " | Cargotype: " + getCargoType() +
                " | CargoWeight: " + getCargoWeight();
    }

    public void remoteMonitoring(){
        Random random = new Random();
        boolean property = random.nextBoolean();
        if(this.monitoring){
            if(property)
                System.out.println("Everything is great in " + getId() + " car");
            else
                System.out.println("Something is wrong, please check condition of the " + getId() + " car");
        }else
            System.out.println("As car " + getId() + "is of type" + getDangerType() + " it's not allowed to be remotely checked");

    }
    public void addSecurity(int ppl){
        this.securityGuard += ppl;
    }
    public void removeSecurity(int ppl){
        if((this.securityGuard - ppl) > 0){
            this.securityGuard -= ppl;
        }
        else
            System.out.println("This type of car requires to have at least one security guard");
    }
    @Override
    public void addCargo(String cargoType, double cargoWeight) {
        if (this.service) {
            System.out.println("Cargo can't be added, because car is on service");
        } else {
            this.cargoType += ("," + cargoType);
            this.cargoWeight += cargoWeight;
        }
    }

    @Override
    public void checkPressure() {
        System.out.println("Pressure is not measured in this car");
    }

    @Override
    public void takeCarToService() {
        super.service = true;
    }

    @Override
    public void takeCarOffService() {
        super.service = false;
    }

    public String getCargoType() {
        return cargoType;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public int getSecurityGuard() {
        return securityGuard;
    }

    public char getDangerType() {
        return dangerType;
    }
}
