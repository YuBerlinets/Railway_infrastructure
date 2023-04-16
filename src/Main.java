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
        Locomotive l1 = new Locomotive("Victory", 12, 59500, 5,
                stationTMP, findStation("Lille,France", stations), findStation("Poltava,Ukraine", stations));
        Locomotive l2 = new Locomotive("Python", 12, 67000, 4,
                stationTMP, findStation("Krakow,Poland", stations), findStation("Braga,Portugal", stations));
        Locomotive l3 = new Locomotive("Test 1", 12, 58000, 6, stationTMP,
                findStation("Zurich,Switzerland", stations), findStation("Reykjavik,Iceland", stations));

        //generating routes for locomotives
        l1.generateRoute();
        l2.generateRoute();
        l3.generateRoute();

        Map<String, RailroadCar> hashCars = new HashMap<>();

        Trainset t1 = new Trainset(l1);
        new CarGenerator().generateCars(t1);
        Trainset t2 = new Trainset(l2);
        new CarGenerator().generateCars(t2);
        Trainset t3 = new Trainset(l3);
        new CarGenerator().generateCars(t3);

        //Threads
        List<Trainset> trainsets = new ArrayList<>();
        trainsets.add(t1);
        trainsets.add(t2);
        trainsets.add(t3);
        Map<String, Trainset> menuButton = new HashMap<>();
        for (int i = 0; i < trainsets.size(); i++) {
            String trainNum = "t" + (i + 1);
            menuButton.put(trainNum, trainsets.get(i));
        }
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

