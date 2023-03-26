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
    private double speed = 160;

    private static int count = 1;

    Locomotive(String name, Station homeRailwayStation, Station sourceStation, Station destinationStation) {
        this.id = id + count++;
        this.name = name;
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
                    throw new RuntimeException("Locomotive " + getId() + " is out of speed limit");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Name: " + getName() + " | Current speed: " + getSpeed() + "\nHome Station: " + getHomeRailwayStation() +
                "\nSource: " + getSourceStation() +
                "\nDestination: " + getDestinationStation();
    }


    public double getSpeed() {
        return speed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getHomeRailwayStation() {
        return homeRailwayStation;
    }

    public void setHomeRailwayStation(Station homeRailwayStation) {
        this.homeRailwayStation = homeRailwayStation;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(Station sourceStation) {
        this.sourceStation = sourceStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }


}
