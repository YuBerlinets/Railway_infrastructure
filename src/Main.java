import RailroadCars.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //generating stations
        List<Station> stations = Station.generateStations();

        //creating locomotives
        List<Locomotive> locomotives = creatingLocomotives(stations);

        //creating trainsets
        List<Trainset> trainsets = creatingTrainsets(locomotives);

        //storing information about cars and trainsets for menu
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
        Menu menuTest = new Menu(hashTrains, hashCars,stations);
        menuTest.display();


    }



    public static List<Trainset> creatingTrainsets(List<Locomotive> locomotives) {
        List<Trainset> trainsets = new ArrayList<>();
        CarGenerator carGenerator = new CarGenerator();
        for (int i = 0; i < locomotives.size(); i++) {
            Trainset trainset = new Trainset(locomotives.get(i));
            carGenerator.generateCars(trainset);
            trainsets.add(trainset);
        }
        return trainsets;
    }

    public static List<Locomotive> creatingLocomotives(List<Station> stations) {
        List<Locomotive> locomotives = new ArrayList<>();
        LocomotiveGenerator locomotiveGenerator = new LocomotiveGenerator(stations);
        for (int i = 0; i < 25; i++) {
            Locomotive locomotive = locomotiveGenerator.generateLocomotive();
            locomotive.generateRoute();
            //printingRoute(locomotive);//prints routes forward and back
            locomotives.add(locomotive);

        }
        return locomotives;
    }
    public static void printingRoute(Locomotive locomotive){
        System.out.println("Forward");
        System.out.println(locomotive.getRoute().getRouteStations());//test
        System.out.println("Back");
        System.out.println(locomotive.getReverseRoute().getRouteStations());
        System.out.println();
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
                    "\tPath: " + trainset.getLocomotive().getSourceStation() + " to " + trainset.getLocomotive().getDestinationStation());
    }
}

