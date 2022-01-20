package TouchObjects;

import Utils.Touchpad;
import Utils.Bounds.Bounds;
import Utils.Bounds.RectBounds;
import Utils.Point;
import Utils.Scale;

//IMPORTANT PLEASE DONT SET VALUE TO 0 AUTOMATICALLY IN THIS CLASS THATS WHAT SNAPBACK IS FOR!
public class Slider extends ScaledTouchObject<Double> {

    public enum SliderType {
        X_AXIS,
        Y_AXIS
    }

    protected final SliderType sliderType;
    protected final Bounds bounds;

    public Slider(Touchpad touchpad, double defaultValue, SliderType sliderType, Bounds bounds,
                  double lowerOut, double upperOut) {
        super(touchpad, defaultValue, new Scale(
                sliderType == SliderType.X_AXIS ? bounds.getMinX() : bounds.getMinY(),
                sliderType == SliderType.X_AXIS ? bounds.getMaxX() : bounds.getMaxY(),
                lowerOut, upperOut
        ));
        this.sliderType = sliderType;
        this.bounds = bounds;
    }

    public Slider(Touchpad touchpad, SliderType sliderType, Bounds bounds,
                  double lowerOut, double upperOut) {
        super(touchpad, 0d, new Scale(
                sliderType == SliderType.X_AXIS ? bounds.getMinX() : bounds.getMinY(),
                sliderType == SliderType.X_AXIS ? bounds.getMaxX() : bounds.getMaxY(),
                lowerOut, upperOut
        ));
        this.sliderType = sliderType;
        this.bounds = bounds;
    }

    public Slider(Touchpad touchpad, double defaultValue, SliderType sliderType,
                  double lowerOut, double upperOut) {
        this(touchpad, defaultValue, sliderType, new RectBounds(), lowerOut, upperOut);
    }

    public Slider(Touchpad touchpad, SliderType sliderType,
                  double lowerOut, double upperOut) {
        this(touchpad, 0d, sliderType, new RectBounds(), lowerOut, upperOut);
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
                return;
            case Y_AXIS:
                if (touchpad.getNumFingers() == 2 && bounds.contains(fingerTwo))
                    value = scale(fingerTwo.getY());
                else if (touchpad.getNumFingers() == 1 && bounds.contains(fingerOne))
                    value = scale(fingerOne.getY());
                return;
            default:
                value = 0d;
        }
    }
}
