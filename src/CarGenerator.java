import RailroadCars.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CarGenerator {
    CarGenerator() {

    }
    public void generateCars(Trainset trainset) {
        Random random = new Random();
        int maxCars = random.nextInt(6) + 5;
        String[] carTypes = {"prc",
                "porc", "bmrc", "rrc", "rfrc", "lrc", "grc", "erc", "trc", "ltrc"};
        for (int i = 0; i < maxCars; i++) {
            int carIndex = random.nextInt(carTypes.length);
            String type = carTypes[carIndex];
            switch (type) {
                case "prc": {
                    int carClass = random.nextInt(1) + 1;
                    int numSeats = random.nextInt(21) + 30;
                    PassengerRailroadCar prc = new PassengerRailroadCar(getShipper(), generateNetWeight(), carClass, numSeats);
                    prc.connectToElectricalGrid();
                    prc.addCargo(generateGrossWeight());
                    trainset.addCar(prc);
                    break;
                }
                case "porc": {
                    int maxCapacityBoxes = random.nextInt(21) + 30;
                    boolean fragileParcels = random.nextBoolean();
                    PostOfficeRailroadCar porc = new PostOfficeRailroadCar(getShipper(), generateNetWeight(), maxCapacityBoxes, fragileParcels);
                    porc.connectToElectricalGrid();
                    porc.addCargo(generateGrossWeight());
                    trainset.addCar(porc);
                    break;
                }
                case "bmrc": {
                    int maxNumSuitcase = random.nextInt(21) + 25;
                    boolean pets = random.nextBoolean();
                    BaggageMailRailroadCar bmrc = new BaggageMailRailroadCar(getShipper(), generateNetWeight(), maxNumSuitcase, pets);
                    bmrc.addCargo(generateGrossWeight());
                    trainset.addCar(bmrc);
                    break;
                }
                case "rrc": {
                    int staff = random.nextInt(6) + 2;
                    int tables = random.nextInt(5) + 9;
                    RestaurantRailroadCar rrc = new RestaurantRailroadCar(getShipper(), generateNetWeight(), staff, tables);
                    rrc.connectToElectricalGrid();
                    rrc.addCargo(generateGrossWeight());
                    trainset.addCar(rrc);
                    break;
                }
                case "rfrc": {
                    String[] coolingMethods = {"Direct Expansion System", "Cryogenic Cooling", "Eutectic Plate System"};
                    String coolingMethod = coolingMethods[random.nextInt(coolingMethods.length)];
                    boolean humidityContol = random.nextBoolean();
                    RefrigeratedRailroadCar rfrc = new RefrigeratedRailroadCar(getShipper(), generateNetWeight(), coolingMethod, humidityContol);
                    rfrc.connectToElectricalGrid();
                    rfrc.addCargo(generateGrossWeight());
                    trainset.addCar(rfrc);
                    break;
                }
                case "lrc": {
                    String[] liquidTypes = {"Water", "Chemicals", "Liquid waste", "Oil", "Liquid metals"};
                    String liquidType = liquidTypes[random.nextInt(liquidTypes.length)];
                    LiquidRailroadCar lrc = new LiquidRailroadCar(getShipper(), generateNetWeight(), liquidType);
                    lrc.addCargo(generateGrossWeight());
                    trainset.addCar(lrc);
                    break;
                }
                case "grc": {
                    String[] gases = {"Petrol", "LNG", "Diesel"};
                    String gas = gases[random.nextInt(gases.length)];
                    String[] gasCompressors = {"Positive Displacement Compressor", "Centrifugal Compressor", "Axial Compressor"};
                    String gasCompressor = gasCompressors[random.nextInt(gasCompressors.length)];
                    GaseousRailroadCar grc = new GaseousRailroadCar(getShipper(), generateNetWeight(), gas, gasCompressor);
                    grc.addCargo(generateGrossWeight());
                    trainset.addCar(grc);
                    break;
                }
                case "erc": {
                    int securityGuard = random.nextInt(4) + 1;
                    char[] danderTypes = {'A', 'B', 'C'};
                    char danderType = danderTypes[random.nextInt(danderTypes.length)];
                    String[] cargoTypes = {"Ammunition", "TNT", "Nitroglycerin", "Pyrotechnics", "Detonators", "RDX"};
                    String cargoType = cargoTypes[random.nextInt(cargoTypes.length)];
                    ExplosiveRailroadCar erc = new ExplosiveRailroadCar(getShipper(), generateNetWeight(), securityGuard, danderType, cargoType);
                    erc.addCargo(generateGrossWeight());
                    trainset.addCar(erc);
                    break;
                }
                case "trc": {
                    String[] toxicMaterials = {"Uranium", "Plutonium", "Acids", "Radioactive waste", "Industrial waste",
                            "Poisons", "Toxins"};
                    String toxicMaterial = toxicMaterials[random.nextInt(toxicMaterials.length)];
                    ToxicRailroadCar trc = new ToxicRailroadCar(getShipper(), generateNetWeight(), toxicMaterial);
                    trc.addCargo(generateGrossWeight());
                    trainset.addCar(trc);
                    break;
                }
                case "ltrc": {
                    String[] cargoTypes = {"Brake fluid", "Benzene", "Wastewater", "Glue", "Transmission fluid"};
                    String cargoType = cargoTypes[random.nextInt(cargoTypes.length)];
                    LiquidToxicRailroadCar ltrc = new LiquidToxicRailroadCar(getShipper(), generateNetWeight(), cargoType);
                    ltrc.addCargo(generateGrossWeight());
                    trainset.addCar(ltrc);
                    break;
                }
                default: {
                    System.out.println("Error");
                }
            }
        }

    }

    private double generateGrossWeight() {
        Random random = new Random();
        return Math.round(random.nextDouble(1001) + 2000);
    }

    private static double generateNetWeight() {
        Random random = new Random();
        return Math.round(random.nextDouble(1000 + 1) + 3000);
    }

    public String getShipper() {
        String result = null;
        Random random = new Random();
        String path = "TechFiles\\CarShipper.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> lines = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            int randomLine = random.nextInt(lines.size());
            result = lines.get(randomLine);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
        }

        return result;
    }
}
