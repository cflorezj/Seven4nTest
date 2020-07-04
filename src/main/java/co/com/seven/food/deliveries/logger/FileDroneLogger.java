package co.com.seven.food.deliveries.logger;

import co.com.seven.food.deliveries.pojo.Drone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileDroneLogger implements AbstractDroneLogger {

    private FileWriter writer;

    public FileDroneLogger(String outputFilePath) {
        File file = new File(outputFilePath);
        try {
            file.createNewFile();
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printDroneLocation(Drone drone) {
        try {
            writer.write(drone.toString().concat(" \n"));
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error trying to print Drone location to file");
            e.printStackTrace();
        }
    }
}
