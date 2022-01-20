package Utils.Bounds;

import Utils.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class PolygonBounds extends Bounds {

    private final ArrayList<Point> points;

    public PolygonBounds(ArrayList<Point> pointList) {
        super(); //Only 0s until the points are checked

        points = pointList;
        minX = points.get(0).getX();
        maxX = minX;
        minY = points.get(0).getY();
        maxY = minY;

        for(int i = 1; i < points.size(); i++) {

            double tempX = points.get(i).getX();
            double tempY = points.get(i).getY();

            if(tempX > maxX) maxX = tempX;
            if(tempX < minX) minX = tempX;
            if(tempY > maxY) maxY = tempY;
            if(tempY < maxY) minY = tempY;
        }
    }

    public PolygonBounds(Point... points) {
        this((ArrayList<Point>) Arrays.asList(points));
    }

    public void addPoint(Point p) {
        points.add(p);

        if (p.getX() > maxX) maxX = p.getX();
        if (p.getX() < minX) minX = p.getX();
        if (p.getY() > maxY) maxY = p.getY();
        if (p.getY() < minY) minY = p.getY();
    }

    @Override
    public boolean contains(Point p)
    {
        if (p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY)
            return false;

        // https://wrf.ecse.rpi.edu/Research/Short_Notes/pnpoly.html
        boolean inside = false;
        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++)
        {
            if ((points.get(i).getY() > p.getY()) != (points.get(j).getY() > p.getY()) &&
                    p.getX() < (points.get(j).getX() - points.get(i).getX()) * ( p.getY() - points.get(i).getY()) / ( points.get(j).getY() - points.get(i).getY()) + points.get(i).getX())
            {
                inside = !inside;
            }
        }

        return inside;
    }
}
