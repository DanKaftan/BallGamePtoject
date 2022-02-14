import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class of a ball.
 */
public class Ball implements Sprite {
    // center point of the ball
    private Point center;
    // radius of the ball
    private int r;
    // color of the ball
    private java.awt.Color color;
    // velocity of the ball
    private Velocity velocity;
    // the frame the ball is drawn on
    private Frame frame;
    // Game Environment
    private GameEnvironment gameEnvironment;

    /**
     * Constructor getting point as center, define the balls values.
     * @param center center point of the ball
     * @param r radius of the ball
     * @param color color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    /**
     * Constructor getting x,y values of the center, define the balls values.
     * @param x x value of the center
     * @param y y value of the center
     * @param r radius of the center
     * @param color color of the ball
     * @param gameEnvironment game environment of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    // accessors

    /**
     * accessor returns x value of the ball.
     *
     * @return x value of this point
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }
    /**
     * accessor returns y value of the ball.
     *
     * @return y value of this point
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }
    /**
     * accessor returns size of the ball.
     *
     * @return x value of this point
     */
    public int getSize() {
        return this.r;
    }
    /**
     * accessor returns color of the ball.
     *
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    /**
     * draw the ball on the given DrawSurface.
     * @param surface the given DrawSurface
     */

    @Override
    public void drawOn(DrawSurface surface) {
        drawOn(surface, new Frame(new Point(0, 0), surface.getHeight(), surface.getWidth(), null));
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }


    /**
     * draw the ball on the given DrawSurface.
     * @param d the given DrawSurface
     * @param frame1 the frame that the ball is drawn on.
     */

    public void drawOn(DrawSurface d, Frame frame1) {
        this.frame = frame1;
        d.setColor(this.color);
        d.fillCircle(this.getX(), this.getY(), this.r);
        d.setColor(Color.black);
        d.drawCircle(this.getX(), this.getY(), this.r);
    }


    /**
     * setter.
     * set the velocity of the ball by getting velocity.
     * @param v velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * setter.
     * set the velocity of the ball by getting the change in the x and y values of the ball.
     * @param dx the change in the x value of the ball in each frame.
     * @param dy the change in the y value of the ball in each frame.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * accessor returns velocity of the ball.
     *
     * @return velocity of the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * move the ball one step in each frame.
     */
    public void moveOneStep() {
        // compute the ball trajectory
        Line trajectory = new Line(this.center, new Point(this.center.getX()
                + velocity.getDx(), this.center.getY() + velocity.getDy()));

        // check if moving on this trajectory will hit anything
        CollisionInfo collisionCollide = gameEnvironment.getClosestCollision(trajectory);
        if (collisionCollide != null) {
            Point colllisionPoint = collisionCollide.collisionPoint();
                this.velocity = collisionCollide.collisionObject().hit(this, colllisionPoint, this.velocity);
        } else {
            this.center = trajectory.end();
        }
    }

    /**
     * add the ball to the sprites list of the game.
     * @param g the game of the ball.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Setter of gameEnvironment.
     * @param ge gameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment ge) {
        gameEnvironment = ge;
    }

    /**
     * remove the ball from the sprite list.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}