package GameProgram;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {

    private final int WIDTH = 800, HEIGHT = 800;

    private final List<Reward> rewardsList = new ArrayList<>();

    private final List<Obstacle> obstacles = new ArrayList<>();

    private int obsHeight;

    private PropertyChangeSupport observable;

    /**
     * Creates new GameProgram.GameBoard object.
     */
    public GameBoard() {
        randomizeObstacle(true);
        randomizeObstacle(true);
        randomizeObstacle(true);
        this.observable = new PropertyChangeSupport(this);
    }

    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("obstacleLocations", observer);
        observable.addPropertyChangeListener("rewardLocation", observer);
    }

    public void notifyObservers(PropertyChangeEvent event) {
        for (PropertyChangeListener observer : observable.getPropertyChangeListeners()) {
            observer.propertyChange(event);
        }
    }

    public List<Obstacle> getObsList(){
        return this.obstacles;
    }

    public void setObsHeight(int height){
        this.obsHeight = height;
    }

    /**
     * Checks if player has hit an obstacle.
     * @param player the player in the current game
     * @return boolean
     */
    public boolean isTouchingObstacle(GamePlayer player) {
        for (Obstacle o : obstacles) {
            int x = o.x;
            int obsHeight = o.height;
            List<Double> playerPosition = player.getLocation();
            int width = player.getWidth();
            int height = player.getHeight();
            if (playerPosition.get(0) > x | x + o.width < playerPosition.get(0) + width) {
                return (playerPosition.get(1) < obsHeight | playerPosition.get(1) + height > obsHeight + 40);
            } else { return false; }
        }
        return false;
    }

    /**
     * Checks if player has hit the bottom of the game board.
     * @param player the player in the current game
     * @return boolean
     */
    public boolean isTouchingBottom(GamePlayer player) {
        List<Double> playerPosition = player.getLocation();
        return playerPosition.get(1) < 0;
    }

    /**
     * Checks if player has collected a reward.
     * @param player the player in the current game
     * @return boolean
     */
    public Reward isTouchingReward(GamePlayer player) {
        List<Double> playerPosition = player.getLocation();
        Double xPos = playerPosition.get(0);
        Double yPos = playerPosition.get(1);
        List<Reward> remove = new ArrayList<>();
        for (Reward r : rewardsList) {
            List<Double> rewardPosition = r.getRewardsLocation();
            if (xPos > rewardPosition.get(0) && xPos - player.getWidth() < rewardPosition.get(0) + r.getWidth()) {
                if (yPos + player.getHeight() > rewardPosition.get(1) && yPos < rewardPosition.get(1) + r.getHeight()) {
                    remove.add(r);
                }
            }
        }
        return removeReward(remove);
    }

    /**
     * Removes the reward from the rewards list.
     * @param remove the list where a Reward will be removed from
     * @return Reward
     */

    private Reward removeReward(List<Reward> remove) {
        if (!remove.isEmpty()) {
            rewardsList.remove(remove.get(0));
            return remove.get(0);
        }
        return null;
    }

    /**
     * Adds new obstacle to the game board.
     */
    public void updateObstacles() {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obs = obstacles.get(i);
            int oldLocation = obs.x;
            PropertyChangeEvent event = new PropertyChangeEvent(this, "obstacleLocations",
                    oldLocation, obs.getLocation());
            notifyObservers(event);
            obs.x -= 1;
            if (obs.x + obs.width < 0) {
                obstacles.remove(obs);
                if (obs.y == 0) {
                    randomizeObstacle(false);
                }
            }
        }
    }

    /**
     * Adds new reward to the game board.
     */
    public void updateRewards() {
        Obstacle last = obstacles.get(obstacles.size() - 1);
        if (last.x == 800) {
            rewardsList.add(randomizeReward());
        }
        if (!rewardsList.isEmpty()) {
            if (rewardsList.get(0).getXCoordinate() + rewardsList.get(0).getWidth() == 0) {
                rewardsList.remove(0);
            }
        }
    }

    /**
     * Creates a new randomized obstacle.
     * @return GameProgram.Obstacle
     */
    public void randomizeObstacle(boolean go) {
        int space = 250;
        int width = 75;
        int height = this.obsHeight;
        if (go){
            int x1 = WIDTH + width + obstacles.size() * 200;
            obstacles.add(new Obstacle(x1, HEIGHT - height - 120, width, height));
            obstacles.add(new Obstacle(x1, 0, width, HEIGHT - height - space));
        }
        else {
            int x3 = obstacles.get(obstacles.size() - 1).x + 400;
            obstacles.add(new Obstacle(x3, HEIGHT - height - 120, width, height));
            obstacles.add(new Obstacle(x3, 0, width, HEIGHT - height - space));
        }
    }

    /**
     * Creates a new randomized reward.
     * @return Rewards
     */
    public Reward randomizeReward() {
        List<Reward> reward = new ArrayList<>();
        Random rand = new Random();
        int y = rand.nextInt(this.HEIGHT - 20) + 5;
        reward.add(new Reward(this.WIDTH + 70, y, true));
        reward.add(new Reward(this.WIDTH + 70, y, false));
        int index = rand.nextInt(reward.size());
        return reward.get(index);
    }

    /**
     * Moves all objects on the game board (excluding player) left by 1 square.
     */
    public void moveObjects() {
        for (Obstacle o : obstacles) {
            o.moveLeft();
        }
        for (Reward r : rewardsList) {
            r.moveLeft();
        }
    }

    /**
     * Returns the current list of obstacles.
     *
     * @return List</ Obstacle>
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Adds a reward to the list of rewards.
     * @param r the reward to be added to the list of rewards
     */
    public void addReward(Reward r) {
        this.rewardsList.add(r);
    }

    /**
     *  Returns the list of rewards.
     * @return List</Reward>
     */
    public List<Reward> getRewardsList() { return this.rewardsList; }

    /**
     * Returns the current obstacle height.
     */

    public int getObsHeight() {return this.obsHeight;}


}







