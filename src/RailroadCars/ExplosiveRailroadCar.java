package RailroadCars;

public class ExplosiveRailroadCar extends RailroadCar implements HeavyFreightRailroadCar,Service{
    private String cargoType;
    private double cargoWeight;
    private double pressure = 30; //in meaning psi
    public static int count = 1;


    //add unique fields

    public ExplosiveRailroadCar(String shipper, double netWeight, String cargoType){
        super(shipper,netWeight);
        this.cargoType = cargoType;
        this.id = "erc" + count++;//erc stands for RailroadCars.ExplosiveRailroadCar
    }

    public String toString(){
        return super.toString() + " | Cargotype: " +getCargoType() + " | CargoWeight: " + getCargoWeight(); //change Cargotype name for more appropriate
    }

    @Override
    public void addCargo(String cargoType, double cargoWeight) {
        if(this.service){
            System.out.println("The pressure is 0, because car is in service");
        }else{
            this.cargoType += ("," + cargoType);
            this.cargoWeight += cargoWeight;
        }
    }

    @Override
    public void checkPressure() {
        if(this.service){
            System.out.println("The pressure is 0, because car is in service");
        }else{
            if (this.pressure > 40) {
                System.out.println("Pressure is higher than it requires");
            } else if (this.pressure < 20) {
                System.out.println("Pressure is lower that it requires");
            } else
                System.out.println("Pressure is in normal condition");
        }
    }
    @Override
    public void takeCarToService() {
        super.service = true;
    }

    @Override
    public void takeCarOffService() {
        super.service = false;
    }

    public String getCargoType() {
        return cargoType;
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public double getPressure() {
        return pressure;
    }
}
