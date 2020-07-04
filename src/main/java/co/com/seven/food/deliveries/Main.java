package co.com.seven.food.deliveries;

/**
 * Main class to execute the delivery food app.
 */
public class Main {

    /**
     * Initial entry point to execute this app.
     * @param args
     *      The app support 4 parameters in the command line in the following order:
     *      1. Directory path to check files with routes defined per each Drone.
     *      2. Number of blocks around to support delivery services. Default is 10 blocks around.
     *      3. Capacity deliveries at same time per Drone. Default is 3 deliveries at same time.
     *      4. Number of Available drones. Default is 20.
     */
    public static void main(String... args) {

        //Initially number blocks around for deliveries is 10, but this area could be extended in the future, this can be set as a parameter.
        int numberBlocksAround = 10;
        //Initially Drones are capable to handle 3 deliveries at same time, but this capacity can be increase in the future, this can be set as a parameter.
        int capacityPerDrone = 3;
        //Initially business count with 20 Drones for deliveries, in case new Drones are available, this can be set as a parameter.
        int numberDronesAvailable = 20;
        //Directory path where inputs files with routes are located by business manager. this parameter is a must.
        String routesDirectoryPath = "";

        if (args.length > 0) {
            routesDirectoryPath = args[0];
            if (args.length > 1) {
                numberBlocksAround = Integer.valueOf(args[1]);
                if (args.length > 2) {
                    capacityPerDrone = Integer.valueOf(args[2]);
                    if (args.length > 3) {
                        numberDronesAvailable = Integer.valueOf(args[3]);
                    }
                }
            }
            System.out.format("[Params: Routes directory: %s  Number of blocks around for deliveries: %d  Capacity per Drone: %d ] \n", routesDirectoryPath, numberBlocksAround, capacityPerDrone);

            FoodDeliveryExecutor foodDeliveryExecutor = new FoodDeliveryExecutor(numberBlocksAround, capacityPerDrone, routesDirectoryPath, numberDronesAvailable);
            foodDeliveryExecutor.setupDrones();
            foodDeliveryExecutor.triggerDeliveries();

        } else {
            throw new IllegalStateException("At least one parameter with route's files directory is required");
        }

    }

}
