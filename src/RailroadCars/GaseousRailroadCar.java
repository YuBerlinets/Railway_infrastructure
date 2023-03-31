package RailroadCars;

public class GaseousRailroadCar extends BasicFreightRailroadCar{
    private String gasType;
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

}
