package TouchObjects;

import Utils.Touchpad;
import Utils.Scale;

public abstract class ScaledTouchObject<T> extends TouchObject<T> {

    private final Scale scale;

    public ScaledTouchObject(Touchpad touchpad, T defaultValue, Scale scale) {
        super(touchpad, defaultValue);
        this.scale = scale;
    }

    public ScaledTouchObject(Touchpad touchpad, T defaultValue) {
        this(touchpad, defaultValue, new Scale(0, 1, 0, 1));
    }

    public double scale(double input) {
        return scale.output(input);
    }
}
