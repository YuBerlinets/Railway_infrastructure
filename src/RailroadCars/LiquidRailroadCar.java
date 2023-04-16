package RailroadCars;

import java.util.Random;

public class LiquidRailroadCar extends RailroadCar implements BasicFreightRailroadCar {
    private String liquidType;
    private String cargoType;
    private String cargoWeight;
    private double pressure;
    public static int count = 1;
    private boolean running;

    // add unique fields

    public LiquidRailroadCar(String shipper, double netWeight, String liquidType) {
        super(shipper, netWeight);
        this.liquidType = liquidType;
        this.id = "lrc" + count++; //lrc stands for RailroadCars.LiquidRailroadCar
        this.pressure = 30;//in meaning psi
        this.running = true;
        Thread pressureUpdating = new Thread(() -> {
            double percentChange = 0.01;
            while (running) {
                Random random = new Random();
                double delta = this.pressure * percentChange;
                boolean pressureChanging = random.nextBoolean();
                if (pressureChanging)
                    this.pressure += delta;
                else
                    this.pressure -= delta;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        pressureUpdating.start();
    }

    public String getLiquidType() {
        return liquidType;
    }

    public String toString() {
        return super.toString() + " | Liquid type: " + getLiquidType() + " Pressure: " + getPressure();
    }

    public String getCargoType() {
        return cargoType;
    }

    public String getCargoWeight() {
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

}
