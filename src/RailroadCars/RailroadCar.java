package RailroadCars;

import java.util.Date;

public class RailroadCar {
    public String id;
    private String shipper;
    private String securityInformation;
    private double netWeight;
    private double grossWeight;
    private Date dateOfManufacture;
    protected boolean connectedToElectricalGrid;
    protected boolean service;



    public static int count = 1;


    public RailroadCar(String shipper, double netWeight) {
        this.id = "rc" + count++;//rc stands for railroad car
        this.shipper = shipper;
        this.netWeight = netWeight;
    }

    public void addCargo(double weight){
        this.grossWeight += weight;
    }

//    public void connectToElectricalGrid(){
//        this.connectedToElectricalGrid = true;
//    }
//    public void disconnectFromElectricalGrid(){
//        this.connectedToElectricalGrid = false;
//    }

    public String toString(){
        return "\nID: " + getId() + " | Shipper: " + getShipper() +
                " | Electrical grid: " + (isConnectedToElectricalGrid()? "Connected" : "Not connected") +
                " | netWeight: " + getNetWeight();
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

    public String getSecurityInformation() {
        return securityInformation;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }


    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }
}
