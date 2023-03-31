import RailroadCars.RailroadCar;
import exception.TooBigWeight;
import exception.TooManyRailroadCars;
import exception.TooManyRailroadCarsElecticalGrid;

import java.util.ArrayList;

public class Trainset implements TrainsetConfiguration {
    private String id = "t"; // t stands for Trainset
    private Locomotive locomotive;
    public ArrayList<RailroadCar> railroadCars = new ArrayList<RailroadCar>();
    private int trainsetNumberCars;
    private double trainsetWeightLoad;
    private int trainsetCarsConnectedElectricalGrid;


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
        int numCar = this.getLocomotive().getMaxNumCar(); // getting num of max car from locomotive
        double maxWeightLoad = this.getLocomotive().getMaxWeight();// getting a max weight load
        int numCarsConnectedElectricalGrid = this.getLocomotive().getMaxNumRailroadCarsElectricityGrid();// getting a max number of cars connected to electrical grid
        if (this.railroadCars.size() <= numCar) {
            if (this.trainsetWeightLoad <= maxWeightLoad) {
                if (!railroadCar.isConnectedToElectricalGrid()) {
                    railroadCars.add(railroadCar);
                    this.trainsetWeightLoad += (railroadCar.getNetWeight() + railroadCar.getGrossWeight());
                } else if (railroadCar.isConnectedToElectricalGrid() &&
                        (this.trainsetCarsConnectedElectricalGrid + 1 <= numCarsConnectedElectricalGrid)) {
                    railroadCars.add(railroadCar);
                    this.trainsetWeightLoad += (railroadCar.getNetWeight() + railroadCar.getGrossWeight());
                    this.trainsetCarsConnectedElectricalGrid++;
                } else {
                    try {
                        throw new TooManyRailroadCarsElecticalGrid();
                    } catch (TooManyRailroadCarsElecticalGrid e) {
                        System.out.println("WARNING: RailroadCars.RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                                " because there are too many railroad cars that are already connected to electrical grid in " + this.getId());
                    }
                }
            } else {
                try {
                    throw new TooBigWeight();
                } catch (TooBigWeight e) {
                    System.out.println("WARNING: RailroadCars.RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                            " because there will be too big weight in trainset " + this.getId());
                }
            }
        } else {
            try {
                throw new TooManyRailroadCars();
            } catch (TooManyRailroadCars e) {
                System.out.println("WARNING: RailroadCars.RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                        " because there are too many cars in trainset " + this.getId());
            }
        }
    }


    public String toString() {
        return "Trainset - ID: " + getId() + " | Total Weight: " + getTrainsetWeightLoad() + "\n"+
                "Locomotive: " + getLocomotive() + "\t\nCars:" + getRailroadCars() + "\n";
    }

    public int getTrainsetNumberCars() {
        return trainsetNumberCars;
    }

    public double getTrainsetWeightLoad() {
        return trainsetWeightLoad;
    }

    public int getTrainsetCarsConnectedElectricalGrid() {
        return trainsetCarsConnectedElectricalGrid;
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

}
