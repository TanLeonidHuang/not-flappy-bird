package Login;

public class LoginState {

    private boolean running;

    public LoginState() {
        this.running = true;
    }

    public void stopRunning() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

}

