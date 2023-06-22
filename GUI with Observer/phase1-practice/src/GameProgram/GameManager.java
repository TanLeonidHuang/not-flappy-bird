package GameProgram;
import Login.UserAccount;
import The_GUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class GameManager{
    private final Stopwatch timer;

    private final List<Reward> totalGoldenApples = new ArrayList<>();

    private final GamePlayer player;

    private GameBoard grid = new GameBoard();
    private int score;

    /**
     * Creates new GameProgram.GameManager object. Stores a new GameProgram.GamePlayer object in an instance variable and assigns to it the
     * username of the current user.
     */
    public GameManager(UserAccount user) {
        player = new GamePlayer(user.getUsername());
        score = 0;
        timer = new Stopwatch();
    }

    /**
     * Moves the player upwards by 1 square.
     */
    public void moveUp(){
        Double xCoord = player.getLocation().get(0);
        Double yCoord = player.getLocation().get(1);
        double newY = yCoord + 1.0;
        player.setLocation(xCoord, newY);
        System.out.println("up");
    }

    /**
     * Moves the player downwards by 1 square.
     */
    public void moveDown(){
        Double xCoord = player.getLocation().get(0);
        Double yCoord = player.getLocation().get(1);
        double newY = yCoord - 1.0;
        player.setLocation(xCoord, newY);
        System.out.println("down");
    }

    /**
     * Starts a new game.
     */
    public void startGame() {
        this.grid = new GameBoard();
        timer.start();
    }

    /**
     * Runs the game and ends it when the player has hit an obstacle or the bottom of the game board. Calculates
     * the score and updates the leaderboard after the game ended.
     */
    public void runGame() {
        startGame();
        grid.moveObjects();
        grid.updateObstacles();
        grid.updateRewards();
    }

    /**
     * Checks whether the player object comes in contact with the bottom of the Gameboard, an obstacle or if it's
     * touching a reward and what its consequences will be.
     * @return boolean
     */
    public boolean isTouching() {
        Reward reward = grid.isTouchingReward(player);
        if(grid.isTouchingBottom(player) || grid.isTouchingObstacle(player)){
            endGame();
            System.out.println("touch");
            return true;
        } else if (reward != null) {
            if (reward.isBadReward()) {
                endGame();
                System.out.println("touch");
                return true;
            } else {
                this.totalGoldenApples.add(reward);
            }
        }
        System.out.println("good");
        return false;
    }

    /**
     * Ends the current game, calculates the score of the game and updates the leaderboard, if required.
     */
    public void endGame() {
        Leaderboard scoreBoard = new Leaderboard();
        timer.stop();
        generateRewardScore();
        if (!scoreBoard.updateExistingScore(player.getUsername(), score)){
            scoreBoard.addNewScore(player.getUsername(), score);
        } else {
            scoreBoard.updateExistingScore(player.getUsername(), score);
        }
    }

    /**
     * Updates the score variable while the game is running based on the time elapsed.
     */
    public void updateScore() {
        score = (int)timer.getElapsedSeconds();
    }

    /**
     * Calculates the score for the current game based on the time elapsed and the number of
     * Golden Apples collected. Stores it in an instance variable.
     */
    public void generateRewardScore(){
        score = (int)timer.getElapsedSeconds() + totalGoldenApples.size() * 10;
    }

    /**
     * Returns the score of the current game.
     * @return int score of the current game
     */
    public int getScore(){
        return score;
    }

    /**
     * Returns the player of the current game.
     * @return GameProgram.GamePlayer player of the current game
     */
    public GamePlayer getPlayer() { return player; }

    public GameBoard getGameBoard() { return this.grid; }

    public static void main(String[] args) {
        GameManager m = new GameManager(new UserAccount("12345", "12345678", false));
        m.runGame();
    }

}

