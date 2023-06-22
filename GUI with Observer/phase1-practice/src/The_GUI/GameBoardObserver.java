package The_GUI;

import GameProgram.Obstacle;
import GameProgram.Reward;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameBoardObserver implements PropertyChangeListener {

    private final GUI userInter;

    public GameBoardObserver(GUI g) {
        userInter = g;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        if (name.equals("move")) {
            userInter.moveAllLeft();
        } else if (name.equals("reward")) {
            if (evt.getNewValue().equals("null")) {
                removeFirstReward(evt.getOldValue());
            } else if (evt.getNewValue().equals("collected")) {
                removeReward(evt.getOldValue());
            } else {
                addReward(evt.getNewValue());
            }
        } else if (name.equals("obstacle")) {
            if (evt.getNewValue().equals("null")) {
                userInter.removeObstacle();
            } else {
                addObstacle(evt.getNewValue());
            }
        }
    }

    public void removeReward(Object reward) {
        Reward r = (Reward) reward;
        if (!r.isBadReward()) {
            int xPos = r.getXCoordinate();
            int yPos = r.getYCoordinate();
            userInter.removeGoldenApple(xPos, yPos);
        }

    }
    public void removeFirstReward(Object r) {
        Reward reward = (Reward) r;
        if (reward.isBadReward()) {
            userInter.removeFirstPoisonApple();
        } else {
            userInter.removeFirstGoldenApple();
        }
    }

    public void addReward(Object r) {
        Reward reward = (Reward) r;
        Rectangle newReward = new Rectangle(reward.getXCoordinate(), reward.getYCoordinate(),
                reward.getWidth(), reward.getHeight());
        if (reward.isBadReward()) {
            userInter.addPoisonApple(newReward);
        } else {
            userInter.addGoldenApple(newReward);
        }
    }

    public void addObstacle(Object obstacle) {
        Obstacle o = (Obstacle) obstacle;
        userInter.addObstacle(new Rectangle(o.getLocation(), 600 - o.getBottomObstacleHeight(),
                o.getWidth(), o.getBottomObstacleHeight()));
        userInter.addObstacle(new Rectangle(o.getLocation(), 0,
                o.getWidth(), o.getTopObstacleHeight()));
    }

}
