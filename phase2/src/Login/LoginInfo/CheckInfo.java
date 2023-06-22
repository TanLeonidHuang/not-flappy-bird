package Login.LoginInfo;

import Login.AccountSystem;
import Login.ValidateCredentials;

public class CheckInfo {
    private final ValidateCredentials cred;
    private final AccountSystem system;

    public CheckInfo(AccountSystem mainSystem) {
        this.cred = new ValidateCredentials();
        this.system = mainSystem;
    }

    public boolean check(String username, String password) {
        if (checkValidUsername(username, password)) {
            return !checkUsed(username);
        } return false;
    }

    public boolean checkValidUsername(String username, String password) {
        if (cred.isValidUsername(username)) {
            return cred.isValidPasswordLength(password);
        } return false;
    }

    public boolean checkUsed(String username) {
        return system.checkUsername(username);
    }

}
