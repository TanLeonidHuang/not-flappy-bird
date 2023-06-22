package The_GUI;

import GameProgram.GamePlayer;
import GameProgram.Listener;
import GameProgram.Obstacle;
import GameProgram.PlayGame;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GameManagerObserver implements PropertyChangeListener {

    private GUI userInter;
    private PlayGame play;

    public GameManagerObserver(PlayGame playGame) {
        this.play = playGame;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("gameEnd")) {
            if (evt.getNewValue().equals("run")) {
                startGUI();
            } else if (evt.getNewValue().equals("stop")) {
                userInter.close();
            }
        } else if (evt.getPropertyName().equals("player")) {
            movePlayer(evt.getNewValue(), evt.getOldValue());
        }
    }

    public void startGUI() {
        GamePlayer p = play.getManager().getPlayer();
        Rectangle player = new Rectangle(p.getXCoordinate(), p.getYCoordinate(), p.getWidth(), p.getHeight());
        Listener myListener = new Listener(play.getManager());
        this.userInter = new GUI(player, myListener);
        userInter.frameSetup();
        userInter.frame.add(userInter);
        userInter.frameVisible();
        List<Obstacle> obstacles = play.getObstacleList();
        for (Obstacle obs : obstacles) {
            userInter.addObstacle(new Rectangle(obs.getLocation(), 600 - obs.getBottomObstacleHeight(),
                    obs.getWidth(), obs.getBottomObstacleHeight()));
            userInter.addObstacle(new Rectangle(obs.getLocation(), 0,
                    obs.getWidth(), obs.getTopObstacleHeight()));
        }
    }

    public void movePlayer(Object newValue, Object oldValue) {
        int newV = Integer.parseInt(newValue.toString());
        int oldV = Integer.parseInt(oldValue.toString());
        userInter.moveGamePlayer(newV - oldV);
    }

    public GUI getGUI() { return userInter; }
}
