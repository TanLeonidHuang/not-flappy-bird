import GameProgram.PlayGame;
import Login.UserAccount;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlayGameTest {

    @Test
    public void testIsRunning() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        PlayGame play = new PlayGame(user);
        assertTrue(play.isRunning());
    }

    @Test
    public void testStopRunning() {
        UserAccount user = new UserAccount("Jane", "12345678", false);
        PlayGame play = new PlayGame(user);
        play.stopRunning();
        assertFalse(play.isRunning());
    }
}
