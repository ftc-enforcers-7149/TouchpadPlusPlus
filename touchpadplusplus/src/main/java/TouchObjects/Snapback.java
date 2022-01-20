package TouchObjects;


import Utils.Touchpad;
import Utils.Bounds.Bounds;
import Utils.Point;

public class Snapback extends Slider {

    public Snapback(Touchpad touchpad, double defaultValue, SliderType sliderType, Bounds bounds, double lowerOut, double upperOut) {
        super(touchpad, defaultValue, sliderType, bounds, lowerOut, upperOut);
    }

    public Snapback(Touchpad touchpad, double defaultValue, SliderType sliderType, double lowerOut, double upperOut) {
        super(touchpad, defaultValue, sliderType, lowerOut, upperOut);
    }

    @Override
    public void updateInput() {
        Point fingerOne = touchpad.getFingerOne();
        Point fingerTwo = touchpad.getFingerTwo();

        switch(sliderType) {
            case X_AXIS:
                if (touchpad.getNumFingers() == 2 && bounds.contains(fingerTwo))
                    value = scale(fingerTwo.getX());
                else if (touchpad.getNumFingers() == 1 && bounds.contains(fingerOne))
                    value = scale(fingerOne.getX());
                else
                    value = defaultValue;
                return;
            case Y_AXIS:
                if (bounds.contains(fingerTwo))
                    value = scale(fingerTwo.getY());
                else if (bounds.contains(fingerOne))
                    value = scale(fingerOne.getY());
                else
                    value = defaultValue;
                return;
            default:
                value = defaultValue;
        }
    }
}
