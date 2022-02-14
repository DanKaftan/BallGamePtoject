/**
 * @author Dan Kaftan
 * id 213848518
 * Class of a point.
 */
public class Point {
    private double x;    // x value of the point
    private double y;    // y value of the point

    /**
     * constructor.
     *
     * @param x x value of the point.
     * @param y y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance.
     *
     * @param other the point that the distance is checks for.
     * @return distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

    /**
     * check equality of 2 points.
     *
     * @param other the point that the equality is checks for.
     * @return rue is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return ((x == other.x) && (y == other.y));
    }

    // Return the x and y values of this point

    /**
     * returns x value.
     *
     * @return x value of this point
     */
    public double getX() {
        return x;
    }

    /**
     * returns y value.
     *
     * @return y value of this point
     */
    public double getY() {
        return y;
    }
}

