import RailroadCars.*;

import javax.swing.plaf.TableHeaderUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Station> stations = Station.generateStations();
        Station stationTMP = new Station("Station TMP HOME");

        Locomotive l1 = new Locomotive("Victory", 10, 45000, 5,
                stationTMP, findStation("Madrid,Spain", stations), findStation("Bordeaux,France", stations));
        Locomotive l2 = new Locomotive("Python", 10, 39500, 4,
                stationTMP, findStation("Bordeaux,France", stations), findStation("Braga,Portugal", stations));

        Route route1 = generateRoute(l1);
        Route route2 = generateRoute(l2);

        System.out.println(route1);
        System.out.println("route 1 total km -> " + route1.getTotalDistance());

        System.out.println(route2);
        System.out.println("route 2 total km -> " + route2.getTotalDistance());

        PassengerRailroadCar prc1 = new PassengerRailroadCar("Ukrzaliznitsya", 4000, 1, 40);
        PassengerRailroadCar prc2 = new PassengerRailroadCar("PKP Intercity", 4000, 2, 50);
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

        prc1.takeCarToService();
        prc1.addPeople(38);
        prc2.addPeople(36);
        prc1.hasWifi();
        prc2.hasWifi();
        prc1.addBicycle(2);
        prc2.addBicycle(5);

        Trainset t1 = new Trainset(l1, route1);
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

        Trainset t2 = new Trainset(l2, route2);
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
        t2.addCar(erc1);//throws exception

        //Threads
        List<Trainset> trainsets = new ArrayList<>();
        trainsets.add(t1);
        trainsets.add(t2);

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
            threadsRoute[i]= new Thread(() -> {
                try {
                    trainsets.get(j).moveRoute();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            });
            threadsSpeed[i].start(); //assigns speed to locomotive
            threadsRoute[i].start();
        }

        //menu



        Scanner menu = new Scanner(System.in);
        Map<String, Trainset> menuButton = new HashMap<>();
        for (int i = 0; i < trainsets.size(); i++) {
            String trainNum = "t" + (i + 1);
            menuButton.put(trainNum, trainsets.get(i));
        }
        MenuTrain menuTrain = new MenuTrain(menuButton);
        Menu menuTest = new Menu(menuButton);
        menuTest.display();
        boolean programIsRunning = false;
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

    public static void carToService(RailroadCar railroadCar){
        railroadCar.getId();
    }


    public static Station findStation(String stationName, List<Station> stations) {

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
        Station destination = locomotive.getDestinationStation();
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
            System.out.println("This no connection between those stations" + locomotive.getSourceStation() +" "
            + locomotive.getDestinationStation());
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

