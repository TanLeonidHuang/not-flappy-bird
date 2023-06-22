package GameProgram;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Obstacle {
    private final Dimension topObstacle;
    private final Dimension bottomObstacle;

    private int xCoordinate;

    private PropertyChangeSupport observable;

    /**
     * Creates Obstacle object
     * @param topHeight the height of the upper obstacle
     * @param bottomHeight the height of the lower obstacle
     * @param x the coordinate of the location of the upper and lower obstacles
     */
    public Obstacle(int topHeight, int bottomHeight, int x) {
        topObstacle = new Dimension(30, topHeight);
        bottomObstacle = new Dimension(30, bottomHeight);
        xCoordinate = x;
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer) { observable.addPropertyChangeListener(
            "obstacleLocation", observer); }

    public void notifyObservers(PropertyChangeEvent event) {
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()) {
            observer.propertyChange(event);
        }
    }

    /**
     * Sets the obstacle's upper/lower height and x coordinate location with the given values
     * @param topHeight the initialized upper height
     * @param bottomHeight the initialized lower height
     * @param width the initialized x location
     */
    public void setObstacleSize(int topHeight, int bottomHeight, int width){
        topObstacle.setSize(width, topHeight);
        bottomObstacle.setSize(width, bottomHeight);
    }

    /**
     * Returns the height of the upper part of the obstacle
     * @return int
     */
    public int getTopObstacleHeight(){
        return topObstacle.height;
    }

    /**
     * Returns the height of the lower part of the obstacle
     * @return int
     */
    public int getBottomObstacleHeight(){
        return bottomObstacle.height;
    }

    /**
     * Returns the width of the obstacles. Since the width is equal for both upper and lower obstacles,
     * it returns from bottomObstacle.
     * @return int
     */
    public int getWidth() { return bottomObstacle.width; }

    /**
     * Gets the current location of the Obstacles
     * @return int
     */
    public int getLocation() {
        return xCoordinate;
    }

    /**
     * Moves the obstacle to the left towards the negative direction by changing the x coordinate.
     */
    public void moveLeft() {
        int oldX = this.xCoordinate;
        xCoordinate -= 1;
        PropertyChangeEvent event = new PropertyChangeEvent(this, "obstacleLocation", oldX,
                this.xCoordinate);
        notifyObservers(event);
    }

    public void setLocation(int x) { this.xCoordinate = x; }
}
