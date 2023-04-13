import RailroadCars.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Station> stations = Station.generateStations();
        Station stationA = new Station("Station A");
        Station stationB = new Station("Station B");
        Station stationC = new Station("Station C");
        Station stationD = new Station("Station D");
        Station stationE = new Station("Station E");
        Station stationF = new Station("Station F");
        Station stationG = new Station("Station G");
        Station stationK = new Station("Station K");
        Station stationM = new Station("Station M");
        Station stationL = new Station("Station L");
        Station stationN = new Station("Station N");

        Locomotive l1 = new Locomotive("Victory", 10, 45000, 5,
                stationD, findStation("Madrid,Spain",stations), findStation("Bordeaux,France",stations));
        Locomotive l2 = new Locomotive("Python", 10, 39500, 4, stationK, stationB, stationM);


        stationA.addIntersectsWith(stationB, 310);
        stationB.addIntersectsWith(stationC, 280);
        stationC.addIntersectsWith(stationD, 175);
        stationD.addIntersectsWith(stationE, 183);
        stationD.addIntersectsWith(stationF, 421);
        stationD.addIntersectsWith(stationM, 253);
        stationE.addIntersectsWith(stationK, 341);
        stationE.addIntersectsWith(stationG, 71);
        stationG.addIntersectsWith(stationM, 128);
        stationK.addIntersectsWith(stationM, 209);
        stationM.addIntersectsWith(stationL, 312);
        stationM.addIntersectsWith(stationN, 138);

        Route route1 = generateRoute(l1);
        //Route route2 = generateRoute(l2);

        System.out.println(route1);
        System.out.println("route 1 total km -> " + route1.getTotalDistance());

        //System.out.println(route2);
        //System.out.println("route 2 total km -> " + route2.getTotalDistance());

        PassengerRailroadCar prc1 = new PassengerRailroadCar("Ukrzaliznitsya", 4000, 1,40);
        PassengerRailroadCar prc2 = new PassengerRailroadCar("PKP Intercity", 4000, 2,50);
        PostOfficeRailroadCar porc1 = new PostOfficeRailroadCar("Nova Post", 3900);
        BaggageMailRailroadCar bmrc1 = new BaggageMailRailroadCar("DHL", 4000);
        RestaurantRailroadCar rrc1 = new RestaurantRailroadCar("WOG", 3600, 3);
        RefrigeratedRailroadCar rfrc1 = new RefrigeratedRailroadCar("LG", 3800, "Direct Expansion System", true);
        LiquidRailroadCar lrc1 = new LiquidRailroadCar("Stark Industries", 3400, "Water");
        GaseousRailroadCar grc1 = new GaseousRailroadCar("OKO", 3900, "Petrol", "Centrifugal Compressor");
        ExplosiveRailroadCar erc1 = new ExplosiveRailroadCar("American Express", 4100, "Bomb");
        ToxicRailroadCar trc1 = new ToxicRailroadCar("South uranium", 3400, "Uranium");

        prc1.connectToElectricalGrid();
        porc1.connectToElectricalGrid();
        rrc1.connectToElectricalGrid();
        System.out.println(rrc1.isConnectedToElectricalGrid());

        prc1.addPeople(38);
        prc2.addPeople(36);
        prc1.hasWifi();
        prc2.hasWifi();
        prc1.addBicycle(2);
        prc2.addBicycle(5);
        Trainset t1 = new Trainset(l1,route1);
        t1.addCar(prc1);
        t1.addCar(prc2);
        t1.addCar(porc1);
        t1.addCar(bmrc1);
        t1.addCar(rrc1);
        t1.addCar(rfrc1);
        t1.addCar(lrc1);
        t1.addCar(grc1);
        t1.addCar(erc1);
        t1.addCar(trc1);
        t1.addCar(erc1);//throws exception

//        Trainset t2 = new Trainset(l2,route2);
//        t2.addCar(prc1);
//        t2.addCar(prc2);
//        t2.addCar(porc1);
//        t2.addCar(bmrc1);
//        t2.addCar(rrc1);
//        t2.addCar(rfrc1);
//        t2.addCar(lrc1);
//        t2.addCar(grc1);
//        t2.addCar(erc1);
//        t2.addCar(trc1);
//        t2.addCar(erc1);//throws exception

        //ArrayList<Station> stations = new ArrayList<>();
        //generateStations(stations);
        //System.out.println(stations);

        List<Trainset> trainsets = new ArrayList<>();

        trainsets.add(t1);
        //trainsets.add(t2);

        Thread[] threads = new Thread[trainsets.size()];
        for (int i = 0; i < trainsets.size(); i++) {
            final int j = i;
            threads[i] = new Thread(() -> {
                try {
                    trainsets.get(j).getLocomotive().adjustSpeed();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start(); //assigns speed to locomotive
        }
        Thread trainMove1 = new Thread(() -> {
            try {
                trainsets.get(0).moveRoute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        trainMove1.start();
//        Thread trainMove2 = new Thread(() -> {
//            try {
//                trainsets.get(1).moveRoute(route2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        trainMove2.start();

        Scanner menu = new Scanner(System.in);
        Map<String, Trainset> menuButton = new HashMap<>();
        for (int i = 0; i < trainsets.size(); i++) {
            String trainNum = "t" + (i + 1);
            menuButton.put(trainNum, trainsets.get(i));
        }
        MenuTrain menuTrain = new MenuTrain(menuButton);
        boolean programIsRunning = true;
        while (programIsRunning) {
            System.out.println("For providing information about trains' abbreviation type \"info\".\n" +
                    "For providing information about specific train type train's id.\n" +
                    "For exiting from the program type \"exit\".");
            String menuInput = menu.next().toLowerCase();
            if (menuInput.equals("info"))
                System.out.println(abbreviationInfo());
            else if (menuInput.equals("exit")) {
                programIsRunning = false;
                System.out.println("Exiting the program...");
            } else if (menuButton.containsKey(menuInput))
                System.out.println(menuTrain.getInformation(menuInput));
            else
                System.out.println("Incorrect input");
        }
    }
    public static Station findStation(String stationName,List<Station> stations){

        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
    public static Route generateRoute(Locomotive locomotive) {
        Route route = new Route();
        Station source = locomotive.getSourceStation();
        Station destination =locomotive.getDestinationStation();
        Map<Station, Double> distances = new HashMap<>();
        Map<Station, Station> prev = new HashMap<>();
        PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        distances.put(source, 0.0);
        pq.offer(source);

        while (!pq.isEmpty()) {
            Station currentStation = pq.poll();
            if (currentStation == destination) {
                break;
            }
            double currentDistance = distances.get(currentStation);
            for (Station nextStation : currentStation.getIntersectsWith().keySet()) {
                double distance = currentDistance + currentStation.getDistanceTo(nextStation);
                if (!distances.containsKey(nextStation) || distance < distances.get(nextStation)) {
                    distances.put(nextStation, distance);
                    prev.put(nextStation, currentStation);
                    pq.offer(nextStation);
                }
            }
        }

        if (!prev.containsKey(destination)) {
            return null;
        }

        Station currentStation = destination;
        double totalDistance = 0.0;
        while (currentStation != source) {
            Station previousStation = prev.get(currentStation);
            double distance = previousStation.getDistanceTo(currentStation);
            totalDistance += distance;
            route.addStation(previousStation);
            currentStation = previousStation;
        }
        route.reverse();
        route.addStation(destination);
        route.calculatedDistance(totalDistance);
        return route;
    }


    public static void generateCars(Trainset trainset) {
        int numOfCars = (int) (Math.random() * 3 + 8);
        double netWeight = Math.random() * 1000 + 3500;
        for (int i = 0; i < numOfCars; i++) {
            trainset.addCar(new PostOfficeRailroadCar(getShipper(), netWeight));

        }
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

    public static List<Station> generateStations(ArrayList<Station> stations) {
        String result = null;
        Random random = new Random();
        String path = "TechFiles\\Stations.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> lines = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                Station station = new Station(line);
                stations.add(station);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
        return stations;
    }

    public static String abbreviationInfo() {
        return "Here is the explanation of the trains' abbreviation\n" +
                "1.prc - Passenger Railroad Car\n" +
                "2.porc - Post Office Railroad Car\n" +
                "3.bmrc - Baggage and Mail Railroad Car\n" +
                "4.rrc - Restaurant Railroad Car\n" +
                "5.bfrc - Basic Freight Railroad Car\n" +
                "6.hrfc - Heavy Freight Railroad Car\n" +
                "7.rfrc - Refrigerated Railroad Car\n" +
                "8.lrc - Liquid Railroad Car\n" +
                "9.grc - Gaseous Railroad Car\n" +
                "10.erc - Explosive Railroad Car\n" +
                "11.trc - Toxic Railroad Car\n" +
                "12.ltrc - Liquid Toxic Railroad Car\n";
    }
}

