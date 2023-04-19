import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocomotiveGenerator {
    List<Station> stations;
    LocomotiveGenerator(List<Station> stations){
        this.stations = stations;
    }
    public Locomotive generateLocomotive(String home, String source, String destination){
        Random random = new Random();
        int maxCars = random.nextInt(2)+9;
        double maxWeight = random.nextDouble(110000-82000)+82000;
        int maxNumCarsElecticGrid = random.nextInt(3)+5;
        Station homeStation = getStation(home,stations);
        Station sourceStation = getStation(source,stations);
        Station destinationStation = getStation(destination,stations);
        Locomotive locomotive = new Locomotive(getName(),maxCars,maxWeight,maxNumCarsElecticGrid,homeStation,
                sourceStation, destinationStation);
        locomotive.generateRoute();
        System.out.println(locomotive.getRoute().getRouteStations());
        return locomotive;
    }
    public Locomotive generateLocomotive(){
        Random random = new Random();
        int maxCars = random.nextInt(2)+9;
        double maxWeight = random.nextDouble(110000-82000)+82000;
        int maxNumCarsElecticGrid = random.nextInt(3)+5;
        Station homeStation = getStation(getStationName(),stations);
        Station sourceStation = getStation(getStationName(),stations);
        Station destitationStation = getStation(getStationName(),stations);
        Locomotive locomotive = new Locomotive(getName(),maxCars,maxWeight,maxNumCarsElecticGrid,homeStation,
                sourceStation, destitationStation);
        return locomotive;
    }
    private String getName(){
        String [] names = {"Iron Horse", "Loco", "Bullet Train",
                "Express", "Freight Train", "Mighty Hauler", "Iron Beast", "Thunderbolt",
                "Powerhouse", "Highballer", "Black Diamond", "Silver Streak", "Green Machine"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }
    private static Station getStation(String stationName, List<Station> stations) {
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
    public Station findStation(String name){
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }
    private String getStationName(){
        String result = null;
        Random random = new Random();
        String path = "TechFiles\\Stations.txt";
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
