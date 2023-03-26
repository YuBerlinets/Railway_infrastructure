package RailroadCars;

public class HeavyFreightRailroadCar extends RailroadCar{
    public static int count = 1;

    HeavyFreightRailroadCar(String shipper, double netWeight){
        super(shipper, netWeight);
        this.id = "hrc" + count++; //hrfc stands for RailroadCars.HeavyFreightRailroadCar
    }

    public String toString(){
        return super.toString();
    }
}
