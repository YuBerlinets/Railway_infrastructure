package RailroadCars;

public class RestaurantRailroadCar extends RailroadCar implements ElectricalGrid{
    public static int count = 1;
    private int staffMembers;

    public RestaurantRailroadCar(String shipper, double netWeight, int staffMembers) {
        super(shipper, netWeight);
        this.id = "rrc" + count++;//rrc stands for RestaurantRailroadCar
        this.staffMembers = staffMembers;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
