import java.util.*;

public class Route{
    private final List<Station> stations;
    private double totalDistance;
    public Route(){
        this.stations = new ArrayList<>();
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }

    public List<Station> getRouteStations() {
        return stations;
    }
    public void reverse() {
        Collections.reverse(this.stations);
    }

    public double getTotalDistance() {
        return totalDistance;
    }
    public void calculatedDistance(double totalDistance){
        this.totalDistance = totalDistance;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Station station : stations) {
            sb.append(station.getName()).append(" -> ");
        }
        sb.append("END");
        return sb.toString();
    }
}