package head;

import TI.BoeBot;
import TI.PinMode;
import actuators.Motor;
import sensors.Button;
import sensors.ButtonCallback;
import sensors.Ultrasonic;
import sensors.UltrasonicCallback;

import java.util.ArrayList;

public class Controller implements Updateable, ButtonCallback, UltrasonicCallback {

    public Boolean isRunning;
    private Zoomer zoomer;
    private Ultrasonic ultrasone;
    public EmergencyStop emergencyStop;
    private Button testButton;
    private Button testButton2;

    private Motor leftMotor;
    private Motor rightMotor;
    private MotorHelper motorAansturen;
    private ArrayList<Updateable> updatables;
    public Controller() {
        BoeBot.setMode(1, PinMode.Input);
        this.isRunning = true;

        //this.zoomer = new Zoomer(10, 11);
        this.emergencyStop = new EmergencyStop(5);
    };


    public void startUp() {
        this.isRunning = true;
    }

    public void init(){
        updatables  = new ArrayList<>();

        updatables.add(this.leftMotor = new Motor(12,15));
        updatables.add(this.rightMotor = new Motor(13,15));
        updatables.add(ultrasone = new Ultrasonic(10,11, this));
        updatables.add(zoomer = new Zoomer(8));
        updatables.add(this.testButton = new Button(this,0));
        updatables.add(this.testButton2 = new Button(this,1));

        motorAansturen = new MotorHelper(leftMotor,rightMotor);
    }

    public void update() {

//        if (this.emergencyStop.check()){
////            motorAansturen.stop();
//            this.isRunning = false;
//            return;
//        }

        for (Updateable updatable : updatables)
            updatable.update();
        BoeBot.wait(1);


    }

    public Boolean getRunning() {
        return isRunning;
    }


    public void setRunning(boolean b) {
        this.isRunning = b;
    }

    @Override
    public void onButton(Button whichButton) {

        if(whichButton == testButton){
            //System.out.println("test 0 button pressed!");
            motorAansturen.forwards();
        }
        else if(whichButton == testButton2){
            //System.out.println("test 1 button pressed!");
            motorAansturen.hardStop();
        }

//        switch (whichButton){
//            case testButton:
//                System.out.println();
//            case testButton2:
//            default:
//                System.out.println("");;
//        }

//        leftMotor.setSpeed(100);
//        rightMotor.setSpeed(100);
    }



    /**
     * @author Stijn de vos
     * @since 04-12-2023
     * @param distance
     * this code should check if the distance that you are from an object is not to close.
     * the closer you are the more it checks how close you are.
     * if you get to close the buzzer wil start giving of a siren noise.
     */
    @Override
    public void onUltrasonic(double distance) {
        System.out.println("Ultrasone distance: " + distance);
        if(distance >= 30)
        {
            System.out.println("you are far enough");
            zoomer.setClose(false);
        }
        else if (distance >= 20 && distance < 30) {
            System.out.println("you are getting closer");
            ultrasone.setTimer(10);
            zoomer.setClose(false);
        }
        else if (distance >= 10 && distance < 20){
            System.out.println("very close");
            ultrasone.setTimer(15);
            zoomer.setClose(true);
        }
        else{
            System.out.println("way to close");
            zoomer.setClose(true);
        }
    }
}




//    public static void main(String[] args) {
//        head.Zoomer zoomer = new head.Zoomer(12, 14);
//        head.EmergencyStop emergencyStop = new head.EmergencyStop(0);
//
//        while (true) {
//
//            //zoomer.update(12);
//            zoomer.update(14);
//            if(emergencyStop.check()){
//                //check for emergency stop press, stop the wheels and break the loop
//                head.MotorAansturen.stop();
//                break;
//            }
//            BoeBot.wait(1);
//        }