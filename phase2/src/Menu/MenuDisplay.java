package Menu;

public class MenuDisplay {

    public String display() {
        return ("Please type the number of the corresponding menu option you'd like:\n1. PLAY GAME\n2. LEADERBOARD\n3. HOW TO PLAY\n4. EXIT");
    }

    public String adminDisplay(boolean admin) {
        String output = null;
        if (admin) {
            output = "5. ADMIN FUNCTIONS";
        }
        return output;
    }
}
