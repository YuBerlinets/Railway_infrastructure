package RailroadCars;

import exception.TooManyPeople;

import javax.swing.*;

public class PassengerRailroadCar extends RailroadCar {
    private int numberSeats;
    private int people;
    private int bicycles;
    private int placeBicycle = 4;
    private int carClass;
    private boolean wifi;
    public static int count = 1;

    public PassengerRailroadCar(String shipper, double netWeight, int carClass, int numberSeats) {
        super(shipper, netWeight);
        this.id = "prc" + count++; //prc stands for PassengerRailroadCar
        this.numberSeats = numberSeats;
        this.carClass = carClass;
        this.wifi = (carClass == 1) ? true : false;
    }

    public void hasWifi() {
        System.out.println(this.wifi ? "Car " + getId() + " has the WIFI, because it's the 1st class car" :
                "Car " + getId() + " doesn't have the WIFI, because it's the 2nd class car");
    }

    public void addPeople(int people) {
        if ((this.people + people) < this.numberSeats) {
            this.people += people;
        } else {
            try {
                throw new TooManyPeople();
            } catch (TooManyPeople e) {
                System.out.println("WARNING: " + people + " people cannot be added to " + this.getId() + " car."
                        + "Because there are " + getPeople() + "already in it");
            }
        }
    }

    public void removePeople(int people) {
        if ((this.people - people >= 0)) {
            this.people -= people;
        } else {
            System.out.println("Can't remove such amount of people because now there are " + getPeople());
        }
    }

    public void addBicycle(int bicycles) {
        if (this.carClass == 2) {
            if ((this.bicycles + bicycles) < this.placeBicycle) {
                this.bicycles += bicycles;
            } else {
                System.out.println("WARNING: " + bicycles + " bicycles cannot be added to " + this.getId() + " car."
                        + " Because there are " + getBicycles() + "already in it and max number is 4");
            }
        }else{
            System.out.println("Bicycle aren't allowed in the 1st class car");
        }
    }

    public void removeBicycle(int bicycles) {
        if(this.carClass == 2) {
            if ((this.bicycles - bicycles >= 0)) {
                this.bicycles -= bicycles;
            } else {
                System.out.println("Can't remove such amount of bicycle because now there are " + getBicycles());
            }
        }else
            System.out.println("Bicycle aren't allowed in the 1st class car");
    }

    @Override
    public String toString() {
        return super.toString() + " | Seats: " + getNumberSeats() + " | Passengers: " + getPeople() + " | Bicycle places: " + getPlaceBicycle();
    }

    public int getBicycles() {
        return bicycles;
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
