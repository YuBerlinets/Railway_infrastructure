package exception;

public class TooManyPeople extends Exception{
    public TooManyPeople(){
        super("Can't add more people. Too many people");
    }
}
