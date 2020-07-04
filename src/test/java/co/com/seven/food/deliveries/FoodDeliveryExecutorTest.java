package co.com.seven.food.deliveries;

import junit.framework.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FoodDeliveryExecutorTest extends TestCase {

    private FoodDeliveryExecutor test;

    private String absolutePath;

    @Override
    public void setUp() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        absolutePath = resourceDirectory.toFile().getAbsolutePath().concat("/");

        test = new FoodDeliveryExecutor(10, 3, absolutePath, 20);
    }

    public void testSetupDrones() {
        test.setupDrones();
        assertEquals(false, test.getDrones().isEmpty());
        assertEquals(2, test.getDrones().size());
        assertEquals(3, test.getDrones().get(0).getRoutes().size());
        assertEquals(3, test.getDrones().get(1).getRoutes().size());
    }

    public void testTriggerDeliveries() throws IOException {
        test.setupDrones();
        test.triggerDeliveries();
        assertEquals(true, Files.exists(Paths.get(absolutePath.concat("out01.txt"))));
        assertEquals(true, Files.exists(Paths.get(absolutePath.concat("out02.txt"))));
    }
}
