package RailroadCars;

public class BasicFreightRailroadCar extends RailroadCar{
    public static int count = 1;
    BasicFreightRailroadCar(String shipper, double netWeight) {
        super(shipper, netWeight);
        this.id = "bfrc" + count++;//bfrc stands for RailroadCars.BasicFreightRailroadCar
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
