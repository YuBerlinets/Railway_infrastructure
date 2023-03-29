package RailroadCars;

import exception.TooManyPeople;

public class PassengerRailroadCar extends RailroadCar{
    private int numberSeats;
    private int people;
    public static int count = 1;

    public PassengerRailroadCar(String shipper, double netWeight, int numberSeats){
        super(shipper, netWeight);
        this.id = "prc" + count++; //prc stands for RailroadCars.PassengerRailroadCar
        this.numberSeats = numberSeats;
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

    public int getPeople() {
        return people;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    @Override
    public String toString(){
        return super.toString() + " | Seats: " + getNumberSeats() + " | Passengers: " + getPeople();
    }

}
