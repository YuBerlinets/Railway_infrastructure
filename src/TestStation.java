import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestStation {
    public static void main(String[] args) {
        List<Station> stations = Station.generateStations();

         //print out each station and its intersections
        for (Station station : stations) {
            System.out.println("Station: " + station.getName());
            System.out.println("Intersections:");
            for (Map.Entry<Station, Double> entry : station.getIntersectsWith().entrySet()) {
                System.out.println(entry.getKey().getName() + ", distance: " + entry.getValue());
            }
            System.out.println();
        }

    }
}
