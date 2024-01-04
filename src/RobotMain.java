import TI.BoeBot;
import TI.Timer;
import head.Controller;
import head.EmergencyStop;

public class RobotMain {
    static Controller engine = new head.Controller();
    static boolean state = true;
    static Timer EmergencyTimer = new Timer(400);

    public static void main(String[] args) {
        engine.init();
        while (true) {
            while (!engine.getRunning()) {
                if (!BoeBot.digitalRead(1))
                    engine.startUp();
                if (EmergencyTimer.timeout())
                    state = !state;
                BoeBot.digitalWrite(5, state);
                BoeBot.digitalWrite(4, !state);
            }
            if (engine.emergencyStop.check())
                engine.setRunning(false);
            engine.update();
            BoeBot.wait(1);
        }
    }
}
