package RailroadCars;

import java.util.Random;

public class LiquidToxicRailroadCar extends LiquidRailroadCar implements HeavyFreightRailroadCar, Service {
    private String cargoType;
    private double cargoWeight;
    private double pressure;
    private String certificateDate;
    private int loadingTime;
    public static int count = 1;


    public LiquidToxicRailroadCar(String shipper, double netWeight, String cargoType) {
        super(shipper, netWeight, cargoType);
        this.id = "ltrc" + count++; //ltrc stands for LiquidToxicRailroadCar
        this.pressure = 30;//in meaning psi
        this.service = false;
        this.certificateDate = generateCertificateDate();
        Thread pressureUpdating = new Thread(() -> {
            double percentChange = 0.01;
            while (attached && !this.service) {
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
    //checking usual time to get loaded
    public void usualLoadingTime(){
        int weightPerMin = 300;
        double time = Math.round(this.cargoWeight / weightPerMin);
        System.out.println(time + " minutes");
    }
    private String generateCertificateDate() {
        Random random = new Random();
        int day = random.nextInt(15) + 1;//all certificate are being given in first part the month
        int month = random.nextInt(12) + 1;
        int year = random.nextInt(2022 - 2005 + 1) + 2005;
        return "dd.mm.yy - " + day + "." + month + "." + year;
    }
    public String toString() {
        return super.toString() + " | Certificate date: " + getCertificateDate();
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
        this.service = true;
    }

    @Override
    public void takeCarOffService() {
        this.service = false;
    }

    @Override
    public String getCargoType() {
        return cargoType;
    }

    @Override
    public double getCargoWeight() {
        return cargoWeight;
    }

    @Override
    public double getPressure() {
        return pressure;
    }

    public String getCertificateDate() {
        return certificateDate;
    }
}
