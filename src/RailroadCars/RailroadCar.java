package RailroadCars;

import java.util.Date;

public class RailroadCar implements Comparable<RailroadCar> {
    public String id;
    private String shipper;
    private double netWeight;
    private double grossWeight;

    protected boolean connectedToElectricalGrid;
    protected boolean service;
    protected boolean attached;


    public static int count = 1;


    public RailroadCar(String shipper, double netWeight) {
        this.id = "rc" + count++;//rc stands for railroad car
        this.shipper = shipper;
        this.netWeight = netWeight;
        this.grossWeight = netWeight;
    }

    public void addCargo(double weight) {
        this.grossWeight += weight;
    }

    public String toString() {
        return "\nID: " + getId() + " | Shipper: " + getShipper() +
                " | Electrical grid: " + (isConnectedToElectricalGrid() ? "Connected" : "Not connected") +
                " | netWeight: " + getNetWeight() + " | grossWeight: " + getGrossWeight();
    }

    public boolean isOnService() {
        return service;
    }

    public boolean isConnectedToElectricalGrid() {
        return connectedToElectricalGrid;
    }

    public String getId() {
        return id;
    }

    public String getShipper() {
        return shipper;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void attachCar(){
        this.attached = true;
    }
    @Override
    public int compareTo(RailroadCar railroadCar) {
        if (this.grossWeight == railroadCar.getGrossWeight())
            return 0;
        else if (this.grossWeight > railroadCar.getGrossWeight())
            return 1;
        else
            return -1;
    }
}
