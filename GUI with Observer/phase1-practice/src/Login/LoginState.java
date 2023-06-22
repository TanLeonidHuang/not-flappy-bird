package Login;

public class LoginState {
    private boolean running;
    private LoginLines loginLines;

    public LoginState() {
        this.running = true;
        this.loginLines = LoginLines.START;
    }

    public void stopRunning() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public LoginLines getLoginLinesState() {
        return loginLines;
    }

    public void determineLoginLinesState() {

    }
}
