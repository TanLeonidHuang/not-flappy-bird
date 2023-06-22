package Menu;

import GameProgram.LeaderboardDisplay;
import GameProgram.PlayGame;
import Login.UserAccount;
import The_GUI.GameBoardObserver;
import The_GUI.GameManagerObserver;
import The_GUI.MenuListener;
import The_GUI.MenuObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;

public class Menu {

    private static final Logger logger = (Logger) LogManager.getLogger(PlayGame.class);

    public void mainMenu(String user, boolean admin) {
        GameBoardObserver observer1 = new GameBoardObserver();
        GameManagerObserver observer2 = new GameManagerObserver(observer1);
        logger.info("New GameManagerObserver has been created");
        LeaderboardDisplay leaderboardDisplay = new LeaderboardDisplay();
        PlayGame play = new PlayGame(user, observer2, observer1, leaderboardDisplay);
        observer2.savePlayGame(play);
        MenuCommander commander = new MenuCommander(play, leaderboardDisplay);
        //MenuDisplay display = new MenuDisplay();
        //Scanner in = new Scanner(System.in);
        MenuListener listener = new MenuListener(commander);
        MenuObserver menu = new MenuObserver(listener, 300, 600);
        observer2.saveJFrame(menu.frame);
        while (play.isRunning()) {
            //System.out.println(display.display());
            //System.out.println(display.adminDisplay(admin));
            //String userInput = in.nextLine();
            //commander.next(commander.input(userInput));
            menu.frameSetup();
            menu.frameVisible();
            menu.frame.add(menu);
        }
    }
}
