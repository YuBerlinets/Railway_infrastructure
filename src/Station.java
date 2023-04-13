import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Station {
    private String id = "s"; //s stands for station
    private String name;
    private Map<Station,Double> intersectsWith;
    private Map<Station, Double> kmToNextStation;
    private boolean visited;
    private boolean available = true;
    public static int count = 1;


    Station(String name){
        this.name = name;
        this.id = id + count++;
        this.intersectsWith = new HashMap<>();
        this.visited = false;
    }
    @Override
    public String toString(){
        return " ID: " + getId() + " Name: " + getName();

    }

    public void generateRandomIntersections(List<Station> allStations, int numIntersections) {
        Random rand = new Random();

        // randomly select other stations to connect with
        for (int i = 0; i < numIntersections; i++) {
            Station randomStation = allStations.get(rand.nextInt(allStations.size()));
            double distance = rand.nextDouble() * 500 + 100; // randomly generate distance between 100 and 600
            this.addIntersectsWith(randomStation, distance);
            //System.out.println(this.intersectsWith.get(this));
        }
    }

    public static List<Station> generateStations() {
        List<Station> stations = new ArrayList<>();
        String path = "TechFiles\\Stations.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = null;
            int index = 0;
            while ((line = bufferedReader.readLine()) != null && index < 100) {
                Station station = new Station(line);
                stations.add(station);
                index++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
        // generate random intersections for each station
        for (Station station : stations) {
            Random random =new Random();
            int num = random.nextInt(3) + 1;
            station.generateRandomIntersections(stations, num); // generate 3 random intersections for each station
        }

        return stations;
    }

    public String getId() {
        return id;
    }
    public Map<Station, Double> getKmToNextStation() {
        return getKmToNextStation();
    }
    public void setUnavailability(){
        this.available = false;
    }
    public void setAvailability(){
        this.available = true;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isAvailable() {
        return available;
    }
    public void addIntersectsWith(Station station,double distance) {
        this.intersectsWith.put(station,distance);
    }

    public String getName() {
        return name;
    }

    public Map<Station,Double> getIntersectsWith() {
        return intersectsWith;
    }

    public double getDistanceTo(Station station) {
        return intersectsWith.get(station);
    }

}
