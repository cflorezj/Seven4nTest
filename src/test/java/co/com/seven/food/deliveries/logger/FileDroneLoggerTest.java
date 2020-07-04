package co.com.seven.food.deliveries.logger;

import co.com.seven.food.deliveries.pojo.Drone;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;


public class FileDroneLoggerTest extends TestCase {

    private FileDroneLogger test;

    private Drone drone = new Drone("01");

    private File output;

    @Override
    public void setUp() throws IOException {
        output = File.createTempFile("ouput","txt");
        test = new FileDroneLogger(output.getPath());
        drone.goHome();
    }

    public void testPrintDroneLocation(){
        test.printDroneLocation(drone);

        try (Stream<String> stream = Files.lines(output.toPath())) {
            stream.forEach( line ->
                    assertEquals("(0,0) heading NORTH ",line)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
