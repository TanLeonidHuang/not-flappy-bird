package GameProgram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import Login.UserAccount;
import The_GUI.GUI;

import javax.swing.*;

public class PlayGame extends JPanel {
    public static GameManager manager;
    private GamePlayer player;
    private boolean running;

    /**
     * Creates new GameProgram.PlayGame object.
     */
    public PlayGame(Login.UserAccount user) {
        running = true;
        manager = new GameManager(user);
        player = manager.getPlayer();

    }

    /**
     * Sets the integer value of the output to regulate user input on the menu options
     *
     * @param input the user input to prompt
     * @return int
     */
    public int selectOption(String input) {
        switch (input) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            default:
                return 4;
        }
    }

    /**
     * Returns whether the program is still running
     *
     * @return boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Stops the program from continuing running
     */
    public void stopRunning() {
        running = false;
    }

    /**
     * Displays the all-time leaderboard of scores with their respective usernames
     *
     * @return Map<String, Integer>
     */
    public Map<String, Integer> displayLeaderboard() {
        return Leaderboard.getScoreMap();
    }

    /**
     * Runs the game program by calling GameManager methods to accomplish the tasks
     */
    public void runGame() {
        manager.runGame();

    }

    /**
     * Returns the game board object.
     * @return GameBoard
     */
    public GameBoard getGameBoard() { return manager.getGameBoard(); }

    public GamePlayer getPlayer() { return player; }

    /**
     * Manages up and down keystrokes by the user to move the player object
     *
     */

    public GameBoard getBoard(){return board;}

    public static void main(String[] args) {
        PlayGame g = new PlayGame(new UserAccount("12345", "12345678", false));
        g.runGame();
    }
}
