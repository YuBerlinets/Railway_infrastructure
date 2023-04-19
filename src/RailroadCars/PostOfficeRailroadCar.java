package RailroadCars;

public class PostOfficeRailroadCar extends RailroadCar implements ElectricalGrid, Service {
    private int maxCapacityBoxes;
    private int boxes;
    private boolean fragileParcels;// is this car can store fragile parcels
    public static int count = 1;

    public PostOfficeRailroadCar(String shipper, double netWeight, int maxCapacityBoxes, boolean fragileParcels) {
        super(shipper, netWeight);
        this.id = "porc" + count++; //porc stands for post office railroad car
        this.maxCapacityBoxes = maxCapacityBoxes;
        this.fragileParcels = fragileParcels;
    }

    public String toString() {
        return super.toString() + " | Boxes: " + getBoxes() + " | Fragile Parcels: " + (isFragileParcels() ? "Yes" : "No");
    }

    public void addBoxes(int boxes) {
        if (this.boxes + boxes <= maxCapacityBoxes)
            this.boxes += boxes;
        else
            System.out.println("There isn't place left for " + boxes + " in car " + this.getId());
    }

    public void removeBoxes(int boxes) {
        if (this.boxes - boxes >= 0)
            this.boxes -= boxes;
        else
            System.out.println(boxes + " can't be removed, because there are " + this.boxes + " in car " + this.getId());

    }

    public int getBoxes() {
        return boxes;
    }

    public int getMaxCapacityBoxes() {
        return maxCapacityBoxes;
    }

    public boolean isFragileParcels() {
        return fragileParcels;
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
