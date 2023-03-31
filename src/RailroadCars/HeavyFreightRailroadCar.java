package RailroadCars;

public class HeavyFreightRailroadCar extends RailroadCar{
    public static int count = 1;

    HeavyFreightRailroadCar(String shipper, double netWeight){
        super(shipper, netWeight);
        this.id = "hfrc" + count++; //hfrc stands for HeavyFreightRailroadCar
    }

    public String toString(){
        return super.toString();
    }
}
