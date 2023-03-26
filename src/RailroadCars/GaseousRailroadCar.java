package RailroadCars;

public class GaseousRailroadCar extends BasicFreightRailroadCar{
    private String gasType;
    public static int count = 1;

    // add unique fields

    public GaseousRailroadCar(String shipper, double netWeight, String gasType){
        super(shipper, netWeight);
        this.gasType = gasType;
        this.id = "grc" + count++; //grc stands for RailroadCars.GaseousRailroadCar
    }

    public String getGasType() {
        return gasType;
    }

    public String toString(){
        return super.toString() + " | Gas type: " + getGasType();
    }
}
