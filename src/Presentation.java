import RailroadCars.*;
import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.List;
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
        rrc1.bookTable();
        System.out.println("Getting our reservations:");
        System.out.println(rrc1.getReservations());
        rrc1.releaseTable();
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
        trc1.checkMaterialType();//password is "check"
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

        List<Station> stations = Station.generateStations();

        LocomotiveGenerator locomotiveGenerator = new LocomotiveGenerator(stations);
        Locomotive presentationLocomotive1 = new Locomotive("Showing",12,90000,6,
                locomotiveGenerator.findStation("Poltava,Ukraine"),locomotiveGenerator.findStation("Warsaw,Poland"),
                locomotiveGenerator.findStation("Cologne,Germany"));//generated by our own thoughts
        Locomotive presentationLocomotive2 = locomotiveGenerator.generateLocomotive();//randomly generated locomotive
        //generate route for locomotive
        presentationLocomotive1.generateRoute();
        presentationLocomotive2.generateRoute();
        //printing them out
        printRoute(presentationLocomotive1);
        printRoute(presentationLocomotive2);

        Trainset presentationTrainset1 = new Trainset(presentationLocomotive1);
        presentationTrainset1.addCar(prc1);
        presentationTrainset1.addCar(porc1);
        presentationTrainset1.addCar(bmrc1);
        presentationTrainset1.addCar(rrc1);
        presentationTrainset1.addCar(rfrc1);
        presentationTrainset1.addCar(lrc1);
        presentationTrainset1.addCar(grc1);
        presentationTrainset1.addCar(erc1);
        presentationTrainset1.addCar(ltrc1);//assigning cars by our own
        System.out.println("\nPresentation trainset:");
        System.out.println(presentationTrainset1);

        Trainset presentationTrainset2 = new Trainset(presentationLocomotive2);
        carGenerator.generateCars(presentationTrainset2);//generating random cars to our trainset


        List<Trainset> trainsets = new ArrayList<>();
        trainsets.add(presentationTrainset1);
        trainsets.add(presentationTrainset2);
        Thread[] threadsSpeed = new Thread[trainsets.size()];
        Thread[] threadsRoute = new Thread[trainsets.size()];
        for (int i = 0; i < trainsets.size(); i++) {
            final int j = i;
            threadsSpeed[i] = new Thread(() -> {
                try {
                    trainsets.get(j).getLocomotive().adjustSpeed();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            });
            threadsRoute[i] = new Thread(() -> {
                try {
                    trainsets.get(j).moveRoute();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            });
            threadsSpeed[i].start(); //assigns speed to locomotive
            threadsRoute[i].start(); //assigns routes to trainsets
        }
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
                    threadsSpeed[0].interrupt();
                    threadsSpeed[1].interrupt();
                    threadsRoute[0].interrupt();
                    threadsRoute[1].interrupt();
                    System.out.println("Exiting..");
                    break;
                }
                case 1:{
                    System.out.println("Speed of " + presentationTrainset1.getId() + " is "+
                    presentationTrainset1.getLocomotive().getSpeed());
                    System.out.println("Speed of " + presentationTrainset2.getId() + " is "+
                            presentationTrainset1.getLocomotive().getSpeed());
                    break;
                }
                case 2:
                    System.out.println("Full information about trainsets");
                    System.out.println(presentationTrainset1.getId());
                    System.out.println(presentationTrainset1);
                    System.out.println(presentationTrainset2.getId());
                    System.out.println(presentationTrainset2);
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        }

    }
    private static void printRoute(Locomotive locomotive){
        System.out.println("Generated route");
        System.out.println(locomotive.getRoute().getRouteStations());
        System.out.println(locomotive.getRoute().getTotalDistance());
        System.out.println("Generated backward route");
        System.out.println(locomotive.getReverseRoute().getRouteStations());
        System.out.println(locomotive.getReverseRoute().getTotalDistance());
    }
}
