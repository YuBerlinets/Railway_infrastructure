public class Main {
    public static void main(String[] args){
        Station warsaw = new Station("Warsaw, Poland");
        Station wroclaw = new Station("Wroclaw, Poland");
        Station poltava = new Station("Poltava, Ukraine");
        Station london = new Station("London, UK");
        Station paris = new Station("Paris, France");
        Locomotive l1 = new Locomotive("Victory", poltava, warsaw, wroclaw);
        Locomotive l2 = new Locomotive("Python", warsaw, london, paris);


        PassengerRailroadCar prc1 = new PassengerRailroadCar("Ukrzaliznitsya", 4000, 40);
        PassengerRailroadCar prc2 = new PassengerRailroadCar("PKP Intercity", 3800, 52);
        PassengerRailroadCar prc3 = new PassengerRailroadCar("Deutsche Bahn",4100, 55);
        PostOfficeRailroadCar porc1 = new PostOfficeRailroadCar("UkrPoshta", 1000);
        PostOfficeRailroadCar porc2 = new PostOfficeRailroadCar("Poczta Polska", 2000);
        BaggageMailRailroadCar bmrc1 = new BaggageMailRailroadCar("DHL", 3200);
        BaggageMailRailroadCar bmrc2 = new BaggageMailRailroadCar("American Express", 3920);
        BasicFreightRailroadCar bfrc1 = new BasicFreightRailroadCar("Stark Industries", 2500);
        BasicFreightRailroadCar bfrc2 = new BasicFreightRailroadCar("G&G Company LLC", 4000);
        RestaurantRailroadCar rrc1 = new RestaurantRailroadCar("Mcdonald's", 3800);
        RestaurantRailroadCar rrc2 = new RestaurantRailroadCar("KFC", 3950);
        RestaurantRailroadCar rrc3 = new RestaurantRailroadCar("WOG", 3200);


        porc1.connectToElectricalGrid();
        rrc1.connectToElectricalGrid();
        rrc2.connectToElectricalGrid();
        //rrc3.connectToElectricalGrid();//throws an exception

        Trainset t1 = new Trainset(l1);
        t1.addCar(prc1);
        t1.addCar(prc2);
        t1.addCar(prc3);
        t1.addCar(porc1);
        t1.addCar(porc2);
        t1.addCar(bmrc1);
        t1.addCar(bmrc2);
        t1.addCar(bfrc1);
        t1.addCar(bfrc2);
        t1.addCar(rrc1);
        t1.addCar(rrc2);
        t1.addCar(rrc3); //throws an exception
        //t1.addCar(bmrc2);//throws an exception

        prc1.addPeople(38);
        prc2.addPeople(43);

        System.out.println(t1);
        System.out.println(t1.getNumRailroadCarsElectricityGrid());

        Trainset t2 = new Trainset(l2);
        t2.addCar(prc1);
        t2.addCar(prc2);
        t2.addCar(prc3);
        t2.addCar(porc1);
        t2.addCar(porc2);
        t2.addCar(bmrc1);
        t2.addCar(bmrc2);
        t2.addCar(bfrc1);
        t2.addCar(bfrc2);
        t2.addCar(rrc1);
        t2.addCar(rrc2);
        t2.addCar(rrc3);

        System.out.println(t2);

    }
}
