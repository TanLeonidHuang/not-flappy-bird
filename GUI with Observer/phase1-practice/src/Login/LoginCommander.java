package Login;

import java.util.ArrayList;

public class LoginCommander {
    private final AccountSystem system;
    private String identity;
    private LoginInfo info = new LoginInfo();
    private LoginPathway pathway;

    public LoginCommander() {
        this.system = new AccountSystem();
        this.identity = null;
    }

    public void inputStore(String userInput ) {
        //store information from Login main
        if (identity == null) {
            identity = userInput;
        } else {identity += ("." + userInput); }
    }

    public void start(String userInput) {
        if (userInput.equals("NEW")) {
            pathway = new LoginCreate();
        } else { pathway = new LoginOld(); }
    }

    public void accountLogin() {
        pathway.processInfo(system, info);
    }

    public void infoSet() {
        info.menuInputs(info.identitySplitter(identity));
    }

    public String getIdentity() {
        return identity;
    }
}


