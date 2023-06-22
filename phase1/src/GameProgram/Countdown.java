package GameProgram;
// https://www.delftstack.com/howto/java/countdown-timer-java/ source to help count the timer just for phase 1

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Countdown {
    public static void main(String[] args) {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 0;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter++;

                if (countdownStarter >= 10) {
                    System.out.println("Game Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}
