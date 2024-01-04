package sensors;

import TI.BoeBot;
import TI.PinMode;
import TI.Timer;
import head.Updateable;

public class LineDetector implements Updateable {
    private LineDetectorCallback lineDetectorCallback;
    Timer timer = new Timer(100);
    int adcPin;
    public LineDetector(int adcPin, LineDetectorCallback lineDetectorCallback) {
        this.adcPin = adcPin;
        this.lineDetectorCallback = lineDetectorCallback;
        BoeBot.setMode(adcPin, PinMode.Input);
    }

    /**
     * checkForLine
     * @return whether the line is black
     * reads the adcPin of this sensor (giving a value of 0 or higher)
     * and returns true if the value isn't 0 (which means there's a black line detected) and isn't 2783 (which means something is wrong with the sensor)
     */
    public boolean checkForLine(){
        int value = BoeBot.analogRead(this.adcPin);
        if(value > 150 && value != 2783){
            return true;
        }
        return false;
    }

    /**
     * getTestData
     * @author Joshua Roovers
     * @return the read value of the given adcPin (which should be giving a signal from a lineDetector
     * returns the read value of a lineDetector for testing purposes
     */
    public int getTestData(){
        return BoeBot.analogRead(this.adcPin);
    }


    @Override
    public void update() {
        if (!timer.timeout()){
            return;
        }
            //System.out.println(adcPin + ": " + BoeBot.analogRead(adcPin));
            lineDetectorCallback.onLine(this);
    }
}
