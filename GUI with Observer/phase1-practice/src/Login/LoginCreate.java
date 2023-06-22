package Login;

public class LoginCreate implements LoginPathway{
    @Override
    public void processInfo(AccountSystem system, LoginInfo info) {
        system.createUser(info.getUsername(), info.getPassword(), info.getAdmin());
    }
}
