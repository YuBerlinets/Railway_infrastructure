import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Station {
    private String id; //s stands for station
    private String name;
    private Map<Station, Double> intersectsWith;
    private boolean visited;
    private boolean available = true;
    public static int count = 1;


    Station(String name) {
        this.name = name;
        this.id = "s" + count++;
        this.intersectsWith = new HashMap<>();
        this.visited = false;
    }

    @Override
    public String toString() {
        return " ID: " + getId() + " Name: " + getName();

    }
    public static List<Station> generateStations() {
        List<Station> stations = new ArrayList<>();
        String path = "TechFiles\\Stations.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = null;
            int index = 0;
            while ((line = bufferedReader.readLine()) != null) {
                Station station = new Station(line);
                stations.add(station);
                index++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }
        for (Station station : stations) {
            int numConnections = station.getIntersectsWith().size();
            while (numConnections < 3) {
                Station otherStation = stations.get(new Random().nextInt(stations.size()));
                if (otherStation != station && !station.intersectsWith.containsKey(otherStation)) {
                    double distance = new Random().nextDouble() * 300 + 300;//distance between 300 and 600
                    station.addIntersectsWith(otherStation, distance);
                    otherStation.addIntersectsWith(station, distance);
                    numConnections++;
                }
            }
        }

        return stations;
    }


    public String getId() {
        return id;
    }

    public void setUnavailability() {
        this.available = false;
    }

    public void setAvailability() {
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

    public void addIntersectsWith(Station station, double distance) {
        this.intersectsWith.put(station, distance);
    }

    public String getName() {
        return name;
    }

    public Map<Station, Double> getIntersectsWith() {
        return intersectsWith;
    }

    public double getDistanceTo(Station station) {
        return intersectsWith.get(station);
    }

}
