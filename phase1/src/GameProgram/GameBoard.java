package GameProgram;

import The_GUI.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {

    private final int width = 300;



    private final int height = 600;

    private final List<Reward> rewardsList = new ArrayList<>();

    private final List<Obstacle> obstacleList = new ArrayList<>();

    /**
     * Creates new GameProgram.GameBoard object.
     */
    public GameBoard() {
        this.obstacleList.add(new Obstacle(120, 120, 100));
        this.obstacleList.add(randomizeObstacle());
        obstacleList.get(1).setLocation(220);
    }

    /**
     * Checks if player has hit an obstacle.
     * @param player the player in the current game
     * @return boolean
     */
    public boolean isTouchingObstacle(GamePlayer player) {
        for (Obstacle o : obstacleList) {
            int oLocation = o.getLocation();
            int blank = o.getBottomObstacleHeight();
            List<Double> playerPosition = player.getLocation();
            int width = player.getWidth();
            int height = player.getHeight();
            if (playerPosition.get(0) > oLocation | oLocation + o.getWidth() < playerPosition.get(0) + width) {
                return (playerPosition.get(1) < blank | playerPosition.get(1) + height > blank + 40);
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
        if (obstacleList.get(0).getLocation() == 60) {
            obstacleList.add(randomizeObstacle());
        } else if (obstacleList.get(0).getLocation() + obstacleList.get(0).getWidth() == 0) {
            obstacleList.remove(0);
        }
    }

    /**
     * Adds new reward to the game board.
     */
    public void updateRewards() {
        Obstacle last = obstacleList.get(obstacleList.size() - 1);
        if (last.getLocation() == 300) {
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
    public Obstacle randomizeObstacle() {
        List<Obstacle> obstacleList = new ArrayList<>();
        obstacleList.add(new Obstacle(320, 160, this.width));
        obstacleList.add(new Obstacle(160, 320, this.width));
        obstacleList.add(new Obstacle((this.height - 120) / 2, (this.height - 120) / 2, this.width));
        Random rand = new Random();
        int index = rand.nextInt(obstacleList.size());

        if (GUI.frame != null) {

            if (obstacleList.get(index).getTopObstacleHeight() == 320) {
                frame.add(GUI.top_o1);
                frame.add(GUI.bottom_o1);
            } else if (obstacleList.get(index).getTopObstacleHeight() == 160) {
                frame.add(GUI.top_o2);
                frame.add(GUI.bottom_o2);
            } else {
                frame.add(GUI.top_o3);
                frame.add(GUI.bottom_o3);
            }
        }

        return obstacleList.get(index);
    }

    /**
     * Creates a new randomized reward.
     * @return Rewards
     */
    public Reward randomizeReward() {
        List<Reward> reward = new ArrayList<>();
        Random rand = new Random();
        reward.add(new Reward(this.width + 70, rand.nextInt(this.height - 20) + 5, true));
        reward.add(new Reward(this.width + 70, rand.nextInt(this.height - 20) + 5, false));
        int index = rand.nextInt(reward.size());
        return reward.get(index);
    }

    /**
     * Moves all objects on the game board (excluding player) left by 1 square.
     */
    public void moveObjects() {
        for (Obstacle o : obstacleList) {
            o.moveLeft();
        }
        for (Reward r : rewardsList) {
            r.moveLeft();
        }
        GUI.moveAllLeftVisual();

    }

    /**
     * Returns the current list of obstacles.
     * @return List</Obstacle>
     */
    public List<Obstacle> getObstacleList() {
        return obstacleList;
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


}







