package GameProgram;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Reward {


    private final Point rewardLocation = new Point();
    private final Dimension dimensions = new Dimension(10, 10);

    private final boolean isBad;


    /**
     * Creates Reward object and takes in the initial coordinates
     * @param start_x initializes x coordinate
     * @param start_y initializes y coordinate
     * @param isBad stating whether the reward is good or bad
     */
    public Reward(int start_x, int start_y, boolean isBad){
        this.rewardLocation.x = start_x;
        rewardLocation.y = start_y;
        this.isBad = isBad;
    }

    /**
     * Gets the current location of the reward and returns the coordinates as a list
     * @return List</Double>
     */
    public List<Double> getRewardsLocation(){
        List<Double> coordinates = new ArrayList<>();
        coordinates.add((double) rewardLocation.x);
        coordinates.add((double) rewardLocation.y);
        return coordinates;
    }

    /**
     * Tells the boolean value of the reward
     * @return boolean
     */
    public boolean isBadReward(){
        return this.isBad;
    }

    /**
     * Returns the current x coordinate of the reward
     * @return int
     */
    public int getXCoordinate() { return rewardLocation.x; }

    /**
     * Moves the reward to the left in the negative direction by subtracting the x coordinate value
     */
    public void moveLeft() {
        rewardLocation.x -= 1;
    }

    public int getWidth() { return dimensions.width; }

    public int getHeight() { return dimensions.height; }
}