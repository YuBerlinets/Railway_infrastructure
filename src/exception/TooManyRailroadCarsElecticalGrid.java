package exception;

public class TooManyRailroadCarsElecticalGrid extends Exception{
    public TooManyRailroadCarsElecticalGrid(){
        super("Too many railroad cars are already connected to electrical grid");
    }
}
