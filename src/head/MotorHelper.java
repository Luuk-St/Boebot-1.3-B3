package head;

import actuators.Motor;

public class MotorHelper {
    private Motor s1;
    private Motor s2;

    public MotorHelper(Motor s1, Motor s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

//    public void Wheels(int Wheel1, int Wheel2){
//        this.s1.setSpeed(Wheel1);
//        this.s2.setSpeed(Wheel2);
//    }
    public void Wheels(int Wheel1, int Wheel2, boolean gradualIncrement){
        this.s1.setGradualIncrement(gradualIncrement);
        this.s2.setGradualIncrement(gradualIncrement);
        this.s1.setSpeed(Wheel1);
        this.s2.setSpeed(Wheel2);
    }
    public void turn_right(){
        Wheels(1400,1525, false);
    }
    public void turn_left(){
        Wheels(1475,1600, false);
    }
    public void backwards(){

        Wheels(1600,1400, false);
    }
    public void forwards(){
        //System.out.println("Wheels forwards");
        Wheels(1400,1600, true);
    }
    public void stop(){
        //System.out.println("stop");
        Wheels(1500,1500, true);
    }

    public void hardStop(){
        //System.out.println("Hard stop");
        this.s1.hardStop();
        this.s2.hardStop();
    }
}