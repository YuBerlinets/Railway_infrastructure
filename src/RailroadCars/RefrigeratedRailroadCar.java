package RailroadCars;

public class RefrigeratedRailroadCar extends BasicFreightRailroadCar implements ElectricalGrid,Service{
    private String coolingMethod;
    //examples of cooling methods:
    //Direct Expansion System
    //Cryogenic Cooling
    //Eutectic Plate System
    private boolean humidityControl;
    public static int count = 1;
    public RefrigeratedRailroadCar(String shipper, double netWeight,String coolingMethod, boolean humidityControl){
        super(shipper, netWeight);
        this.id = "rfrc" + count++; //rfrc stands for RailroadCars.RefrigeratedRailroadCar
        this.coolingMethod = coolingMethod;
        this.humidityControl = humidityControl;
    }

    public String toString(){
        return super.toString() + " | Cooling Method: " + getCoolingMethod() + " | Humidity Control: " + (isHumidityControl() ? "Yes" : "No");
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
}
