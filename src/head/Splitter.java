package head;

import actuators.Claw;
import actuators.Motor;
import sensors.LineDetector;

import java.util.ArrayList;
import java.util.Objects;

public class Splitter {

    private  String splice;
    private ArrayList<String> split;
    private String control;
    private MotorHelper motorHelper;
    private Claw claw;
    private int step;
    private LineDetector lineDetector;
    public Splitter(MotorHelper motorHelper){
        this.motorHelper = motorHelper;
    }

    public void setSplice(String splice) {
        this.splice = splice;
        step =0;
    }

    //    public void returning(String command){
//            switch(control){
//            case "r":
//                motorHelper.turn_left();
//                break;
//            case "l":
//                motorHelper.turn_right();
//                break;
//            case "v":
//                motorHelper.forwards();
//                break;
//            case "n":
//                motorHelper.hardStop();
//                break;
//            case "e":
//                motorHelper.stop();
//                break;
//            case "o":
////                claw.open();
//                break;
//            case "c":
////                claw.close();
//                break;
//        }
//        }
//    }
    /**
     * Splitter
     * @author Morris Woestenburg
     * splits the command from the GUI into individual characters that call on the motor methods
     */
    public void splitter(){
        if (step >= splice.length()){System.out.println("no commands left");}
        else {
            control = String.valueOf(splice.charAt(step));
            decoder(control);
            step++;
        }
    }
    private void decoder(String control){
//        if (splice.isEmpty()){return;}
        switch(control){
            case "l":
                motorHelper.turn_left();
                break;
            case "r":
                motorHelper.turn_right();
                break;
            case "v":
                motorHelper.forwards();
                break;
            case "n":
                motorHelper.hardStop();
                break;
            case "e":
                motorHelper.stop();
                break;
            case "o":
//                claw.open();
                break;
            case "c":
//                claw.close();
                break;
        }
//            if (Objects.equals(control, "b") && String.valueOf(splice.charAt(splice.length() - 1)).equals("b") && (control + 1).isEmpty()){returning(splice);}
//            else{continue;}
    }

}

