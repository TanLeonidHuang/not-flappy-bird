package Login;

import java.util.*;
import Login.LoginInfo.*;

public class LoginStart {
    private LoginInfo info;
    public LoginStart() {
        this.info = new LoginInfo();
    }

    public void startUp(ArrayList<String> identityList, AccountSystem system) {
        for (String identity : identityList) {
            info.menuInputs(info.identitySplitter(identity));
            system.createUser(info.getUsername(), info.getPassword(), info.getAdmin());
        }
    }
}
