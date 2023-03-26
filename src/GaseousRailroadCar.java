public class GaseousRailroadCar extends BasicFreightRailroadCar{
    private String gasType;
    public static int count = 1;

    // add unique fields

    GaseousRailroadCar(String shipper, double netWeight, String gasType){
        super(shipper, netWeight);
        this.gasType = gasType;
        this.id = "grc" + count++; //grc stands for GaseousRailroadCar
    }

    public String getGasType() {
        return gasType;
    }

    public String toString(){
        return super.toString() + " | Gas type: " + getGasType();
    }
}
