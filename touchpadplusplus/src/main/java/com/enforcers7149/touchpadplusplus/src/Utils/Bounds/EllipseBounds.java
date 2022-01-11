package com.enforcers7149.touchpadplusplus.src.Utils.Bounds;

import com.enforcers7149.touchpadplusplus.src.Utils.Point;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class EllipseBounds extends Bounds {

    // xOffset is h, yOffset is k, angleOffset is A, a and b are "extensions"
    private final double xOffset, yOffset, angle, a, b;

    /**
     *
     * @param center center of the ellipse
     * @param angle angle. IS IN RADIANS PLEASE AND THANK YOU
     * @param a "stretch 1"
     * @param b "stretch 2"
     */
    public EllipseBounds(Point center, double angle, double a, double b) {

        // Calculates the min/max x and y
        super(angle % Math.PI == 0 ? -b : -a,
                angle % Math.PI == 0 ? b : a,
                angle % Math.PI == 0 ? -a : -b,
                angle % Math.PI == 0 ? a : b);

        xOffset = center.getX(); yOffset = center.getY();
        this.angle = angle % Math.PI;
        this.a = a;
        this.b = b;
    }

    public boolean contains(Point p) {
        double x = p.getX(), y = p.getY();
        return 1 <= Math.pow(((x - xOffset) * Math.cos(angle) - (y - yOffset) * Math.sin(angle)), 2) / Math.pow(a, 2)
                + Math.pow(((x - xOffset) * Math.sin(angle) - (y - yOffset) * Math.cos(angle)), 2) / Math.pow(b, 2);
    }
}
