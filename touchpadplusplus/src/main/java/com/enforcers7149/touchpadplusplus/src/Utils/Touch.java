package com.enforcers7149.touchpadplusplus.src.Utils;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

// Vector packet handling class. Sure it may be redundant, but it's fun!
public class Touch {

    // Important storage variables
    private final Point pos, lastPos;
    private double time, lastTime;

    // deadzone var; if less than, v = 0;
    //private final double deadzone = .005;

    // Standard constructor when there's a starting point

    public Touch(Point pos, Point lastPos, double time, double lastTime) {
        this.pos = pos; this.lastPos = lastPos; this.time = time; this.lastTime = lastTime;
    }

    public Touch(Touch touch) {
        pos = touch.pos; lastPos = touch.lastPos; time = touch.time; lastTime = touch.lastTime;
    }

    // Zeroed constructor

    public Touch() {
        pos = new Point(); lastPos = new Point(); time = 0; lastTime = 0;
    }

    public void setTouch(Touch vP) {
        pos.setPoint(vP.pos); lastPos.setPoint(vP.pos);
        time = vP.time; lastTime = vP.lastTime;
    }

    public void setTouch(Point pos, Point lastPos, double time, double lastTime) {
        pos.setPoint(pos); lastPos.setPoint(lastPos); this.time = time; this.lastTime = lastTime;
    }

    public void resetTouch() {
        pos.setPoint(new Point()); lastPos.setPoint(new Point()); time = 0; lastTime = 0;
    }

    public double getDistance() {
        return pos.distanceTo(lastPos);
    }

    public double getVelocity() {
        double velocity = Math.sqrt(Math.pow(getXVel(), 2) + Math.pow(getYVel(), 2));
        return velocity /*> deadzone ? velocity : 0*/;
    }

    public double getAngle(AngleUnit angleUnit) {
        return lastPos.angleTo(pos, angleUnit);
    }

    public double getXVel() {
        return (pos.getX() - lastPos.getX()) / (time - lastTime);
    }

    public double getYVel() {
        return (pos.getY() - lastPos.getY()) / (time - lastTime);
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

}
