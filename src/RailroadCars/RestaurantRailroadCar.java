package RailroadCars;

public class RestaurantRailroadCar extends RailroadCar implements ElectricalGrid,Service{
    private int staffMembers;
    private boolean service;
    private boolean connectedToElectricalGrid;
    public static int count = 1;

    public RestaurantRailroadCar(String shipper, double netWeight, int staffMembers) {
        super(shipper, netWeight);
        this.id = "rrc" + count++;//rrc stands for RestaurantRailroadCar
        this.staffMembers = staffMembers;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void connectToElectricalGrid() {
        super.connectedToElectricalGrid = true;
    }

    @Override
    public void disconnectFromElectricalGrid() {
        super.connectedToElectricalGrid = false;
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
