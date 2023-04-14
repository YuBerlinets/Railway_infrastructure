import exception.RailroadHazard;
import exception.TooBigWeight;

import java.util.*;

public class Locomotive {
    private String id = "l"; // l - stands for locomotive
    private String name;
    private Station homeRailwayStation;
    private Station sourceStation;
    private Station destinationStation;
    private double speed = 120;
    private boolean onRoute = false;
    private int maxNumCar;
    private double maxWeight;
    private int maxNumRailroadCarsElectricityGrid;
    private Route route;
    private Route reverseRoute;

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
        this.onRoute = true;
    }
    public void generateRoute() {
        Route route = new Route();
        Route reverseRoute = new Route(); // create a new Route object for storing the reverse route
        Station source = this.getSourceStation();
        Station destination = this.getDestinationStation();
        Map<Station, Double> distances = new HashMap<>();
        Map<Station, Station> prev = new HashMap<>();
        PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        distances.put(source, 0.0);
        pq.offer(source);

        while (!pq.isEmpty()) {
            Station currentStation = pq.poll();
            if (currentStation == destination) {
                break;
            }
            double currentDistance = distances.get(currentStation);
            for (Station nextStation : currentStation.getIntersectsWith().keySet()) {
                double distance = currentDistance + currentStation.getDistanceTo(nextStation);
                if (!distances.containsKey(nextStation) || distance < distances.get(nextStation)) {
                    distances.put(nextStation, distance);
                    prev.put(nextStation, currentStation);
                    pq.offer(nextStation);
                }
            }
        }

        if (!prev.containsKey(destination)) {
            System.out.println("This no connection between those stations" + this.getSourceStation() + " "
                    + this.getDestinationStation());
            NullPointerException npe = new NullPointerException("no connection");
            System.out.println(npe);
        }

        Station currentStation = destination;
        double totalDistance = 0.0;
        while (currentStation != source) {
            Station previousStation = prev.get(currentStation);
            double distance = previousStation.getDistanceTo(currentStation);
            totalDistance += distance;
            route.addStation(previousStation);
            reverseRoute.addStation(currentStation); // add current station to the reverse route
            currentStation = previousStation;
        }
        route.reverse();
        route.addStation(destination);
        reverseRoute.addStation(source);
        route.calculatedDistance(totalDistance);
        this.route = route;
        reverseRoute.calculatedDistance(totalDistance); // calculate the distance for the reverse route
        this.reverseRoute = reverseRoute; // store the reverse route in the reverseRoute field
    }

    public void adjustSpeed() throws InterruptedException {
        Random random = new Random();
        while (this.onRoute) {
            if (speed < 200) {
                double delta = Math.round(speed * 0.03);
                boolean speedChanging = random.nextBoolean();
                if (speedChanging) {
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
        return "id: " + getId() + " | Name: " + getName() +
                " | Current speed: " + getSpeed() + "\nHome Station: " + getHomeRailwayStation() +
                "\nSource: " + getSourceStation() +
                "\nDestination: " + getDestinationStation();
    }

    public Route getReverseRoute() {
        return reverseRoute;
    }

    public Route getRoute() {
        return route;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isOnRoute() {
        return onRoute;
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
