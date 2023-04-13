package RailroadCars;

public class PostOfficeRailroadCar extends RailroadCar implements ElectricalGrid,Service{
    public static int count = 1;

    public PostOfficeRailroadCar(String shipper, double netWeight){
        super(shipper, netWeight);
        this.id = "porc" + count++; //porc post office railroad car
    }

    public String toString(){
        return super.toString();
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
