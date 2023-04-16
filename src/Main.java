import RailroadCars.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        //creating locomotives
        LocomotiveGenerator locomotiveGenerator = new LocomotiveGenerator();
        Locomotive l1 = locomotiveGenerator.generateLocomotive();
        Locomotive l2 = locomotiveGenerator.generateLocomotive();
        Locomotive l3 = locomotiveGenerator.generateLocomotive();
        Locomotive l4 = locomotiveGenerator.generateLocomotive();

        //generating routes for locomotives
        l1.generateRoute();
        l2.generateRoute();
        l3.generateRoute();
        l4.generateRoute();

        //creating trainsets
        Trainset t1 = new Trainset(l1);
        Trainset t2 = new Trainset(l2);
        Trainset t3 = new Trainset(l3);
        Trainset t4 = new Trainset(l4);

        //generating cars for each trainset
        CarGenerator carGenerator = new CarGenerator();
        carGenerator.generateCars(t1);
        carGenerator.generateCars(t2);
        carGenerator.generateCars(t3);
        carGenerator.generateCars(t4);

        //storing information about cars and trainsets for menu
        List<Trainset> trainsets = new ArrayList<>();
        trainsets.add(t1);
        trainsets.add(t2);
        trainsets.add(t3);
        trainsets.add(t4);
        Map<String, Trainset> hashTrains = new HashMap<>();
        for (int i = 0; i < trainsets.size(); i++) {
            String trainNum = "t" + (i + 1);
            hashTrains.put(trainNum, trainsets.get(i));
        }
        Map<String, RailroadCar> hashCars = saveCars(trainsets);

        //Threads
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

        //Message about current trainsets on the route
        startMessage(trainsets);
        //saving data to AppState.txt
        saveData(trainsets);

        //menu
        Menu menuTest = new Menu(hashTrains, hashCars);
        menuTest.display();


    }

    public static Map<String, RailroadCar> saveCars(List<Trainset> trainsets) {
        Map<String, RailroadCar> hashCars = new HashMap<>();
        for (Trainset item : trainsets) {
            for (RailroadCar railroadCar : item.getRailroadCars()) {
                hashCars.put(railroadCar.getId(), railroadCar);
            }
        }
        return hashCars;
    }

    public static void saveData(List<Trainset> trainsets) {
        String path = "TechFiles\\AppState.txt";
        Collections.sort(trainsets);
        boolean programIsRunning = true;
        Thread writing = new Thread(() -> {
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

    public static void startMessage(List<Trainset> trainsets) {
        System.out.println("Currently on the routes: ");
        int count = 1;
        for (Trainset trainset : trainsets)
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

