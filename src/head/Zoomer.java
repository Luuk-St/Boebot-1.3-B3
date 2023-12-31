package head;

import TI.BoeBot;
import TI.PinMode;

public class Zoomer implements Updateable {

    private int buz1 = 8;
    private int led = 4;
    private int led2 = 5;
    private int button;
    private int button2;

//            BoeBot.setMode(button, PinMode.Input);
//            BoeBot.setMode(buz1, PinMode.Output);
//            BoeBot.setMode(led, PinMode.Output);

    private Boolean buttonstate;
    private Boolean state = true;
    private int A = 466;
    private int B = 494;
    private int C = 262;
    private int D = 294;
    private int E = 330;
    private int F = 349;
    private int G = 392;
    //Pin setup
        Zoomer(int button, int button2) {
            this.button = button;
            this.button2 = button2;
            BoeBot.setMode(this.button, PinMode.Input);
            BoeBot.setMode(this.button2, PinMode.Input);
            BoeBot.setMode(this.buz1, PinMode.Output);
            BoeBot.setMode(this.led, PinMode.Output);
            BoeBot.setMode(this.led2, PinMode.Output);
        }

        public void update() {
            if (BoeBot.digitalRead(this.button) || BoeBot.digitalRead(this.button2))
                return;
            buttonstate = !BoeBot.digitalRead(button);

            if (buttonstate){
                for (int i = 0; i < 5; i++){
                    state = !state;
                    BoeBot.digitalWrite(led, state);
                    BoeBot.digitalWrite(led2, !state);
                    BoeBot.wait(200);
                }

                BoeBot.freqOut(buz1, A, 200);
                BoeBot.wait(10);
                BoeBot.freqOut(buz1, B, 200);
                BoeBot.wait(50);
                BoeBot.freqOut(buz1, D, 100);
                BoeBot.wait(10);
                BoeBot.freqOut(buz1, B, 200);
                BoeBot.wait(50);
                BoeBot.freqOut(buz1, F, 100);
                BoeBot.wait(25);
                BoeBot.freqOut(buz1, F, 100);
                BoeBot.wait(25);
                BoeBot.freqOut(buz1, E, 200);
            }

            BoeBot.wait(500);
        }
    }
