package RailroadCars;

public class RefrigeratedRailroadCar extends RailroadCar implements BasicFreightRailroadCar,ElectricalGrid,Service{
    private String coolingMethod;
    //examples of cooling methods:
    //Direct Expansion System
    //Cryogenic Cooling
    //Eutectic Plate System
    private boolean humidityControl;
    private int temperature;
    private String cargoType;
    private double cargoWeight;
    private double pressure;
    public static int count = 1;
    public RefrigeratedRailroadCar(String shipper, double netWeight,String coolingMethod, boolean humidityControl){
        super(shipper, netWeight);
        this.id = "rfrc" + count++; //rfrc stands for RefrigeratedRailroadCar
        this.coolingMethod = coolingMethod;
        this.humidityControl = humidityControl;
        this.temperature = 3;
    }

    public String toString(){
        return super.toString() + " | Cooling Method: " + getCoolingMethod() + "" +
                " | Humidity Control: " + (isHumidityControl() ? "Yes" : "No")+
                " | Pressure: " + getPressure();
    }
    public void decreaseTemperature(int temp){
        if((this.temperature - temp) > -5){
            this.temperature -= temp;
        }else
            System.out.println("It will ruin the product");
    }
    public void increaseTemperature(int temp){
        if((this.temperature + temp) < 10){
            this.temperature += temp;
        }else
            System.out.println("It will ruin the product");
    }

    public int getTemperature() {
        return temperature;
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

    public String getCoolingMethod() {
        return coolingMethod;
    }

    public boolean isHumidityControl() {
        return humidityControl;
    }

    @Override
    public void connectToElectricalGrid() {
        super.connectedToElectricalGrid = true;
    }

    @Override
    public void disconnectFromElectricalGrid() {
        super.connectedToElectricalGrid = false;
    }

    @Override
    public void takeCarToService() {
        super.service = true;
    }

    @Override
    public void takeCarOffService() {
        super.service = false;
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
