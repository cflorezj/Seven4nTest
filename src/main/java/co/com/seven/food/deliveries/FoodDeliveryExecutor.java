package co.com.seven.food.deliveries;

import co.com.seven.food.deliveries.logger.AbstractDroneLogger;
import co.com.seven.food.deliveries.logger.FileDroneLogger;
import co.com.seven.food.deliveries.pojo.DeliveryArea;
import co.com.seven.food.deliveries.pojo.Drone;
import co.com.seven.food.deliveries.thread.DeliveryTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is responsible for manage all the Drones and trigger the deliveries scheduled by the managers.
 */
public class FoodDeliveryExecutor {

    /**
     * This field represents the allowed area to deliver food. Initially 10 blocks around.
     */
    private DeliveryArea area;

    /**
     * List of Drones ready and set to deliver food.
     */
    private List<Drone> drones = new ArrayList<>();

    /**
     * Capacity of deliveries at same time per Drone. Initially 3.
     */
    private int capacityPerDrone;

    /**
     * Directory path where business managers places the routes for deliveries per Drone.
     */
    private String routesDirectoryPath;

    /**
     * Number of available Drones. Initially 20.
     */
    private int numberDronesAvailable;

    private static final String INPUT_ROUTE_FILE_PREFIX = "in";

    private static final String OUTPUT_ROUTE_FILE_PREFIX = "out";

    private static final String TXT_FILE_EXTENSION = ".txt";

    public FoodDeliveryExecutor(int numberBlocksAround, int capacityPerDrone, String routesDirectoryPath, int numberDronesAvailable) {
        area = new DeliveryArea(numberBlocksAround);
        this.capacityPerDrone = capacityPerDrone;
        this.routesDirectoryPath = routesDirectoryPath;
        this.numberDronesAvailable = numberDronesAvailable;
    }

    /**
     * This method create Drones instances based on inputs files found in the routesDirectoryPath parameter.
     * Drone instance will set all routes to be processed later and its underlying output logger instance will be set as well.
     */
    public void setupDrones() {
        File routesDirectory = new File(routesDirectoryPath);
        if (routesDirectory.isDirectory()) {
            for (int i = 1; i <= this.numberDronesAvailable; i++) {
                String droneID = (i < 10) ? ("0").concat(String.valueOf(i)) : String.valueOf(i);
                String droneRoutesFileName = INPUT_ROUTE_FILE_PREFIX.concat(droneID).concat(TXT_FILE_EXTENSION);
                File droneRoutesFile = new File(routesDirectoryPath.concat(droneRoutesFileName));
                if (droneRoutesFile.exists()) {
                    System.out.format("[File %s found for drone ID %s ] \n", droneRoutesFile.getPath(), droneID);
                    Drone drone = new Drone(droneID);
                    AbstractDroneLogger droneLogger = new FileDroneLogger(this.routesDirectoryPath.concat(OUTPUT_ROUTE_FILE_PREFIX).concat(drone.getId()).concat(TXT_FILE_EXTENSION));
                    drone.setLogger(droneLogger);
                    this.drones.add(drone);
                    try (Stream<String> stream = Files.lines(droneRoutesFile.toPath())) {
                        stream.forEach(route -> drone.addRoute(route));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * This method send Drones to deliver all the orders requested.
     */
    public void triggerDeliveries() {
        if (drones.isEmpty()) {
            System.out.println("No deliveries found to send in directory:" + routesDirectoryPath);
        } else {
            for (Drone drone : drones) {
                DeliveryTask deliveryTask = new DeliveryTask(drone, capacityPerDrone, area);
                Thread processDeliveries = new Thread(deliveryTask);
                processDeliveries.start();
            }
        }
    }

    public List<Drone> getDrones() {
        return drones;
    }
}
