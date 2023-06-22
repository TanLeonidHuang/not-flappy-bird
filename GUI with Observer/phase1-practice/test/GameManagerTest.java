import GameProgram.*;
import Login.UserAccount;
import org.junit.Test;


import static org.junit.Assert.assertFalse;

public class GameManagerTest {

    @Test(timeout = 150)
    public void testInitializer() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        assert(manager.getScore() == 0);
        assert(manager.getPlayer().getUsername().equals("Jane"));
    }

    @Test(timeout = 50)
    public void testMoveUp() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.moveUp();
        assert(manager.getPlayer().getYCoordinate() == 301);
    }

    @Test(timeout = 50)
    public void testMoveDown() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.moveDown();
        assert(manager.getPlayer().getYCoordinate() == 299);
    }

    @Test
    public void testUpdateScore() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.startGame();
        assert(manager.getScore() == 0);
        try {
        Thread.sleep(5000); }
        catch(InterruptedException ex)
        {
            ex.printStackTrace(); }
        manager.updateScore();
        assert(manager.getScore() == 5);
    }

    @Test(timeout = 300)
    public void testRunGameTrue() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.startGame();
        manager.runGame();
        GameBoard grid = manager.getGameBoard();
        assert(grid.getObstacleList().get(0).getLocation() == 99);
    }

    @Test(timeout = 300)
    public void testRunGameFalse() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.getPlayer().setLocation(100, 220);
        manager.startGame();
        assertFalse(manager.runGame());
    }

    @Test
    public void testEndGame() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.startGame();
        try {
            Thread.sleep(5000); }
        catch(InterruptedException ex)
        {
            ex.printStackTrace(); }
        manager.endGame();
        assert(Leaderboard.getScoreMap().containsKey("Jane"));
    }

    @Test
    public void testGenerateRewardScore() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        GameManager manager = new GameManager(user);
        manager.startGame();
        manager.getGameBoard().addReward(new Reward(79, 299, false));
        manager.runGame();
        try {
            Thread.sleep(5000); }
        catch(InterruptedException ex)
        {
            ex.printStackTrace(); }
        manager.generateRewardScore();
        assert(manager.getScore() == 15);
    }


}
