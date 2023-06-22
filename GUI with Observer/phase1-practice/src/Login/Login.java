package Login;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Login {

    public Login() {
    }

    public static void main(String[] args) {
        LoginCommander r = new LoginCommander();
        LoginState s = new LoginState();
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        r.start(userInput);
        while(s.isRunning()) {
            userInput = in.nextLine();
            try {
                r.inputStore(userInput);
                System.out.println(r.getIdentity());
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }


}

