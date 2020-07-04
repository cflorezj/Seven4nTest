package co.com.seven.food.deliveries.thread;

import co.com.seven.food.deliveries.enumeration.Direction;
import co.com.seven.food.deliveries.logger.AbstractDroneLogger;
import co.com.seven.food.deliveries.logger.FileDroneLogger;
import co.com.seven.food.deliveries.pojo.DeliveryArea;
import co.com.seven.food.deliveries.pojo.Drone;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

public class DeliveryTaskTest extends TestCase {

    private DeliveryTask test;

    private Drone drone = new Drone("01");

    @Override
    public void setUp() throws IOException {
        DeliveryArea deliveryArea = new DeliveryArea(10);
        drone.addRoute("ADDAIAD");
        drone.addRoute("DAAAIAD");
        drone.addRoute("AAIADAA");
        File output = File.createTempFile("ouput", "txt");
        AbstractDroneLogger logger = new FileDroneLogger(output.getPath());
        drone.setLogger(logger);
        test = new DeliveryTask(drone, 3, deliveryArea);
    }

    public void testRun() {
        System.out.println("\n[Test with only one batch of 3 deliveries]");
        test.run();
        assertEquals(Direction.WEST, drone.getDirection());
        assertEquals(-6, drone.getLocation().getX());
        assertEquals(-2, drone.getLocation().getY());
    }

    public void testRun2Batches() {
        System.out.println("\n[Test with 2 batches, first one with 3 deliveries and second one with 2 left]");
        //Add another delivery batch with 2 additional routes.
        drone.addRoute("AAAAIAA");
        drone.addRoute("DDDAIAD");
        test.run();
        assertEquals(Direction.SOUTH, drone.getDirection());
        assertEquals(-1, drone.getLocation().getX());
        assertEquals(3, drone.getLocation().getY());
    }
}
