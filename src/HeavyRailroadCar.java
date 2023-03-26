public class HeavyRailroadCar extends RailroadCar{
    public static int count = 1;

    HeavyRailroadCar(String shipper, double netWeight){
        super(shipper, netWeight);
        this.id = "hrc" + count++; //hrc stands for HeavyRailroadCar
    }

    public String toString(){
        return super.toString();
    }
}
