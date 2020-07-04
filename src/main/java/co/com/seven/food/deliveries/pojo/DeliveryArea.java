package co.com.seven.food.deliveries.pojo;

/**
 * This class represents the area available for deliveries services
 */
public class DeliveryArea {

    //Number blocks around to offer food delivery service
    private int numberBlocksAround;

    //Border allowed for positions where x and y are positive values
    private Location firstQuadrantBorder;

    //Border allowed for positions where x is a negative and y is a positive value
    private Location secondQuadrantBorder;

    //Border allowed for positions where x and y are negative values
    private Location thirdQuadrantBorder;

    //Border allowed for positions where x is a positive and y is a negative value
    private Location fourthQuadrantBorder;

    public DeliveryArea(int numberBlocksAround) {
        this.numberBlocksAround = numberBlocksAround;
        this.firstQuadrantBorder = new Location(numberBlocksAround, numberBlocksAround);
        this.secondQuadrantBorder = new Location(-1 * numberBlocksAround, numberBlocksAround);
        this.thirdQuadrantBorder = new Location(-1 * numberBlocksAround, -1 * numberBlocksAround);
        this.fourthQuadrantBorder = new Location(numberBlocksAround, -1 * numberBlocksAround);
    }

    public boolean isValidPosition(Location location) {
        if (location.getX() >= 0 && location.getY() >= 0) {
            if (location.getX() <= firstQuadrantBorder.getX() && location.getY() <= firstQuadrantBorder.getY())
                return true;
        } else if (location.getX() >= 0 && location.getY() < 0) {
            if (location.getX() <= fourthQuadrantBorder.getX() && location.getY() >= fourthQuadrantBorder.getY())
                return true;
        } else if (location.getX() < 0 && location.getY() >= 0) {
            if (location.getX() >= secondQuadrantBorder.getX() && location.getY() <= secondQuadrantBorder.getY())
                return true;
        } else {
            if (location.getX() >= thirdQuadrantBorder.getX() && location.getY() >= thirdQuadrantBorder.getY())
                return true;
        }
        return false;
    }

    public int getNumberBlocksAround() {
        return numberBlocksAround;
    }

    @Override
    public String toString() {
        return "DeliveryArea{" +
                "numberBlocksAround=" + numberBlocksAround +
                ", firstQuadrantBorder=" + firstQuadrantBorder +
                ", secondQuadrantBorder=" + secondQuadrantBorder +
                ", thirdQuadrantBorder=" + thirdQuadrantBorder +
                ", fourthQuadrantBorder=" + fourthQuadrantBorder +
                '}';
    }
}
