package co.com.seven.food.deliveries.pojo;

import junit.framework.TestCase;


public class DeliveryAreaTest extends TestCase {

    private DeliveryArea test;

    @Override
    public void setUp() {
        test = new DeliveryArea(10);
    }

    public void testNumberBlocksAround() {
        assertEquals(10, test.getNumberBlocksAround());
    }

    public void testValidPosition() {

        assertEquals(true, test.isValidPosition(new Location(5, 10)));

    }

    public void testNotValidPosition() {

        assertEquals(false, test.isValidPosition(new Location(5, 11)));
        assertEquals(false, test.isValidPosition(new Location(5, -11)));
        assertEquals(false, test.isValidPosition(new Location(-5, -11)));

    }
}
