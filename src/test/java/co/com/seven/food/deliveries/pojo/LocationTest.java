package co.com.seven.food.deliveries.pojo;

import junit.framework.TestCase;

public class LocationTest extends TestCase {

    private Location test;

    @Override
    public void setUp() {
        test = new Location(20, 5);
    }

    public void testXCoordinate(){
        assertEquals(20, test.getX());
    }

    public void testYCoordinate(){
        assertEquals(5, test.getY());
    }
}
