package com.enforcers7149.touchpadplusplus.src;

import com.enforcers7149.touchpadplusplus.src.Utils.Touch;
import com.enforcers7149.touchpadplusplus.src.Utils.Updateable;

import com.qualcomm.robotcore.hardware.Gamepad;

import com.enforcers7149.touchpadplusplus.src.Utils.Point;

public class Touchpad implements Updateable {

    //TODO: fix angle handling

    // Gamepad for handling input
    private final Gamepad gamepad;

    // touchButton: touchpad "press"
    private boolean buttonPressed, lastButtonPressed, buttonClicked, buttonReleased;

    // number of fingers on the touchpad
    private int numFingers, lastNumFingers;
    private boolean touchClicked, touchReleased;

    // positional variables
    private final Point fingerOne, lastFingerOne, fingerTwo, lastFingerTwo;

    private long lastTime = 0;

    // how often the touchpad "updates"
    private static final double POLL_TIME = 200;

    // vector packets for storing velocity data
    private final Touch v1, v2, lastV1, lastV2;

    // standard unit multiplier
    public static final double COORD_MULT = 100;
    private static final double DEADZONE = 2;

    // Constructor. pass in the tele-op gamepad
    public Touchpad(Gamepad gamepad) {
        this.gamepad = gamepad;

        fingerOne = new Point(); lastFingerOne = new Point();
        fingerTwo = new Point(); lastFingerTwo = new Point();

        v1 = new Touch();
        v2 = new Touch();
        lastV1 = new Touch();
        lastV2 = new Touch();

    }

    // Update method
    @Override
    public void updateInput() {

        // Updates time

        // time variables
        long time = System.currentTimeMillis();

        // Checks if touchpad press
        buttonPressed = gamepad.touchpad;
        buttonClicked = buttonPressed && !lastButtonPressed;
        buttonReleased = !buttonPressed && lastButtonPressed;

        // Checks how many fingers are on the touchpad
        // Checks the position of fingers and updates
        numFingers = 0;
        if (gamepad.touchpad_finger_1) {
            numFingers++;

            double f1X = Math.round(10 * COORD_MULT * gamepad.touchpad_finger_1_x) / 10.0;
            double f1Y = Math.round(10 * COORD_MULT * gamepad.touchpad_finger_1_y) / 10.0;

            fingerOne.setPoint(f1X, f1Y);
        }
        if (gamepad.touchpad_finger_2) {
            numFingers++;

            double f2X = Math.round(10 * COORD_MULT * gamepad.touchpad_finger_2_x) / 10.0;
            double f2Y = Math.round(10 * COORD_MULT * gamepad.touchpad_finger_2_y) / 10.0;

            fingerTwo.setPoint(f2X, f2Y);
        }
        else if(!gamepad.touchpad_finger_1 && !gamepad.touchpad_finger_2)
            numFingers = 0;

        touchClicked = numFingers > lastNumFingers;
        touchReleased = numFingers < lastNumFingers;

        // If we've hit the poll time
        if(time - lastTime >= POLL_TIME) {
            // Updates the vectors of the fingers if applicable

            switch(numFingers) {
                case 1:
                    //TODO: This won't have good behavior
                    if(Math.abs(fingerOne.getX() - lastFingerOne.getX()) > DEADZONE
                            && Math.abs(fingerOne.getY() - lastFingerOne.getY()) > DEADZONE) {
                        // the reason it sets last first is so that it can be one loop behind.
                        lastV1.setTouch(v1);
                        v1.setTouch(fingerOne, lastFingerOne, time / 1000d, lastTime / 1000d);
                        v2.resetTouch();
                    }
                    break;
                case 2:
                    if(Math.abs(fingerOne.getX() - lastFingerOne.getX()) > DEADZONE
                            && Math.abs(fingerOne.getY() - lastFingerOne.getY()) > DEADZONE) {
                        lastV1.setTouch(v1);
                        v1.setTouch(fingerOne, lastFingerOne, time / 1000d, lastTime / 1000d);
                    }

                    if(Math.abs(fingerTwo.getX() - lastFingerTwo.getX()) > DEADZONE
                            && Math.abs(fingerTwo.getY() - lastFingerTwo.getY()) > DEADZONE) {
                        lastV2.setTouch(v2);
                        v2.setTouch(fingerTwo, lastFingerTwo, time / 1000d, lastTime / 1000d);
                    }
                    break;

                default:
                    v1.resetTouch();
                    v2.resetTouch();
                    break;
            }

            //Updates lasts
            lastTime = time;
        }

        if (numFingers >= 1) {
            lastFingerOne.setPoint(fingerOne);
        }
        if (numFingers == 2) {
            lastFingerTwo.setPoint(fingerTwo);
        }

        lastButtonPressed = buttonPressed;
        lastNumFingers = numFingers;
    }

    // rumble handling functions
    public void rumble(Gamepad.RumbleEffect rumbleEffect) { gamepad.runRumbleEffect(rumbleEffect); }
    public void rumble(int duration) {
        gamepad.rumble(duration);
    }
    public void rumble(double rumble1, double rumble2, int duration) { gamepad.rumble(rumble1, rumble2, duration); }
    public void rumbleBlips(int blips) {
        gamepad.rumbleBlips(blips);
    }
    public void startRumble() {
        gamepad.rumble(Gamepad.RUMBLE_DURATION_CONTINUOUS);
    }
    public void stopRumble() {
        gamepad.stopRumble();
    }

    // standard getter/setters
    public boolean isButtonPressed() { return buttonPressed; }
    public boolean isButtonClicked() { return buttonClicked; }
    public boolean isButtonReleased() { return buttonReleased; }
    public int getNumFingers() { return numFingers; }
    public boolean isTouchPressed() { return numFingers >= 1; }
    public boolean isTouchClicked() { return touchClicked; }
    public boolean isTouchReleased() { return touchReleased; }

    public Point getFingerOne() { return fingerOne; }
    public Point getLastFingerOne() { return lastFingerOne; }
    public Point getFingerTwo() { return fingerTwo; }
    public Point getLastFingerTwo() { return lastFingerTwo; }
    public boolean getFingerOn() { return numFingers >= 1; }

    // Returns the finger vector packets
    public Touch getV1() {
        return v1;
    }
    public Touch getV2() {
        return v2;
    }
    public Touch getLastV1() {
        return v1;
    }
    public Touch getLastV2() {
        return lastV2;
    }
}