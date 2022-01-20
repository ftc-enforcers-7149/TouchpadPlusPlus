package com.example.touchpadlib7149;

import TouchObjects.Button;
import TouchObjects.Slider;
import TouchObjects.Snapback;
import TouchObjects.Swipe;
import Utils.Touchpad;
import Utils.Bounds.PolygonBounds;
import Utils.Bounds.RectBounds;
import Utils.Point;
import Utils.TouchpadHandler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(group = "7149 Utils.Touchpad++ Samples", name = "Utils.Touchpad Test")
public class TouchpadTest extends OpMode {

    Touchpad touchpad;
    TouchpadHandler handler = new TouchpadHandler();
    Button topRight, bottomLeft;
    Button polyButton;
    Slider liftPos;
    Snapback slidePos;
    Swipe rotateLeft, rotateRight;

    public void init() {

        PolygonBounds polygon = new PolygonBounds(new Point(-100, 100), new Point(-100, 0),
                new Point(0, 50), new Point(100, 0), new Point(100, -100));

        touchpad = new Touchpad(gamepad1);
        topRight = new Button(touchpad, false, RectBounds.TOP_RIGHT, false);
        bottomLeft = new Button(touchpad, false, RectBounds.BOTTOM_LEFT, true);
        polyButton = new Button(touchpad, false, polygon, true);
        liftPos = new Slider(touchpad, 0, Slider.SliderType.Y_AXIS, 0, 20);
        rotateLeft = new Swipe(touchpad, false, Swipe.SwipeType.LEFT_SWIPE);
        rotateRight = new Swipe(touchpad, false, Swipe.SwipeType.RIGHT_SWIPE);
        slidePos = new Snapback(touchpad, 0, Slider.SliderType.X_AXIS, 0, 20);

        handler.addInputs(touchpad, topRight, bottomLeft, liftPos, rotateLeft, rotateRight, polyButton,
                slidePos);
    }

    public void loop() {

        handler.updateInputs();

        telemetry.addData("Number of fingers: ", touchpad.getNumFingers());

        if(touchpad.getNumFingers() >= 1)
            telemetry.addData("Finger 1: ", touchpad.getFingerOne());
        else
            telemetry.addLine();

        if(touchpad.getNumFingers() == 2)
            telemetry.addData("Finger 2: ", touchpad.getFingerTwo());
        else
            telemetry.addLine();

        telemetry.addLine();

        telemetry.addData("rotateLeft: ", rotateLeft.get());
        telemetry.addData("rotateRight: ", rotateRight.get());
        telemetry.addData("litPos: ", liftPos.get());
        telemetry.addData("polyButton: ", polyButton.get());
        telemetry.addData("topRight: ", topRight.get());
        telemetry.addData("slidePos: ", slidePos.get());

        telemetry.addLine();

        if(touchpad.getNumFingers() >= 1) telemetry.addData("V1: ", touchpad.getV1().getVelocity());
        telemetry.addData("V1 Angle: ", touchpad.getV1().getAngle(AngleUnit.DEGREES));
        if(touchpad.getNumFingers() == 2) telemetry.addData("V2: ", touchpad.getV2().getVelocity());
    }

    public void stop() {
        handler.stopInputs();
    }

}
