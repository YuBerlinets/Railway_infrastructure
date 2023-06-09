import RailroadCars.RailroadCar;
import exception.TooBigWeight;
import exception.TooManyRailroadCars;
import exception.TooManyRailroadCarsElecticalGrid;

import java.util.ArrayList;
import java.util.List;

public class Trainset implements Comparable<Trainset> {
    private String id;
    private Locomotive locomotive;
    public ArrayList<RailroadCar> railroadCars = new ArrayList<>();
    private double trainsetWeightLoad;
    private int trainsetCarsConnectedElectricalGrid;
    private Route route;
    private Station currentStation;
    private double speed;
    private boolean onRoute;
    private double percentage;

    public static int count = 1;

    Trainset(Locomotive locomotive) {
        this.id = "t" + count++;// t stands for Trainset
        this.locomotive = locomotive;
        this.route = locomotive.getRoute();
        this.currentStation = route.getRouteStations().get(0);
        this.speed = locomotive.getSpeed();
    }

    public void moveRoute() throws InterruptedException, NullPointerException {
        List<Station> routeForward = this.getLocomotive().getRoute().getRouteStations();
        List<Station> routeBack = this.getLocomotive().getReverseRoute().getRouteStations();
        this.onRoute = true;
        Station startStation = routeForward.get(0);
        Station lastStation = routeForward.get(routeForward.size() - 1);
        boolean forward = true;
        double completedDistance = 0;
        double totalDistance = this.getLocomotive().getRoute().getTotalDistance();
        List<Station> route;
        while (this.isOnRoute()) {
            if (forward)
                route = routeForward;
            else
                route = routeBack;
            for (int i = 0; i < route.size() - 1; i++) {
                Station station = route.get(i);
                Station nextStation = route.get(i + 1);
                double distance = station.getIntersectsWith().get(nextStation);
                if (nextStation.isAvailable()) {
                    double time = ((distance / this.speed) * 1000);
                    nextStation.setUnavailability();
                    try {
                        Thread.sleep((long) time);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    this.currentStation.setAvailability();
                    this.currentStation = nextStation;
                    double prevSpeed = this.speed;
                    this.locomotive.setSpeed(0);
                    completedDistance += distance;
                    this.percentage = (completedDistance / totalDistance) * 100;
                    //System.out.println(getId() + " is on station: " + this.currentStation);
                    Thread.sleep(2000);
                    this.locomotive.setSpeed(prevSpeed);
                    if (this.currentStation == lastStation) {
                        //System.out.println(this.getId() + " train has reached a endpoint");
                        prevSpeed = this.speed;
                        this.speed = 0;
                        this.percentage = 100;
                        Thread.sleep(30000);
                        this.percentage = 0;
                        completedDistance = 0;
                        this.speed = prevSpeed;
                        forward = true;
                    }
                    if (this.currentStation == startStation) {
                        //System.out.println(this.getId() + " train has reached an startpoint");
                        prevSpeed = this.speed;
                        this.speed = 0;
                        this.percentage = 100;
                        Thread.sleep(30000);
                        this.percentage = 0;
                        completedDistance = 0;
                        this.speed = prevSpeed;
                        forward = false;
                    }
                } else {
//                    System.out.println(nextStation + " is currently not available"); // to check when two trainset meet at the route and then wait
//                    System.out.println("Train: " + getId() + " is near the station: " + nextStation + ". Waiting for availability");
                    double prevSpeed = this.speed;
                    this.speed = 0;
                    while (this.isOnRoute()) {
                        Thread.sleep(1000);
                        if (nextStation.isAvailable()) {
                            this.speed = prevSpeed;
                            break;
                        }
                    }
                }
            }
        }
    }


    public void addCar(RailroadCar railroadCar) {
        int numCar = this.getLocomotive().getMaxNumCar(); // getting num of max car from locomotive
        double maxWeightLoad = this.getLocomotive().getMaxWeight();// getting a max weight load
        int numCarsConnectedElectricalGrid = this.getLocomotive().getMaxNumRailroadCarsElectricityGrid();// getting a max number of cars connected to electrical grid
        if (this.railroadCars.size() + 1 <= numCar) {
            if (this.trainsetWeightLoad + (railroadCar.getNetWeight() + railroadCar.getGrossWeight()) <= maxWeightLoad) {
                if (!railroadCar.isConnectedToElectricalGrid()) {
                    railroadCars.add(railroadCar);
                    railroadCar.attachCar();
                    this.trainsetWeightLoad += (railroadCar.getNetWeight() + railroadCar.getGrossWeight());
                } else if (railroadCar.isConnectedToElectricalGrid() &&
                        (this.trainsetCarsConnectedElectricalGrid + 1 <= numCarsConnectedElectricalGrid)) {
                    railroadCars.add(railroadCar);
                    railroadCar.attachCar();
                    this.trainsetWeightLoad += (railroadCar.getNetWeight() + railroadCar.getGrossWeight());
                    this.trainsetCarsConnectedElectricalGrid++;
                } else {
                    try {
                        throw new TooManyRailroadCarsElecticalGrid();
                    } catch (TooManyRailroadCarsElecticalGrid e) {
                        System.out.println("WARNING: RailroadCar - " + railroadCar.getId() + " - can't be attached," +
                                " because there are too many railroad cars that are already connected " +
                                "to electrical grid in " + this.getId());
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

    public void removeCar(RailroadCar railroadCar) {
        if (this.railroadCars.contains(railroadCar))
            this.railroadCars.remove(railroadCar);
        else
            System.out.println("Car " + railroadCar.getId() + " hasn't been found");
    }

    public String toString() {
        return "Trainset - ID: " + getId() + " | Current Station: " + getCurrentStation() +
                " | Total Route Distance " + String.format("%.2f", getRoute().getTotalDistance()) + " | Completed path: "
                + String.format("%.2f", getPercentage()) + "%" +
                "\nTotal Weight: " + getTrainsetWeightLoad() + "\n" +
                "Locomotive: " + getLocomotive() + "\t\nCars:" + getRailroadCars() + "\n";
    }

    public double getPercentage() {
        return percentage;
    }

    public double getTrainsetWeightLoad() {
        return trainsetWeightLoad;
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

    public void stopMovingOnRoute() {
        this.onRoute = false;
    }

    public Route getRoute() {
        return route;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isOnRoute() {
        return onRoute;
    }

    public void setOnRoute(boolean onRoute) {
        this.onRoute = onRoute;
    }

    @Override
    public int compareTo(Trainset trainset) {
        if (route.getTotalDistance() == trainset.getRoute().getTotalDistance())
            return 0;
        else if (route.getTotalDistance() > trainset.getRoute().getTotalDistance())
            return -1;
        else
            return 1;
    }
}
