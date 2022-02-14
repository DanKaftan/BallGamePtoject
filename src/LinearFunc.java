/**
 * Class of a linear function in the shape of y = mx + b.
 */
public class LinearFunc {
    // m and b parameters of the function y = mx + b
    private double m;
    private double b;
    private boolean slopeExist;
    private Line line;

    /**
     * Constructor of the Class LinearFunc.
     *
     * @param line the line we calculate the linear function for
     * @get line
     * calculate by the start,end points of the line m,b parameters in
     * y = mx + b equation
     */
    public LinearFunc(Line line) {
        this.line = line;
        // if slope exist
        if (line.start().getX() != line.end().getX()) {
            slopeExist = true;
            this.m = (line.end().getY() - line.start().getY()) / (line.end().getX() - line.start().getX());
            this.b = line.start().getY() - m * line.start().getX();
        // else - slope does not exist
        } else {
            slopeExist = false;
            b = line.start().getX();
        }
    }

    /**
     * @return b parameter of the function.
     */
    public double getB() {
        return this.b;
    }

    /**
     * @return slant (m) parameter of the function.
     */
    public double getM() {
        return this.m;
    }

    /**
     * @return slopeExist indication.
     */
    public boolean isSlopeExist() {
        return this.slopeExist;
    }
    /**
     * calculate the intersection point of this function with the given function.
     *
     * @param other the other line which the intersection point is calculated for.
     * @return the intersection point of the 2 functions.
     */
    public Point getIntersectionPoint(LinearFunc other) {

        // if both lines have no slope - they do not intersect
        if (!this.isSlopeExist() && !other.slopeExist) {
            // if both line at same x value
            if (line.start().getX() == other.line.start().getX()) {
                if ((Math.max(line.start().getY(), line.end().getY())
                        == Math.min(other.line.start().getY(), other.line.end().getY()))) {
                    return new Point(this.b, Math.max(line.start().getY(), line.end().getY()));
                }
                if (Math.min(line.start().getY(), line.end().getY())
                        == Math.max(other.line.start().getY(), other.line.end().getY())) {
                    return new Point(this.b, Math.min(line.start().getY(), line.end().getY()));
                }
                // check if this line is just a point
                if ((line.start().getY() == line.end().getY())) {
                    double yVal = line.start().getY();
                    // check if the point is in the range of the other line
                    if ((yVal <= Math.max(other.line.start().getY(), other.line.end().getY()))
                    && (yVal >= Math.min(other.line.start().getY(), other.line.end().getY()))) {
                        return new Point(this.b, line.start().getY());
                    }
                }
                // check if the other line is just a point
                if (other.line.start().getY() == other.line.end().getY()) {
                    double yVal = other.line.start().getY();
                    // check if the other line is in the range of this line
                    if ((yVal <= Math.max(line.start().getY(), line.end().getY()))
                            && (yVal >= Math.min(line.start().getY(), line.end().getY()))) {
                        return new Point(this.b, other.line.start().getY());
                    }
                }
            }
            return null;
        }

        // if this line has no slope - intersection is on the x of this line and y of other func of this x
        if (!this.isSlopeExist()) {
            return  new Point(this.b, other.calcY(this.b));
        }

        // if other line has no slope - intersection is on the x of other line and y of this func of other x
        if (!other.isSlopeExist()) {
            return  new Point(other.getB(), this.calcY(other.getB()));
        }

        // check if func are parallel each other
        if (this.m == other.getM()) {
            // check if they are intersect in one point
            if (Math.max(line.start().getX(), line.end().getX())
                    == Math.min(other.line.start().getX(), other.line.end().getX())) {
                return new Point(Math.max(line.start().getX(), line.end().getX()),
                        (m * (Math.max(line.start().getX(), line.end().getX())) + b));
            }
            if (Math.min(line.start().getX(), line.end().getX())
                    == Math.max(other.line.start().getX(), other.line.end().getX())) {
                return new Point(Math.min(line.start().getX(), line.end().getX()),
                        (m * (Math.min(line.start().getX(), line.end().getX())) + b));
            }
            return null;
        }

        // both lines have slope and are not parallel, so now we can calc the two functions
        // x value of the intersection point calculated by x = (b2 - b1)/(m1 -m2)
        double xCord = (other.getB() - this.b) / (this.m - other.getM());

        // y value of the intersection point calculated by placing x value in this function
        double yCord = this.m * xCord + this.b;

        // creating a new point with those coordinate
        return new Point(xCord, yCord);
    }

    /**
     * get y value of point in the function by its x value.
     *
     * @param x x value of the point
     * @return y value of the point
     */
    public double calcY(double x) {
        return x * m + b;
    }
}
