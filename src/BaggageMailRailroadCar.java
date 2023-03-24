public class BaggageMailRailroadCar extends RailroadCar{
    public static int count = 1;

    BaggageMailRailroadCar(String shipper, double netWeight) {
        super(shipper, netWeight);
        this.id = "bmrc" + count++; //bmrc stands for Baggage and Mail Railroad Car
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
