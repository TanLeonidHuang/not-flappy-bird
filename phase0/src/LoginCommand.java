import java.io.FileWriter;
import java.io.IOException;
public class LoginCommand {

    private final AccountSystem system; //Account System
    private final ValidateCredentials cred;
    private static int lineNumber; //A count to print different lines
    private boolean running;
    private String newUsername;
    private String newPassword;
    private boolean admin;
    private String username;
    private String password;

    public LoginCommand() {
        lineNumber = 0;
        this.system = new AccountSystem();
        this.cred = new ValidateCredentials();
        this.running = true;
        this.newUsername = null;
        this.newPassword = null;
        this.admin = false;
        this.username = null;
        this.password = null;

    }

    public int inputCheck(String input) {
        int output = 0;
        if (lineNumber == 0) {
            if (input.equals("OLD")) {
                resetInfo();
                output = 4;
                lineNumber = 3;
            }
            else if (input.equals("EXIT")) {stopRunning(); }
            else { output = 1;}
        }
        else if ((lineNumber == 1) || (lineNumber == 2)) {
            if (!checkValid(input, lineNumber)) {
                output = 8;
                lineNumber = -1;
            } else { if (lineNumber == 1) {output = 2;} else{output = 3;} }
        }
        else if (lineNumber == 3) {
            adminCheck(input);
            output = 4;
        }
        else if (lineNumber == 4) {
            username = input;
            output = 5;
        }
        else if (lineNumber == 5) {
            password = input;
            if (login(username, password)) {
                output = 6;

            } else {output = 8;}
            lineNumber = -1;
        }
        lineNumber += 1;
        return output;
    }


    public boolean login(String username, String password) {
        if (!system.checkUsername(username)) {
            lineNumber -= 1;
            return false;
        }
        else if (!system.checkPassword(username, password)) {
            lineNumber -= 1;
            return false;
        }
        return true;
    }
    public boolean checkValid(String input, int number) {
        if (number == 1) {
            if (cred.isValidUsername(input) & !system.checkUsername(input)) {
                newUsername = input;
                return true;
            }
            else {lineNumber -= 1; return false;}
        }
        else{
            if (cred.isValidPassword(input)) {
                newPassword = input;
                writeTo(newUsername, newPassword);
                return true;
            }
            else {lineNumber -= 1; return false;}
        }
    }

    public void adminCheck(String input) {
        if (input.equals("ADMIN")) {
            admin = true;
        }
        system.createUser(newUsername, newPassword, admin);
    }

    public void writeTo(String username, String password) {
        try {
            FileWriter writer = new FileWriter("userdata.txt", true);
            writer.write(username);
            writer.write("\r\n");
            writer.write(password);
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean isRunning() {
        return running;
    }

    public void stopRunning() {
        running = false;
    }

    public AccountSystem getAccountSystem() {
        return system;
    }
    public String getLoginUsername() {return username;}

    public String getLoginPassword() {return password;}

    public void resetInfo() {username = null; password = null;}
}
