import GameProgram.Leaderboard;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LeaderboardTest {

    @Test(timeout = 50)
    public void testAddNewScore() {
        Leaderboard scoreBoard = new Leaderboard();
        scoreBoard.addNewScore("Mary", 10);
        scoreBoard.addNewScore("Judy", 12);
        assertTrue(Leaderboard.getScoreMap().containsKey("Mary"));
        assert(Leaderboard.getScoreMap().get("Judy") == 12);
    }

    @Test(timeout = 50)
    public void testUpdateExistingScore() {
        Leaderboard scoreBoard = new Leaderboard();
        scoreBoard.addNewScore("Mary", 10);
        scoreBoard.updateExistingScore("Mary", 16);
        assert(Leaderboard.getScoreMap().get("Mary") == 16);
    }

    @Test(timeout = 50)
    public void testToString() {
        Leaderboard scoreBoard = new Leaderboard();
        scoreBoard.addNewScore("Mary", 10);
        scoreBoard.addNewScore("Judy", 12);
        scoreBoard.addNewScore("John", 28);
        scoreBoard.addNewScore("July", 19);
        System.out.println(scoreBoard);
    }
}

