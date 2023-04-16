import RailroadCars.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        //generate stations with intersections from file Station.txt
        List<Station> stations = Station.generateStations();
        Station stationTMP = new Station("Station TMP HOME");
        //creating locomotives
        Locomotive l1 = new Locomotive("Victory", 10, 45000, 5,
                stationTMP, findStation("Lille,France", stations), findStation("Poltava,Ukraine", stations));
        Locomotive l2 = new Locomotive("Python", 10, 39500, 4,
                stationTMP, findStation("Krakow,Poland", stations), findStation("Braga,Portugal", stations));
        Locomotive l3 = new Locomotive("Test 1", 10, 43000, 6, stationTMP,
                findStation("Zurich,Switzerland", stations), findStation("Reykjavik,Iceland", stations));

        l1.generateRoute();
        l2.generateRoute();
        l3.generateRoute();
        System.out.println("HERE");
        System.out.println(l1.getRoute());
        System.out.println();
        System.out.println(l1.getReverseRoute());
        System.out.println();
        System.out.println(l2.getRoute());
        System.out.println(l3.getRoute());


        PassengerRailroadCar prc1 = new PassengerRailroadCar("Ukrzaliznitsya", 4000, 1, 40);
        PassengerRailroadCar prc2 = new PassengerRailroadCar("PKP Intercity", 4000, 2, 50);
        PostOfficeRailroadCar porc1 = new PostOfficeRailroadCar("Nova Post", 3900,80, true);
        BaggageMailRailroadCar bmrc1 = new BaggageMailRailroadCar("DHL", 4000,10,false);
        RestaurantRailroadCar rrc1 = new RestaurantRailroadCar("WOG", 3600, 3,12);
        RefrigeratedRailroadCar rfrc1 = new RefrigeratedRailroadCar("LG", 3800, "Direct Expansion System", true);
        LiquidRailroadCar lrc1 = new LiquidRailroadCar("Stark Industries", 3400, "Water");
        GaseousRailroadCar grc1 = new GaseousRailroadCar("OKO", 3900, "Petrol", "Centrifugal Compressor");
        ExplosiveRailroadCar erc1 = new ExplosiveRailroadCar("American Express", 4100, "Bomb");
        ToxicRailroadCar trc1 = new ToxicRailroadCar("South uranium", 3400, "Uranium");

        Map<String, RailroadCar> hashCars = new HashMap<>();
        hashCars.put("prc1", prc1);
        hashCars.put("prc2", prc2);

        prc1.connectToElectricalGrid();
        porc1.connectToElectricalGrid();
        rrc1.connectToElectricalGrid();

        System.out.println("\nTest lines start: ");
        prc1.takeCarToService();
        prc1.addPeople(38);
        prc2.addPeople(36);
        prc1.hasWifi();
        prc2.hasWifi();
        prc1.addBicycle(2);
        prc2.addBicycle(5);
        System.out.println("Test lines end\n");

        Trainset t1 = new Trainset(l1);
        t1.addCar(prc1);
        t1.addCar(prc2);
        t1.addCar(porc1);
        t1.addCar(bmrc1);
        t1.addCar(rrc1);
        t1.addCar(rfrc1);
        t1.addCar(lrc1);
        t1.addCar(grc1);
//        t1.addCar(erc1);
//        t1.addCar(trc1);
        t1.addCar(erc1);//throws exception
        System.out.println("REVERSE ROUTE");
        System.out.println(t1.getLocomotive().getReverseRoute().getRoute());

        Trainset t2 = new Trainset(l2);
        t2.addCar(prc1);
        t2.addCar(prc2);
        t2.addCar(porc1);
        t2.addCar(bmrc1);
        t2.addCar(rrc1);
        t2.addCar(rfrc1);
        t2.addCar(lrc1);
        t2.addCar(grc1);
        t2.addCar(erc1);
        t2.addCar(trc1);
        //t2.addCar(erc1);//throws exception

        Trainset t3 = new Trainset(l3);
        //Threads
        List<Trainset> trainsets = new ArrayList<>();
        trainsets.add(t1);
        trainsets.add(t2);
        trainsets.add(t3);
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

        startMessage(trainsets);
        saveData(trainsets);

        //menu
        Map<String, Trainset> menuButton = new HashMap<>();
        for (int i = 0; i < trainsets.size(); i++) {
            String trainNum = "t" + (i + 1);
            menuButton.put(trainNum, trainsets.get(i));
        }
        Menu menuTest = new Menu(menuButton, hashCars);
        menuTest.display();


    }

    public static void saveData(List<Trainset> trainsets) {
        String path = "TechFiles\\AppState.txt";
        Collections.sort(trainsets);
        boolean programIsRunning = true;
        Thread writing = new Thread(()->{
            try {
                FileWriter writer = new FileWriter(path);
                while (programIsRunning) {
                    for (Trainset item : trainsets) {
                        Collections.sort(item.getRailroadCars());
                        writer.write(item.toString());
                        writer.write("\n");
                    }
                    writer.flush();
                    Thread.sleep(5000);
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file");
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted status
                System.out.println("Thread was interrupted");
            }
        });
        writing.start();//starting
    }

    public static void startMessage(List<Trainset> trainsets){
        System.out.println("Currently on the routes: ");
        int count = 1;
        for(Trainset trainset : trainsets)
            System.out.println(count++ + ". Trainset ID: " + trainset.getId() +
                    " Path: " + trainset.getLocomotive().getSourceStation() + " to " + trainset.getLocomotive().getDestinationStation());
    }

    public static Station findStation(String stationName, List<Station> stations) {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }

    public static String getShipper() {
        String result = null;
        Random random = new Random();
        String path = "TechFiles\\CarShipper.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> lines = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            int randomLine = random.nextInt(lines.size());
            result = lines.get(randomLine);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }

        return result;
    }

}

