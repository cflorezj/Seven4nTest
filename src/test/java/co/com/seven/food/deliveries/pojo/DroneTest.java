package co.com.seven.food.deliveries.pojo;

import co.com.seven.food.deliveries.enumeration.Direction;
import co.com.seven.food.deliveries.enumeration.Orientation;
import junit.framework.TestCase;

public class DroneTest extends TestCase {

    private Drone test;

    @Override
    public void setUp() {
        test = new Drone("01");
    }

    public void testGoInitialPosition() {
        test.goHome();
        assertEquals(Direction.NORTH, test.getDirection());
        assertEquals(0, test.getLocation().getX());
        assertEquals(0, test.getLocation().getY());
    }

    public void testAddRoute() {
        test.addRoute("AAIIDDD");
        assertEquals(1, test.getRoutes().size());
    }

    public void testTurn90Degrees() {
        test.goHome();
        test.turn90Degrees(Orientation.LEFT);
        assertEquals(Direction.WEST, test.getDirection());
        test.turn90Degrees(Orientation.LEFT);
        assertEquals(Direction.SOUTH, test.getDirection());
        test.turn90Degrees(Orientation.LEFT);
        assertEquals(Direction.EAST, test.getDirection());
        test.turn90Degrees(Orientation.LEFT);
        assertEquals(Direction.NORTH, test.getDirection());
        test.turn90Degrees(Orientation.RIGHT);
        assertEquals(Direction.EAST, test.getDirection());
        test.turn90Degrees(Orientation.RIGHT);
        assertEquals(Direction.SOUTH, test.getDirection());
        test.turn90Degrees(Orientation.RIGHT);
        assertEquals(Direction.WEST, test.getDirection());
    }

    public void testMoveForward() {
        test.goHome();
        test.moveForward();
        assertEquals(Direction.NORTH, test.getDirection());
        assertEquals(1, test.getLocation().getY());
        assertEquals(0, test.getLocation().getX());
        test.turn90Degrees(Orientation.LEFT);
        assertEquals(Direction.WEST, test.getDirection());
        test.moveForward();
        assertEquals(1, test.getLocation().getY());
        assertEquals(-1, test.getLocation().getX());
        test.moveForward();
        assertEquals(1, test.getLocation().getY());
        assertEquals(-2, test.getLocation().getX());
        test.turn90Degrees(Orientation.RIGHT);
        assertEquals(Direction.NORTH, test.getDirection());
        test.moveForward();
        assertEquals(2, test.getLocation().getY());
        assertEquals(-2, test.getLocation().getX());
        test.turn90Degrees(Orientation.RIGHT);
        assertEquals(Direction.EAST, test.getDirection());
        test.moveForward();
        assertEquals(2, test.getLocation().getY());
        assertEquals(-1, test.getLocation().getX());
        test.turn90Degrees(Orientation.RIGHT);
        assertEquals(Direction.SOUTH, test.getDirection());
        test.moveForward();
        assertEquals(1, test.getLocation().getY());
        assertEquals(-1, test.getLocation().getX());
    }

    public void testGoRoute(){
        test.goHome();
        test.goRoute("AAAAIAA");
        assertEquals(Direction.WEST, test.getDirection());
        assertEquals(-2, test.getLocation().getX());
        assertEquals(4, test.getLocation().getY());
        assertEquals("(-2,4) heading WEST",test.toString());
    }
}
