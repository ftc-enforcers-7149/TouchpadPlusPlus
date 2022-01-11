package com.enforcers7149.touchpadplusplus.src.Utils.Bounds;

import static com.enforcers7149.touchpadplusplus.src.Touchpad.COORD_MULT;

import com.enforcers7149.touchpadplusplus.src.Utils.Point;

public class RectBounds extends PolygonBounds {

    public static final RectBounds TOP_LEFT = new RectBounds(-COORD_MULT, 0, 0, COORD_MULT);
    public static final RectBounds BOTTOM_LEFT = new RectBounds(-COORD_MULT, 0, -COORD_MULT, 0);
    public static final RectBounds TOP_RIGHT = new RectBounds(0, COORD_MULT, 0, COORD_MULT);
    public static final RectBounds BOTTOM_RIGHT = new RectBounds(0, COORD_MULT, -COORD_MULT, 0);
    public static final RectBounds CENTER = new RectBounds(-COORD_MULT/2, COORD_MULT/2, -COORD_MULT/2, COORD_MULT/2);

    public RectBounds(double minX, double maxX, double minY, double maxY) {
        this(new Point(minX, minY), new Point(maxX, maxY));
    }

    public RectBounds(Point p1, Point p2) {
        super(p1, new Point(p1.getX(), p2.getY()), p2, new Point(p2.getX(), p1.getY()));
    }

    public RectBounds() {
        this(-COORD_MULT, COORD_MULT, -COORD_MULT, COORD_MULT);
    }
}
