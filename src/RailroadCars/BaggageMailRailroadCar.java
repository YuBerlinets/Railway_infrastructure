package RailroadCars;

import exception.TooManyBaggage;

public class BaggageMailRailroadCar extends RailroadCar implements Service {
    private int numSuitcase;
    private int maxNumSuitcase;
    private boolean storingPets;
    private int placePets;
    public static int count = 1;


    public BaggageMailRailroadCar(String shipper, double netWeight, int maxNumSuitcase, boolean storingPets) {
        super(shipper, netWeight);
        this.id = "bmrc" + count++; //bmrc stands for Baggage and Mail Railroad Car
        this.maxNumSuitcase = maxNumSuitcase;
        this.storingPets = storingPets;
        if (this.storingPets)
            this.placePets = 0;
        else
            this.placePets = 5;
    }

    public void loadBaggage(int suitcases) {
        if ((this.numSuitcase + suitcases) <= maxNumSuitcase)
            this.maxNumSuitcase += suitcases;
        else
            try {
                throw new TooManyBaggage();
            } catch (TooManyBaggage e) {
                System.out.println("There too many suitcases in this car");
            }
    }
    public void unloadPets(int pets){
        if (!this.service) {
            if (this.storingPets) {
                if ((this.placePets - pets) > 0) {
                    this.placePets -= pets;
                } else {
                    System.out.println("WARNING: " + pets + " pets cannot be remove from " + this.getId() + " car."
                            + " Because there isn't any pets in it");
                }
            } else {
                System.out.println("Car " + this.getId() + " is not allowed to store pets");
            }
        } else
            System.out.println("This car " + getId() + " is on service");
    }
    public void loadPets(int pets) {
        if (!this.service) {
            if (this.storingPets) {
                if ((this.placePets + pets) < this.placePets) {
                    this.placePets += pets;
                } else {
                    System.out.println("WARNING: " + pets + " pets cannot be added to " + this.getId() + " car."
                            + " Because there are " + getPlacePets() + "already in it and max number is 5");
                }
            } else {
                System.out.println("Car " + this.getId() + " is not allowed to store pets");
            }
        } else
            System.out.println("This car " + getId() + " is on service");
    }

    public int getNumSuitcase() {
        return numSuitcase;
    }

    public int getMaxNumSuitcase() {
        return maxNumSuitcase;
    }

    public boolean isStoringPets() {
        return storingPets;
    }

    public int getPlacePets() {
        return placePets;
    }

    @Override
    public String toString() {
        return super.toString();
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
