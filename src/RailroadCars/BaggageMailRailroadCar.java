package RailroadCars;

import exception.TooManyBaggage;

public class BaggageMailRailroadCar extends RailroadCar implements Service {
    private int numSuitcase;
    private int maxNumSuitcase;
    public static int count = 1;

    public BaggageMailRailroadCar(String shipper, double netWeight) {
        super(shipper, netWeight);
        this.id = "bmrc" + count++; //bmrc stands for Baggage and Mail Railroad Car
    }

    public void takeBaggage(int suitcases){
        if(this.numSuitcase + suitcases <= maxNumSuitcase)
            this.maxNumSuitcase += suitcases;
        else
            try {
                throw new TooManyBaggage();
            } catch (TooManyBaggage e) {
                System.out.println("There too many suitcases in this car");
            }
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
