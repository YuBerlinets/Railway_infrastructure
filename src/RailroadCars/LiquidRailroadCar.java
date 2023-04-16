package RailroadCars;

import java.util.Random;

public class LiquidRailroadCar extends RailroadCar implements BasicFreightRailroadCar,Service {
    private String liquidType;
    private String cargoType;
    private double cargoWeight;
    private double pressure;
    public static int count = 1;


    public LiquidRailroadCar(String shipper, double netWeight, String liquidType) {
        super(shipper, netWeight);
        this.liquidType = liquidType;
        this.id = "lrc" + count++; //lrc stands for LiquidRailroadCar
        this.pressure = 30;//in meaning psi
        this.attached = true;
        Thread pressureUpdating = new Thread(() -> {
            double percentChange = 0.01;
            while (attached) {
                Random random = new Random();
                double delta = this.pressure * percentChange;
                boolean pressureChanging = random.nextBoolean();
                if (pressureChanging)
                    this.pressure += delta;
                else
                    this.pressure -= delta;
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        pressureUpdating.start();
    }


    public String toString() {
        return super.toString() + " | Cargo type: " + getLiquidType() + " | Pressure: " + getPressure();
    }

    public void checkLeaking() {
        Random random = new Random();
        boolean leaking = random.nextBoolean();
        if(leaking)
            System.out.println("Car " + getId() + " might be leaking, please take this car to service");
        else
            System.out.println("Everything is great with " + getId() + " car");

    }

    public String getLiquidType() {
        return liquidType;
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

    @Override
    public void addCargo(String cargoType, double weight) {
        if (this.service) {
            System.out.println("The cargo can't be added, because car is in service");
        } else {
            this.cargoType += ("," + cargoType);
            this.cargoWeight += cargoWeight;
        }
    }

    @Override
    public void checkPressure() {
        if (this.service) {
            System.out.println("The pressure is 0, because car is in service");
        } else {
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
}
