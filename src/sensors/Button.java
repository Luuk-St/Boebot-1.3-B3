package sensors;

import TI.BoeBot;
import TI.PinMode;
import head.Updateable;

public class Button implements Updateable {

    private ButtonCallback callback;
    private int pin;

    public Button(ButtonCallback callback, int pin)
    {
        BoeBot.setMode(pin, PinMode.Input);
        this.pin = pin;
        this.callback = callback;
    }
    @Override
    public void update()
    {
        if(BoeBot.digitalRead(pin))
            return;
        callback.onButton(this);
    }

}
