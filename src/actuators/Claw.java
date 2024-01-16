package actuators;

import TI.BoeBot;
import TI.Servo;
import TI.Timer;
import head.Updateable;

public class Claw implements Updateable {
    private int pin;
    private int currentSpeed;
    private int targetSpeed;
    private int speedStep;
    private Timer timer;
    private Servo s3;
    public Claw(int pin, int speedStep){
        this.speedStep = speedStep;
        this.pin = pin;
        this.s3 = new Servo(pin);
        this.timer = new Timer(100);
        this.currentSpeed = 1800;
    }
    public void open(){
        this.targetSpeed = 1800;
    }

    public void close(){
        this.targetSpeed = 825;
    }

    /**
     * @author morris Woestenburg
     * slowly sets the value of the claw to the needed value.
     */
    @Override
    public void update() {

        if(currentSpeed == targetSpeed)
            return;

        if(timer.timeout())
            return;

        int speedDifference = targetSpeed - currentSpeed;
        if(speedDifference > speedStep)
            speedDifference = speedStep;
        else if (speedDifference < -speedStep)
            speedDifference = -speedStep;

        currentSpeed += speedDifference;

        s3.update(this.targetSpeed);


        System.out.println("Current speed "+ this.pin +": "+ currentSpeed);
//        s3.update(1800);
//        BoeBot.wait(1000);
//        s3.update(825);
//        BoeBot.wait(1000);
    }
}
