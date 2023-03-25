
public class Station {
    private String id = "s"; //s stands for station
    private String name;
    public static int count = 1;

    Station(String name){
        this.name = name;
        this.id = id + count++;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return "\nID: " + getId() + " Name: " + getName();

    }

}
