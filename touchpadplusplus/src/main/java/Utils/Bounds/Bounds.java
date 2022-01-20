package Utils.Bounds;


import Utils.Point;

public abstract class Bounds {
    // Bounding box variables
    protected double minX, minY, maxX, maxY;

    public Bounds(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public Bounds() {
        this.minX = 0;
        this.maxX = 0;
        this.minY = 0;
        this.maxY = 0;
    }

    protected void setBounds(Bounds b) {
        this.minX = b.minX;
        this.maxX = b.maxX;
        this.minY = b.minY;
        this.maxY = b.maxY;
    }

    protected void setBounds(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public abstract boolean contains(Point p);

    public double getMinX() {
        return minX;
    }
    public double getMinY() {
        return minY;
    }
    public double getMaxX() {
        return maxX;
    }
    public double getMaxY() {
        return maxY;
    }
}
