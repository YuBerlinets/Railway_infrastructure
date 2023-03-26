import exception.TooBigWeight;
import exception.TooManyRailroadCars;
import exception.TooManyRailroadCarsElecticalGrid;

import java.util.ArrayList;
import java.util.Random;

public class Trainset implements TrainsetConfiguration {
    private String id = "t"; // t stands for Trainset
    private Locomotive locomotive;
    public ArrayList<RailroadCar> railroadCars = new ArrayList<RailroadCar>();
    private int numRailroadCars;
    private double weightLoad;
    private int numRailroadCarsElectricityGrid;
    public static int count = 1;

    Trainset(Locomotive locomotive) {
        this.id = id + count++;
        this.locomotive = locomotive;
    }

    Trainset(Locomotive locomotive, ArrayList<RailroadCar> railroadCars) {
        this.id = id + count++;
        this.locomotive = locomotive;
        this.railroadCars = railroadCars;
    }

    public void addCar(RailroadCar railroadCar) {
        if (this.railroadCars.size() <= TrainsetConfiguration.maxRailroadCars) {
            if (this.getWeightLoad() <= TrainsetConfiguration.maxWeightLoad) {
                if (!railroadCar.isConnectedToElectricalGrid()) {
                    railroadCars.add(railroadCar);
                    this.weightLoad += (railroadCar.getNetWeight() + railroadCar.getGrossWeight());
                } else if (railroadCar.isConnectedToElectricalGrid() &&
                        (this.numRailroadCarsElectricityGrid + 1 <= TrainsetConfiguration.maxNumRailroadCarsElectricityGrid)) {
                    railroadCars.add(railroadCar);
                    this.weightLoad += (railroadCar.getNetWeight() + railroadCar.getGrossWeight());
                    this.numRailroadCarsElectricityGrid++;
                } else {
                    try {
                        throw new TooManyRailroadCarsElecticalGrid();
                    } catch (TooManyRailroadCarsElecticalGrid e) {
                        System.out.println("WARNING: RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                                " because there are too many railroad cars that are already connected to electrical grid in " + this.getId());
                    }
                }
            } else {
                try {
                    throw new TooBigWeight();
                } catch (TooBigWeight e) {
                    System.out.println("WARNING: RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                            " because there will be too big weight in trainset " + this.getId());
                }
            }
        } else {
            try {
                throw new TooManyRailroadCars();
            } catch (TooManyRailroadCars e) {
                System.out.println("WARNING: RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                        " because there are too many cars in trainset " + this.getId());
            }
        }
    }


    public String toString() {
        return "Trainset - ID: " + getId() + " | Total Weight: " + getWeightLoad() + "\t" +
                "Locomotive: " + getLocomotive() + "\t\nCars:" + getRailroadCars() + "\n";
    }

    public String getId() {
        return id;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public ArrayList<RailroadCar> getRailroadCars() {
        return railroadCars;
    }

    public int getNumRailroadCars() {
        return numRailroadCars;
    }

    public double getWeightLoad() {
        return weightLoad;
    }

    public int getNumRailroadCarsElectricityGrid() {
        return numRailroadCarsElectricityGrid;
    }
}
