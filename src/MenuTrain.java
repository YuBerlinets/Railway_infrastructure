import java.util.Map;

public class MenuTrain {
    Map<String, Trainset> menu;
    MenuTrain(Map<String,Trainset> menu){
        this.menu = menu;
    }
    public Trainset getInformation(String trainset){
        return this.menu.get(trainset);
    }
}
