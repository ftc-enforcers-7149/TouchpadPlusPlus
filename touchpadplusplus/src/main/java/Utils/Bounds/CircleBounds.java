package Utils.Bounds;

import static Utils.Touchpad.COORD_MULT;

import Utils.Point;
//
public class CircleBounds extends Bounds {

    public static final CircleBounds CENTER = new CircleBounds(0, 0, COORD_MULT/2);

    private final Point center;
    private final double r;

    public CircleBounds(Point center, double r) {
        super(Math.max(center.getX() - r, -COORD_MULT), Math.min(center.getX() + r, COORD_MULT),
                Math.max(center.getY() - r, -COORD_MULT), Math.min(center.getY() + r, COORD_MULT));
        this.r = r;
        this.center = center;
    }

    public CircleBounds(double x, double y, double r) {
        this(new Point(x, y), r);
    }

    @Override
    public boolean contains(Point p) {
        return p.distanceTo(center) <= r;
    }
}
