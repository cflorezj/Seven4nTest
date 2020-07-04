package co.com.seven.food.deliveries.thread;

import co.com.seven.food.deliveries.pojo.DeliveryArea;
import co.com.seven.food.deliveries.pojo.Drone;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Async task to process all deliveries assigned to a Drone.
 */
public class DeliveryTask implements Runnable {

    private Drone drone;

    private int capacity;

    private DeliveryArea area;

    public DeliveryTask(Drone drone, int capacity, DeliveryArea area) {
        this.drone = drone;
        this.capacity = capacity;
        this.area = area;
    }

    @Override
    public void run() {
        final AtomicInteger routesCounter = new AtomicInteger();
        //A Drone has a defined capacity of deliveries at same time, if the amount of deliveries for a Drone
        // is greater than its capacity, it should deliver by batches based on its capacity (Initially is 3).
        int deliveriesCounter = 0;
        for (String route : drone.getRoutes()) {
            if (deliveriesCounter == 0 || deliveriesCounter % capacity == 0) {
                //Drone starts at (0,0) towards NORTH position before deliver a new batch of deliveries based on its capacity.
                drone.goHome();
                System.out.format("\n[Sending next batch of orders with Drone %s location %s]\n", drone.getId(), drone.toString());
            }
            deliver(drone, route);
            deliveriesCounter++;
        }
    }

    /**
     * @param drone
     * @param route
     */
    private void deliver(Drone drone, String route) {
        drone.goRoute(route);
        if (!area.isValidPosition(drone.getLocation())) {
            throw new IllegalStateException("Position out of covered area, not possible to handle this delivery : " + drone.getLocation());
        }
        System.out.format("[Order delivered, Drone location %s]\n", drone.toString());
        drone.printLocation();
    }
}
