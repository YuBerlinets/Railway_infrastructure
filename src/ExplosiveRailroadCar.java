public class ExplosiveRailroadCar extends HeavyRailroadCar{
    private String cargoType;
    public static int count = 1;

    //add unique fields

    ExplosiveRailroadCar(String shipper, double netWeight, String cargoType){
        super(shipper,netWeight);
        this.cargoType = cargoType;
        this.id = "erc" + count++;//erc stands for ExplosiveRailroadCar
    }

    public String getCargoType() {
        return cargoType;
    }

    public String toString(){
        return super.toString() + " | Cargotype?: " +getCargoType(); //change Cargotype name for more appropriate
    }
}
