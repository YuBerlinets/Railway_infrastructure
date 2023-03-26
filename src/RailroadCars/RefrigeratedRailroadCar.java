package RailroadCars;

public class RefrigeratedRailroadCar extends BasicFreightRailroadCar implements ElectricalGrid{
    public static int count = 1;
    //add some unique fields
    public RefrigeratedRailroadCar(String shipper, double netWeight){
        super(shipper, netWeight);
        this.id = "rfrc" + count++; //rfrc stands for RailroadCars.RefrigeratedRailroadCar
    }

    public String toString(){
        return super.toString();
    }

}
