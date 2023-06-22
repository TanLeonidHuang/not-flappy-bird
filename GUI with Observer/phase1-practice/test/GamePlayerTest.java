import GameProgram.GamePlayer;
import org.junit.Test;

public class GamePlayerTest {

    @Test(timeout = 300)
    public void testInitializer() {
        GamePlayer player = new GamePlayer("kid");
        assert (!player.isDead());
    }
}
