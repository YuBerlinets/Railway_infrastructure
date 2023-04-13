package RailroadCars;

public class LiquidToxicRailroadCar extends LiquidRailroadCar implements HeavyFreightRailroadCar,Service{
    private String cargoType;
    private double cargoWeight;
    private double pressure = 30; //in meaning psi
    private boolean service = false;
    public static int count = 1;

    LiquidToxicRailroadCar(String shipper, double netWeight,String cargoType, String type){
        super(shipper,netWeight,cargoType);
        this.id = "ltrc" + count++; //ltrc stands for LiquidToxicRailroadCar
    }
    @Override
    public void addCargo(String cargoType, double weight) {
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
        this.service = true;
    }

    @Override
    public void takeCarOffService() {
        this.service = false;
    }
}
