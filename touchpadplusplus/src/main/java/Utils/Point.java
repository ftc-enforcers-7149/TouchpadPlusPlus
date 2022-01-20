package Utils;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x; this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public double distanceTo(Point p) {
        return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
    }

    public double angleTo(Point p, AngleUnit angleUnit) {
        double rads = Math.atan2(p.y - y, p.x - x);

        switch (angleUnit) {
            case DEGREES:
                return Math.toDegrees(rads);
            case RADIANS:
            default:
                return rads;
        }
    }

    public void setPoint(double x, double y) {
        this.x = x; this.y = y;
    }

    public void setPoint(Point p) {
        this.x = p.x; this.y = p.y;
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public double getX() { return x; }
    public double getY() { return y; }

    @NonNull
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
