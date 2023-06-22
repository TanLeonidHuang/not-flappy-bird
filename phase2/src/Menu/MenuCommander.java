package Menu;

import GameProgram.AdminMenu;
import GameProgram.LBFileHandler;
import GameProgram.LeaderboardDisplay;
import GameProgram.PlayGame;
import The_GUI.GUI;
import The_GUI.GameBoardObserver;
import The_GUI.GameManagerObserver;
import The_GUI.MenuObserver;

public class MenuCommander {
    private final PlayGame play;

    private final LeaderboardDisplay display;

    public MenuCommander(PlayGame mainPlay, LeaderboardDisplay display) {
        this.play = mainPlay;
        this.display = display;
    }
    public int inputCheck(String userInput) {
        int output;
        System.out.println("input check running");
        System.out.println(userInput);
        switch (userInput) {
            case "1":
                output = 1;
                break;
            case "2":
                output = 2;
                break;
            case "3":
                output = 3;
                break;
            default:
                output = 4;
                break;
        }
        return output;
    }

    public void next(int option) {
        System.out.println("choosing action");
        System.out.println(option);
        if (option == 1) {
            System.out.println("starting game");
            play.startGame();
            play.runGame();
        } else if (option == 2) {
            System.out.println(display.getLeaderboard());
        } else if (option == 3) {
            System.out.println("Use UP and DOWN arrow keys to move the bird and avoid obstacles. Collect golden apples " +
                    "to increase your score. Avoid poison apples or the game will end!");
        }
        else  {play.stopRunning();}
    }

}
