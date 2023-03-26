public class LiquidRailroadCar extends BasicFreightRailroadCar {
    private String liquidType;
    public static int count = 1;

    // add unique fields

    LiquidRailroadCar(String shipper, double netWeight, String liquidType){
        super(shipper, netWeight);
        this.liquidType = liquidType;
        this.id = "lrc" + count++; //lrc stands for LiquidRailroadCar
    }

    public String getLiquidType() {
        return liquidType;
    }

    public String toString(){
        return super.toString() + " | Liquid type: " + getLiquidType();
    }
}
