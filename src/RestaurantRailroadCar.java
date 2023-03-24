public class RestaurantRailroadCar extends RailroadCar{
    public static int count = 1;

    //add electricity grid!!!

    RestaurantRailroadCar(String shipper, double netWeight) {
        super(shipper, netWeight);
        this.id = "rrc" + count++;//rrc stands for RestaurantRailroadCar
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
