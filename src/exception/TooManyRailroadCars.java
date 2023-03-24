package exception;

public class TooManyRailroadCars extends Exception{
    public TooManyRailroadCars(){
        super("Too many railroad cars are already connected");
    }
}
