public class ToxicRailroadCar extends HeavyRailroadCar{
    private String toxicMaterialsType;
    public static int count = 1;

    //add unique fields

    ToxicRailroadCar(String shipper, double netWeight, String toxicMaterialsType){
        super(shipper,netWeight);
        this.toxicMaterialsType = toxicMaterialsType;
        this.id = "trc" + count++;//trc stands for ToxicRailroadCar
    }

    public String getToxicMaterialsType() {
        return toxicMaterialsType;
    }

    public String toString(){
        return super.toString() + " | Toxic materials type: " +getToxicMaterialsType();
    }
}
