package com.enforcers7149.touchpadplusplus.src.TouchObjects;

import com.enforcers7149.touchpadplusplus.src.Touchpad;
import com.enforcers7149.touchpadplusplus.src.Utils.Updateable;


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
