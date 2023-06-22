package Login;

public class LoginOld implements LoginPathway{
    @Override
    public void processInfo(AccountSystem system, LoginInfo info) {
        system.logIn(info.getUsername(), info.getPassword());
    }
}
