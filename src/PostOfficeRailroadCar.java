public class PostOfficeRailroadCar extends RailroadCar implements ElectricalGrid{
    public static int count = 1;

    PostOfficeRailroadCar(String shipper, double netWeight){
        super(shipper, netWeight);
        this.id = "porc" + count++; //porc post office railroad car
    }

    public String toString(){
        return super.toString();
    }
}
