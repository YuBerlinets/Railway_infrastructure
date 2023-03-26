package RailroadCars;

public class ToxicRailroadCar extends HeavyFreightRailroadCar{
    private String toxicMaterialsType;
    public static int count = 1;

    //add unique fields

    public ToxicRailroadCar(String shipper, double netWeight, String toxicMaterialsType){
        super(shipper,netWeight);
        this.toxicMaterialsType = toxicMaterialsType;
        this.id = "trc" + count++;//trc stands for RailroadCars.ToxicRailroadCar
    }

    public String getToxicMaterialsType() {
        return toxicMaterialsType;
    }

    public String toString(){
        return super.toString() + " | Toxic materials type: " +getToxicMaterialsType();
    }
}
