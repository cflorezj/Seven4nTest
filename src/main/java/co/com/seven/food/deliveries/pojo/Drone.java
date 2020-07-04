package co.com.seven.food.deliveries.pojo;

import co.com.seven.food.deliveries.enumeration.Direction;
import co.com.seven.food.deliveries.enumeration.Orientation;
import co.com.seven.food.deliveries.logger.AbstractDroneLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.seven.food.deliveries.enumeration.Orientation.RIGHT;

/**
 * This class represents a Drone available for delivery.
 */
public class Drone {

    private String id;
    private Location location;
    private Direction direction;
    private List<String> routes = new ArrayList<String>();

    private AbstractDroneLogger logger;

    public Drone(String id) {
        this.id = id;
    }

    /**
     * This method turn Drone 90 degrees to right/left direction.
     * @param orientation
     */
    public void turn90Degrees(Orientation orientation) {
        switch (this.direction) {
            case NORTH:
                this.direction = (orientation.equals(RIGHT)) ? Direction.EAST : Direction.WEST;
                break;
            case SOUTH:
                this.direction = (orientation.equals(RIGHT)) ? Direction.WEST : Direction.EAST;
                break;
            case EAST:
                this.direction = (orientation.equals(RIGHT)) ? Direction.SOUTH : Direction.NORTH;
                break;
            case WEST:
                this.direction = (orientation.equals(RIGHT)) ? Direction.NORTH : Direction.SOUTH;
                break;
        }
    }

    /**
     * This method move Drone forward.
     */
    public void moveForward() {
        switch (this.direction) {
            case NORTH:
                this.location.setY(this.location.getY() + 1);
                break;
            case SOUTH:
                this.location.setY(this.location.getY() - 1);
                break;
            case EAST:
                this.location.setX(this.location.getX() + 1);
                break;
            case WEST:
                this.location.setX(this.location.getX() - 1);
                break;
        }
    }

    /**
     * This method send Drone back to the start position (0,0) in direction NORTH.
     */
    public void goHome() {
        this.location = new Location(0,0);
        this.direction = Direction.NORTH;
    }

    /**
     * This method send Drone to navigate to a defined route.
     * @param route
     *      Defines the route to navigate. i.e AAAAIAA
     */
    public void goRoute(String route) {
        System.out.format("[Sending Drone: %s to following route: %s]\n", this.getId(), route);

        List<Character> list = route.chars()
                .mapToObj(item -> (char) item)
                .collect(Collectors.toList());

        list.forEach(movement -> {
            switch (movement.toString()) {
                case "A":
                    this.moveForward();
                    break;
                case "D":
                    this.turn90Degrees(Orientation.RIGHT);
                    break;
                case "I":
                    this.turn90Degrees(Orientation.LEFT);
                    break;
                default:
                    throw new IllegalStateException("Unexpected movement value: " + movement);
            }
        });
    }

    public void printLocation(){
        logger.printDroneLocation(this);
    }

    public void setLogger(AbstractDroneLogger logger) {
        this.logger = logger;
    }

    public void addRoute(String route) {
        this.routes.add(route);
    }

    public List<String> getRoutes() {
        return routes;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public Direction getDirection() {
        return direction;
    }


    @Override
    public String toString() {
        return "(" + this.location.getX() + "," + this.location.getY() + ") heading " + direction;
    }
}
