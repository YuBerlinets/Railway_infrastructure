package RailroadCars;

public class GaseousRailroadCar extends RailroadCar implements BasicFreightRailroadCar{
    private String gasType;
    private String cargoType;
    private String cargoWeight;
    private double pressure;
    private String typeGasCompressor;
    //examples of Gas Compressor types:
    //Positive Displacement Compressor
    //Centrifugal Compressor
    //Axial Compressor

    public static int count = 1;


    public GaseousRailroadCar(String shipper, double netWeight, String gasType,String typeGasCompressor){
        super(shipper, netWeight);
        this.id = "grc" + count++; //grc stands for RailroadCars.GaseousRailroadCar
        this.gasType = gasType;
        this.typeGasCompressor = typeGasCompressor;
    }

    public String toString(){
        return super.toString() + " | Gas type: " + getGasType() + " | Gas compressor type: " + getTypeGasCompressor();
    }

    public String getTypeGasCompressor() {
        return typeGasCompressor;
    }

    public String getGasType() {
        return gasType;
    }

    @Override
    public void addCargo(String cargoType, double weight) {
        if(this.service){
            System.out.println("The cargo can't be added, because car is in service");
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
}
