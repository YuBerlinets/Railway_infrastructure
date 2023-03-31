package RailroadCars;

public class ExplosiveRailroadCar extends RailroadCar implements HeavyFreightRailroadCar{
    private String cargoType;
    public static int count = 1;

    //add unique fields

    public ExplosiveRailroadCar(String shipper, double netWeight, String cargoType){
        super(shipper,netWeight);
        this.cargoType = cargoType;
        this.id = "erc" + count++;//erc stands for RailroadCars.ExplosiveRailroadCar
    }

    public String getCargoType() {
        return cargoType;
    }

    public String toString(){
        return super.toString() + " | Cargotype?: " +getCargoType(); //change Cargotype name for more appropriate
    }
}
