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
        PassengerRailroadCar prc2 = new PassengerRailroadCar("PKP Intercity", 3800, 52);
        PassengerRailroadCar prc3 = new PassengerRailroadCar("Deutsche Bahn", 4100, 55);
        PostOfficeRailroadCar porc1 = new PostOfficeRailroadCar("UkrPoshta", 1000);
        PostOfficeRailroadCar porc2 = new PostOfficeRailroadCar("Poczta Polska", 2000);
        BaggageMailRailroadCar bmrc1 = new BaggageMailRailroadCar("DHL", 3200);
        BaggageMailRailroadCar bmrc2 = new BaggageMailRailroadCar("American Express", 3920);
        BasicFreightRailroadCar bfrc1 = new BasicFreightRailroadCar("Stark Industries", 2500);
        BasicFreightRailroadCar bfrc2 = new BasicFreightRailroadCar("G&G Company LLC", 4000);
        RestaurantRailroadCar rrc1 = new RestaurantRailroadCar("Mcdonald's", 3800);
        RestaurantRailroadCar rrc2 = new RestaurantRailroadCar("KFC", 3950);
        RestaurantRailroadCar rrc3 = new RestaurantRailroadCar("WOG", 3200);


        porc1.connectToElectricalGrid();
        rrc1.connectToElectricalGrid();
        rrc2.connectToElectricalGrid();
        //rrc3.connectToElectricalGrid();//throws an exception

        Trainset t1 = new Trainset(l1);
        t1.addCar(prc1);
        t1.addCar(prc2);
        t1.addCar(prc3);
        t1.addCar(porc1);
        t1.addCar(porc2);
        t1.addCar(bmrc1);
        t1.addCar(bmrc2);
        t1.addCar(bfrc1);
        t1.addCar(bfrc2);
        t1.addCar(rrc1);
        t1.addCar(rrc2);
        t1.addCar(rrc3); //throws an exception
        //t1.addCar(bmrc2);//throws an exception

        prc1.addPeople(38);
        prc2.addPeople(43);


        //System.out.println(t1);
        //System.out.println(t1.getNumRailroadCarsElectricityGrid());

        Trainset t2 = new Trainset(l2);
        t2.addCar(prc1);
        t2.addCar(prc2);
        t2.addCar(prc3);
        t2.addCar(porc1);
        t2.addCar(porc2);
        t2.addCar(bmrc1);
        t2.addCar(bmrc2);
        t2.addCar(bfrc1);
        t2.addCar(bfrc2);
        t2.addCar(rrc1);
        t2.addCar(rrc2);
        t2.addCar(rrc3);

        //System.out.println(t2);


        //System.out.println(t3);
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

        Scanner scannerTrainset = new Scanner(System.in);
        while(true){
            System.out.println("Enter the number of the train or 0 to exit the program:");
            int numTrainset = scannerTrainset.nextInt();

            switch (numTrainset){
                case 0:
                    System.exit(0);
                case 1:{
                    System.out.println(t1);
                    break;
                }
                case 2:{
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
}

