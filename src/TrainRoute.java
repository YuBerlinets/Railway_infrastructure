public class TrainRoute {
    private Trainset trainset;
    private Station sourceStation;
    private Station destinationStation;

    TrainRoute(Trainset trainset, Station sourceStation, Station destinationStation){
        this.trainset = trainset;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
    }


    //add a method that will generate a route

    public Trainset getTrainset() {
        return trainset;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }
}
