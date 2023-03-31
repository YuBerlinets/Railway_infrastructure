import exception.RailroadHazard;
import exception.TooBigWeight;

import java.util.ArrayList;
import java.util.Random;

public class Locomotive {
    private String id = "l"; // l - stands for locomotive
    private String name;
    private Station homeRailwayStation;
    private Station sourceStation;
    private Station destinationStation;
    private double speed = 150;
    private int maxNumCar;
    private double maxWeight;
    private int maxNumRailroadCarsElectricityGrid;

    private static int count = 1;


    Locomotive(String name,int maxNumCar,double maxWeight, int maxNumRailroadCarsElectricityGrid, Station homeRailwayStation, Station sourceStation, Station destinationStation) {
        this.id = id + count++;
        this.name = name;
        this.maxNumCar = maxNumCar;
        this.maxWeight = maxWeight;
        this.maxNumRailroadCarsElectricityGrid = maxNumRailroadCarsElectricityGrid;
        this.homeRailwayStation = homeRailwayStation;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
    }

    public void adjustSpeed() throws InterruptedException {
        Random random = new Random();
        while (true) {
            if (speed < 200) {
                double delta = Math.round(speed * 0.03);
                boolean increase = random.nextBoolean();
                if (increase) {
                    speed += delta;
                } else {
                    speed -= delta;
                }
                //System.out.println("Train " + getId() + " 's speed is " + getSpeed());
                Thread.sleep(1000);
            } else {
                try {
                    throw new RailroadHazard();
                } catch (RailroadHazard e) {
                    System.out.println(("WARNING: Locomotive " + getId() + " is out of speed limit | Current speed is " + getSpeed()));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Name: " + getName() +
                " | Current speed: " + getSpeed() + "\nHome Station: " + getHomeRailwayStation() +
                "\nSource: " + getSourceStation() +
                "\nDestination: " + getDestinationStation();
    }


    public int getMaxNumRailroadCarsElectricityGrid() {
        return maxNumRailroadCarsElectricityGrid;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
    public int getMaxNumCar() {
        return maxNumCar;
    }

    public double getSpeed() {
        return speed;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }


    public Station getHomeRailwayStation() {
        return homeRailwayStation;
    }


    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

}
