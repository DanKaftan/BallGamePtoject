
/**
 * Class of Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;

    /**
     * getter of dx value of velocity.
     * @return dx value of the velocity.
     */
    public double getDx() {
        return dx;
    }

    /**
     * getter of dy value of velocity.
     * @return dy value of the velocity.
     */
    public double getDy() {
        return dy;
    }

    private double dy;

    /**
     * constructor uses dx and dy values.
     * @param dx the change in the x value of the point in each frame.
     * @param dy the change in the y value of the point in each frame.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor uses angle and speed values.
     * @param angle the angle of the velocity.
     * @param speed the speed of the velocity.
     * @return velocity of a ball.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle - 90));
        double dy = speed * Math.cos(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     * @param p point (center of a ball)
     * with the velocity that change location.
     * @return new point at the new location.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * change ball velocity direction to the opposite side when ball hit corner.
     * @param isSideCorner true if the ball hit the sides borders
     * and false if hits upper or dawn borders.
     */
    public void hitCorner(boolean isSideCorner) {
        if (isSideCorner) {
            this.dx = -1 * dx;
        } else {
            this.dy = -1 * dy;
        }
    }

    /**
     * @return speed of the velocity.
     */
    public double getSpeed() {
        // calculated by Pythagoras formula
        return Math.sqrt(dx * dx + dy * dy);
    }
}

