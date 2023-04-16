package RailroadCars;

import java.util.Random;

public class GaseousRailroadCar extends RailroadCar implements BasicFreightRailroadCar, Service {
    private String gasType;
    private String cargoType;
    private String cargoWeight;
    private double pressure;
    private String typeGasCompressor;
    //examples of Gas Compressor types:
    //Positive Displacement Compressor
    //Centrifugal Compressor
    //Axial Compressor
    private boolean carSystemWorking;
    public static int count = 1;


    public GaseousRailroadCar(String shipper, double netWeight, String gasType, String typeGasCompressor) {
        super(shipper, netWeight);
        this.id = "grc" + count++; //grc stands for GaseousRailroadCar
        this.gasType = gasType;
        this.typeGasCompressor = typeGasCompressor;
        this.carSystemWorking = true;
        this.pressure = 30;//in meaning psi
        Thread pressureUpdating = new Thread(() -> {
            double percentChange = 0.01;
            while (attached && this.carSystemWorking) {
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

    public void emergencyShutDown() {
        this.carSystemWorking = false;
    }

    public void changeGas() {
        Random random = new Random();
        String[] gases = {"Petrol", "LNG", "Diesel"};
        int cuisineIndex = random.nextInt(gases.length);
        if (!gases[cuisineIndex].equals(this.gasType))
            this.gasType = gases[cuisineIndex];
        else
            changeGas();
    }

    public String toString() {
        return super.toString() + " | Gas type: " + getGasType() + " | Gas compressor type: " + getTypeGasCompressor()
                + " | Pressure: " + getPressure();
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

    public String getTypeGasCompressor() {
        return typeGasCompressor;
    }

    public String getGasType() {
        return gasType;
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
        this.carSystemWorking = false;
        this.pressure = 0;
    }

    @Override
    public void takeCarOffService() {
        super.service = false;
        this.carSystemWorking = true;
        this.pressure = 30;
    }
}
