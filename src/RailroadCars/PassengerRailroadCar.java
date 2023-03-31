package RailroadCars;

import exception.TooManyPeople;

public class PassengerRailroadCar extends RailroadCar{
    private int numberSeats;
    private int people;
    private int placeBicycle;
    public static int count = 1;

    public PassengerRailroadCar(String shipper, double netWeight, int numberSeats, int placeBicycle){
        super(shipper, netWeight);
        this.id = "prc" + count++; //prc stands for RailroadCars.PassengerRailroadCar
        this.numberSeats = numberSeats;
        this.placeBicycle = placeBicycle;
    }
    public void addPeople(int people){
        if((this.people + people) < this.numberSeats){
            this.people += people;
        }else{
            try {
                throw new TooManyPeople();
            } catch (TooManyPeople e) {
                System.out.println("WARNING: " + people + " people cannot be added to " + this.getId() + " car");
            }
        }
    }
    @Override
    public String toString(){
        return super.toString() + " | Seats: " + getNumberSeats() + " | Passengers: " + getPeople() + " | Bicycle places: " + getPlaceBicycle();
    }

    public int getPlaceBicycle() {
        return placeBicycle;
    }

    public int getPeople() {
        return people;
    }

    public int getNumberSeats() {
        return numberSeats;
    }


}
