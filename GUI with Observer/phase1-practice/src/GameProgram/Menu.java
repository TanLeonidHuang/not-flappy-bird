package GameProgram;

import The_GUI.GUI;
import Login.AdminManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {

    public void mainMenu(Login.UserAccount user, boolean admin) {
        PlayGame play = new PlayGame(user);
        Scanner myObj = new Scanner(System.in);
        int line;
        System.out.println("Welcome to our Game!");

        while (play.isRunning()) {
            System.out.println("Please type the number of the corresponding menu option you'd like:");
            System.out.println("1. PLAY GAME");
            System.out.println("2. LEADERBOARD");
            System.out.println("3. HOW TO PLAY");
            System.out.println("4. EXIT");
            if (admin){
                System.out.println("5. ADMIN FUNCTIONS");
            }

            String userInput = myObj.nextLine();
            line = play.selectOption(userInput);

            if (line == 1) {
                System.out.println("BEGIN GAME");
                play.runGame();
                Countdown.main(new String[]{"go"});
            } else if (line == 2) {
                System.out.println(play.displayLeaderboard());
            } else if (line == 3) {
                System.out.println("Use UP and DOWN arrow keys to move the bird and avoid obstacles. Collect golden apples " +
                        "to increase your score. Avoid poison apples or the game will end!");
            } else if (admin && line == 5) {
                AdminManager adminAction = new AdminManager();
                System.out.println("P - PROMOTE ADMIN USER");
                System.out.println("B - TEMPORARILY BAN USER");
                System.out.println("U - UNBAN ACCOUNT");
                System.out.println("D - DELETE USER");
                System.out.println("C - CREATE NEW ADMIN");
                Scanner sc = new Scanner(System.in);
                String action = sc.nextLine();
                System.out.println("Enter User Name:");
                String username = sc.nextLine();
                switch (action) {
                    case "P": adminAction.promoteAdminUser(username);
                    case "B": {
                        System.out.println("Enter Year, Month, Day, Hour, Minute");
                        Scanner sc2 = new Scanner(System.in);
                        int year = sc2.nextInt();
                        int month = sc2.nextInt();
                        int day = sc2.nextInt();
                        int hour = sc2.nextInt();
                        int minute = sc2.nextInt();
                        adminAction.temporaryBan(username, LocalDateTime.of(year, month, day, hour, minute));
                    }
                    case "U": {
                        System.out.println("Enter Year, Month, Day, Hour, Minute");
                        Scanner sc3 = new Scanner(System.in);
                        int year2 = sc3.nextInt();
                        int month2 = sc3.nextInt();
                        int day2 = sc3.nextInt();
                        int hour2 = sc3.nextInt();
                        int minute2 = sc3.nextInt();
                        adminAction.unbanAccount(username, LocalDateTime.of(year2, month2, day2, hour2, minute2));
                    }
                    case "D": adminAction.deleteUser(username);
                    case "C": {
                        System.out.println("Enter Password");
                        Scanner sc4 = new Scanner(System.in);
                        String password = sc4.nextLine();
                        adminAction.createNewAdmin(username, password);
                    }
                }


            }

            else play.stopRunning();
        }
}
}
