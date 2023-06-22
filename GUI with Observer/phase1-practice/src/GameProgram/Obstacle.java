package GameProgram;

import java.awt.*;

public class Obstacle extends Rectangle{

    /**
     * Creates Obstacle object
     * @param width the height of the upper obstacle
     * @param y the height of the lower obstacle
     * @param x the coordinate of the location of the upper and lower obstacles
     */
    public Obstacle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * Sets the obstacle's upper/lower height and x coordinate location with the given values
     * @param topHeight the initialized upper height
     * @param bottomHeight the initialized lower height
     * @param width the initialized x location
     */
    //public void setObstacleSize(int topHeight, int bottomHeight, int width){
    //   topObstacle.setSize(width, topHeight);
    //    bottomObstacle.setSize(width, bottomHeight);
    //}

    /**
     * Returns the height of the upper part of the obstacle
     * @return int
     */
    //public int getTopObstacleHeight(){
    // return topObstacle.height;
    //}

    /**
     * Returns the height of the lower part of the obstacle
     * @return int
     */
    //public int getBottomObstacleHeight(){
    // return bottomObstacle.height;
    //}

    /**
     * Returns the width of the obstacles. Since the width is equal for both upper and lower obstacles,
     * it returns from bottomObstacle.
     * @return int
     */
    //public int getWidth() { return bottomObstacle.width; }

    /**
     * Gets the current location of the Obstacles
     * @return int
     */


    /**
     * Moves the obstacle to the left towards the negative direction by changing the x coordinate.
     */
    public void moveLeft() {
        x -= 1;
    }

}
