import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Station warsaw = new Station("Warsaw, Poland");
        Station wroclaw = new Station("Wroclaw, Poland");
        Station poltava = new Station("Poltava, Ukraine");
        Station london = new Station("London, UK");
        Station paris = new Station("Paris, France");
        Locomotive l1 = new Locomotive("Victory", poltava, warsaw, wroclaw);
        Locomotive l2 = new Locomotive("Python", warsaw, london, paris);


        PassengerRailroadCar prc1 = new PassengerRailroadCar("Ukrzaliznitsya", 4000, 40);
        PassengerRailroadCar prc2 = new PassengerRailroadCar("PKP Intercity", 4000, 50);
        PostOfficeRailroadCar porc1 = new PostOfficeRailroadCar("Nova Post",3900);
        BaggageMailRailroadCar bmrc1 = new BaggageMailRailroadCar("DHL", 4000);
        RestaurantRailroadCar rrc1 = new RestaurantRailroadCar("WOG",3600);
        RefrigeratedRailroadCar rfrc1 = new RefrigeratedRailroadCar("LG", 3800);
        LiquidRailroadCar lrc1 = new LiquidRailroadCar("Stark Industries", 3400,"Water");
        GaseousRailroadCar grc1 = new GaseousRailroadCar("OKO", 3900,"Petrol");
        ExplosiveRailroadCar erc1 = new ExplosiveRailroadCar("American Express", 4100, "Bomb");
        ToxicRailroadCar trc1 = new ToxicRailroadCar("South uranium", 3400, "Uranium");


        prc1.connectToElectricalGrid();
        porc1.connectToElectricalGrid();
        rrc1.connectToElectricalGrid();
        rfrc1.isConnectedToElectricalGrid();

        prc1.addPeople(38);
        prc2.addPeople(43);

        Trainset t1 = new Trainset(l1);
        t1.addCar(prc1);
        t1.addCar(porc1);
        t1.addCar(bmrc1);
        t1.addCar(rrc1);
        t1.addCar(rfrc1);
        t1.addCar(lrc1);
        t1.addCar(grc1);
        t1.addCar(erc1);
        t1.addCar(trc1);


        Trainset t2 = new Trainset(l2);
        t2.addCar(prc1);
        t2.addCar(porc1);
        t2.addCar(bmrc1);
        t2.addCar(rrc1);
        t2.addCar(rfrc1);
        t2.addCar(lrc1);
        t2.addCar(grc1);
        t2.addCar(erc1);
        t2.addCar(trc1);


        //ArrayList<Station> stations = new ArrayList<>();
        //generateStations(stations);
        //System.out.println(stations);




        List<Trainset> trainsets = new ArrayList<>();

        trainsets.add(t1);
        trainsets.add(t2);

        Thread [] threads = new Thread[trainsets.size()];
        for (int i = 0; i < trainsets.size(); i++) {
            final int j = i;
            threads[i] = new Thread(() -> {
                try {
                    trainsets.get(j).getLocomotive().adjustSpeed();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start(); //assign speed to locomotive
        }

        Scanner menu = new Scanner(System.in);
        while(true){
            System.out.println("For providing information about trains' abbreviation type \"info\".\n" +
                    "For providing information about specific train type train's id.\n" +
                    "For exiting from the program type \"q\" or \"exit\".");
            String menuItems = menu.next();
            switch (menuItems.toLowerCase()){
                case ("info"):{
                    System.out.println(abbreviationInfo());
                    break;
                }
                case "q":
                case "exit": {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                }
                case "t1":{
                    System.out.println(t1);
                    break;
                }
                case "t2":{
                    System.out.println(t2);
                    break;
                }
                default:
                    System.out.println("Invalid input");
            }
        }
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

    public static ArrayList generateStations(ArrayList<Station> stations) {
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

    public static String abbreviationInfo(){
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

