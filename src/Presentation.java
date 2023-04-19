import RailroadCars.*;
import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class Presentation {
    public static void main(String[] args) {
        CarGenerator carGenerator = new CarGenerator();

        PassengerRailroadCar prc1 = new PassengerRailroadCar(carGenerator.getShipper(), 3800,1,50);
        prc1.addCargo(2000);
        prc1.connectToElectricalGrid();
        prc1.addBicycle(5);
        prc1.hasWifi();
        prc1.addPeople(45);
        prc1.removePeople(10);
        System.out.println(prc1);

        System.out.println("=-=-=-=-=");
        PostOfficeRailroadCar porc1 = new PostOfficeRailroadCar(carGenerator.getShipper(), 3900,54,false);
        porc1.addCargo(2000);
        porc1.connectToElectricalGrid();
        porc1.addBoxes(60);
        porc1.addBoxes(23);
        System.out.println(porc1);

        System.out.println("=-=-=-=-=");
        BaggageMailRailroadCar bmrc1 = new BaggageMailRailroadCar(carGenerator.getShipper() , 3745,25,false);
        bmrc1.isStoringPets();
        bmrc1.addCargo(1783);
        bmrc1.loadBaggage(15);
        bmrc1.loadPets(12);
        bmrc1.unloadBaggage(10);
        System.out.println(bmrc1);

        System.out.println("=-=-=-=-=");
        RestaurantRailroadCar rrc1 = new RestaurantRailroadCar(carGenerator.getShipper(), 3200, 4,10);
        rrc1.connectToElectricalGrid();
        rrc1.addCargo(1000);
        System.out.println("Cuisine before changing:");
        System.out.println(rrc1.getCuisine());
        rrc1.changeCuisine();
        System.out.println("Cuisine after changing:");
        System.out.println(rrc1.getCuisine());
        //rrc1.bookTable();
        System.out.println("Getting our reservations:");
        System.out.println(rrc1.getReservations());
        //rrc1.releaseTable();
        System.out.println("Getting our reservations after releasing:");
        System.out.println(rrc1.getReservations());
        System.out.println(rrc1);

        System.out.println("=-=-=-=-=");
        RefrigeratedRailroadCar rfrc1 = new RefrigeratedRailroadCar(carGenerator.getShipper(),3478,
                "Cryogenic Cooling",true);
        rfrc1.connectToElectricalGrid();
        rfrc1.addCargo(2000);
        System.out.println("Checking pressure, which changes every 15 sec");
        rfrc1.checkPressure();
        rfrc1.increaseTemperature(10);
        System.out.println(rfrc1);

        System.out.println("=-=-=-=-=");
        LiquidRailroadCar lrc1 = new LiquidRailroadCar(carGenerator.getShipper(),2999,"Water");
        lrc1.addCargo(2589);
        System.out.println("Checking pressure, which changes every 15 sec:");
        lrc1.checkPressure();
        System.out.println("Checking leaking:");
        lrc1.checkLeaking();
        System.out.println(lrc1);

        System.out.println("=-=-=-=-=");
        GaseousRailroadCar grc1 = new GaseousRailroadCar(carGenerator.getShipper(), 3432,"LNG","Axial Compressor");
        grc1.addCargo(2397);
        System.out.println("Gas before changing:");
        System.out.println(grc1.getGasType());
        grc1.changeGas();
        System.out.println("Gas after changing:");
        System.out.println(grc1.getGasType());
        System.out.println("Checking pressure:");
        grc1.checkPressure();
        System.out.println("Pressure:");
        System.out.println(grc1.getPressure());
        System.out.println("Emergency shutting down");
        grc1.emergencyShutDown();
        System.out.println(grc1);

        System.out.println("=-=-=-=-=");
        ExplosiveRailroadCar erc1 = new ExplosiveRailroadCar(carGenerator.getShipper(), 3123,3,'B',"TNT");
        erc1.addCargo("Bomb",2300);
        erc1.addSecurity(2);//addining ppl to security
        erc1.remoteMonitoring();
        erc1.checkPressure();
        erc1.removeSecurity(2);
        System.out.println(erc1);

        System.out.println("=-=-=-=-=");
        ToxicRailroadCar trc1 = new ToxicRailroadCar(carGenerator.getShipper(), 3442,"Wastes");
        //trc1.checkMaterialType();//password is "check"
        trc1.takeCarToService();
        trc1.checkPressure();
        trc1.addMaterial("Smth new");
        System.out.println(trc1);

        System.out.println("=-=-=-=-=");
        LiquidToxicRailroadCar ltrc1 = new LiquidToxicRailroadCar(carGenerator.getShipper(), 3455,"Chemicals");
        System.out.println(ltrc1.getCertificateDate());
        ltrc1.addCargo(1893);
        ltrc1.addCargo("Acids",2000);
        ltrc1.usualLoadingTime();
        ltrc1.checkPressure();
        System.out.println(ltrc1);


        LocomotiveGenerator locomotiveGenerator = new LocomotiveGenerator();
        Locomotive presentationLocomotive = new Locomotive("Showing",12,80000,6,
                locomotiveGenerator.findStation("Poltava,Ukraine"),locomotiveGenerator.findStation("Warsaw,Poland"),
                locomotiveGenerator.findStation("Cologne,Germany"));

        //generate route for locomotive
        presentationLocomotive.generateRoute();

        System.out.println("Generated route");
        System.out.println(presentationLocomotive.getRoute().getRouteStations());
        System.out.println(presentationLocomotive.getRoute().getTotalDistance());
        System.out.println("Generated backward route");
        System.out.println(presentationLocomotive.getReverseRoute().getRouteStations());
        System.out.println(presentationLocomotive.getReverseRoute().getTotalDistance());

        Trainset presentationTrainset = new Trainset(presentationLocomotive);
        presentationTrainset.addCar(prc1);
        presentationTrainset.addCar(porc1);
        presentationTrainset.addCar(bmrc1);
        presentationTrainset.addCar(rrc1);
        presentationTrainset.addCar(rfrc1);
        presentationTrainset.addCar(lrc1);
        presentationTrainset.addCar(grc1);
        presentationTrainset.addCar(erc1);
        presentationTrainset.addCar(ltrc1);
        System.out.println("\nPresentation trainset:");
        System.out.println(presentationTrainset);

        Thread changingSpeed = new Thread(()->{
            try {
                presentationTrainset.getLocomotive().adjustSpeed();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread moving = new Thread (()-> {
            try {
                presentationTrainset.moveRoute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        changingSpeed.start();
        moving.start();
        Scanner sc = new Scanner(System.in);
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Enter 0 - to exit the program\n" +
                    "Enter 1 to check the speed of Locomotive\n" +
                    "Enter 2 to check full info about Trainset\n");
            int menuCase = sc.nextInt();
            switch (menuCase){
                case 0: {
                    isWorking = false;
                    changingSpeed.interrupt();
                    System.out.println("Exiting..");
                }
                case 1:{
                    System.out.println("Speed of " + presentationTrainset.getId());
                    presentationTrainset.getLocomotive().getSpeed();
                }
                case 2:
                    System.out.println("Full information about trainset " + presentationTrainset.getId());
                    System.out.println(presentationTrainset);
            }
        }

    }
}
