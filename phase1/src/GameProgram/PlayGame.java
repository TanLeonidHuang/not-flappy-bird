package GameProgram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

public class PlayGame implements KeyListener{

    private boolean up, down;
    GameManager manager;
    GameBoard board = new GameBoard();
    private boolean running;

    /**
     * Creates new GameProgram.PlayGame object.
     */
    public PlayGame(Login.UserAccount user) {
        running = true;
        this.manager = new GameManager(user);
    }

    /**
     * Sets the integer value of the output to regulate user input on the menu options
     * @param input the user input to prompt
     * @return int
     */
    public int selectOption(String input){
        int output;
        if (input.equals("1")){
            output = 1;
        }
        else if (input.equals("2")) {
            output = 2;
        }
        else if (input.equals("3")) {
            output = 3;
        }
        else output = 4;
        return output;
    }

    /**
     * Returns whether the program is still running
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
     * @return Map<String, Integer>
     */
    public Map<String, Integer> displayLeaderboard(){
        return Leaderboard.getScoreMap();
    }

    /**
     * Runs the game program by calling GameManager methods to accomplish the tasks
     */
    public void runGame(){
        manager.startGame();
        while(manager.runGame()) {
            if (up) {
                manager.moveUp();
            } else if (down) {
                manager.moveDown();
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Manages up and down keystrokes by the user to move the player object
     * @param e given key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            manager.moveUp();
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            manager.moveDown();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public List<Obstacle> getObstacleList() { return board.getObstacleList(); }

    public List<Reward> getRewardList() { return board.getRewardsList(); }

    public GameManager getManager() { return this.manager; }
}
