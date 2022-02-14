import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * Class of a line.
 */
public class Line implements Sprite {
    private Point start;   // start point of the line
    private Point end;     // end point of the line
    private double epsilon = 0.001;
    private Color color = Color.blue;
    //

    /**
     * constructor by 2 points.
     *
     * @param start start point of the line
     * @param end   end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor by 2 points and color.
     *
     * @param start start point of the line
     * @param end   end point of the line
     * @param color color of the line
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * constructor by coordinates of 2 points.
     *
     * @param x1 x value of start point
     * @param y1 y value of start point
     * @param x2 x value of end point
     * @param y2 y value of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * get the legth of the line.
     *
     * @return length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle point of the line
     */
    public Point middle() {
        // define middle point of the line
        Point middle = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
        return middle;
    }

    /**
     * return start point of the line.
     *
     * @return start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * return end point of the line.
     *
     * @return end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * check if 2 lines are intersected.
     *
     * @param other the line the intersection does check for.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * find the intersection point of 2 lines.
     *
     * @param other the line the intersection does check for
     * @return intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {

        // creating linear functions for the lines
        LinearFunc func1 = new LinearFunc(this);
        LinearFunc func2 = new LinearFunc(other);

        // calculate intersection point of the two functions
        Point intersectionPoint = func1.getIntersectionPoint(func2);

        // if there is no intersection point - return null
        if (intersectionPoint == null) {
            return null;
        }

        // check if intersection point of the functions is on both lines
        if (this.contains(intersectionPoint) && other.contains(intersectionPoint)) {
            // intersection point is on both lines
            return intersectionPoint;
        }
        // intersection point is not on both lines
        return null;
    }

    /**
     * check if a given point is in the range of the line.
     * @param point the given point
     * @return true if the point is ib the range of the line
     * false otherwise.
     */
    public boolean contains(Point point) {
        return point.getX() >= Math.min(start.getX() - epsilon, end.getX() - epsilon)
                && point.getX() <= Math.max(start.getX() + epsilon, end.getX() + epsilon)
                && point.getY() >= Math.min(start.getY() - epsilon, end.getY() - epsilon)
                && point.getY() <= Math.max(start.getY() + epsilon, end.getY() + epsilon);
    }

    /**
     * check if two lines are the same.
     * @param o the other line.
     * @return terue if the lines are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        // check if the other object is this object
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Line line = (Line) o;

        if (start != null ? !start.equals(line.start) : line.start != null) {
            return false;
        }
        return end != null ? end.equals(line.end) : line.end == null;
    }

    // TODO check what this method do

    /**
     * hash code function.
     * @return
     */
    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * get the closest intersection point of the line with the rectangle
     * to the start point of the line.
     * @param rect the specified rectangle.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // list of all of the intersection points of this line with the given rectangle
        List<Point> intersectionPointsList = rect.intersectionPoints(this);
        // check if there is are intersection points
        if (intersectionPointsList.isEmpty()) {
            // if there are no intersection points - return null
            return null;
        }
        /* the closest intersection point to the start point of the line
           booted to be the first point in the list */
        Point closestInterPoint = intersectionPointsList.get(0);
        /* run all over the intersection points and check which is the closer to
           the start point of the line */
        for (Point intersectionPoint : intersectionPointsList) {
            if (this.start.distance(intersectionPoint) < this.start.distance(closestInterPoint)) {
                // replace the closest point to be this point
                closestInterPoint = intersectionPoint;
            }
        }
        return closestInterPoint;
    }

    /**
     * check if a given point is on this line.
     * @param point the given point.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isPointInLine(Point point) {
        /* if the line is parallel to the y axes its enough to check
        if the point is in the range of the line */
        if (start.getX() == end.getX()) {
            return (point.getX() == start.getX() && point.getY()
                    >= (Math.min(start.getY(), end().getY())) && point.getY()
                    <= (Math.max(start.getY(), end().getY())));
        } else {
            LinearFunc linearFunc = new LinearFunc(this);
            if (Math.abs((point.getX() * linearFunc.getM() + linearFunc.getB() - point.getY())) < 0.000001
                    && this.contains(point)) {
                return true;
            }
            return false;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }

    @Override
    public void timePassed() {

    }
}