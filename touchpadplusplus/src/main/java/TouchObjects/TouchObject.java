package TouchObjects;

import Utils.Touchpad;
import Utils.Updateable;


public abstract class TouchObject<T> implements Updateable {

    protected Touchpad touchpad;
    protected T value, defaultValue;

    public TouchObject(Touchpad touchpad, T defaultValue) {
        this.touchpad = touchpad;
        this.defaultValue = defaultValue;
        value = defaultValue;
    }

    @Override
    public abstract void updateInput();
    public final T get() {
        return value;
    }
}
