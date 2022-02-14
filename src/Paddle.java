import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Class of the paddle of the game.
 * paddle - block that the player can move
 * with the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    // rectangle of the paddle - its "shape"
    private Rectangle rectangle;
    private AnimationRunner animationRunner;
    // the keyboard the player use for move the paddle.
    private biuoop.KeyboardSensor keyboard;
    private int paddleSpeed;


    /**
     * Constructor of the paddle.
     * @param rectangle the rectangle of the paddle - its "shape"
     * @param animationRunner responsible on the animation.
     */
    public Paddle(Rectangle rectangle, AnimationRunner animationRunner) {
        // reset the fields of the paddle
        this.rectangle = rectangle;
        this.animationRunner = animationRunner;
        this.keyboard = animationRunner.getGui().getKeyboardSensor();

    }

    /**
     * Getter of the Paddle's rectangle.
     * @return Paddle's rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * move the paddle left side.
     */
    public void moveLeft() {
        /* check if the paddle is not in the left bounder of the screen
        for not move it outside the screen */
        if (rectangle.getUpperLeft().getX() > 19) {
            Color color = rectangle.getColor();
            rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() - paddleSpeed,
                    this.rectangle.getUpperLeft().getY()), rectangle.getWidth(),
                    rectangle.getHeight());
            rectangle.setColor(color);
        }

    }

    /**
     * move the paddle to the right side.
     */
    public void moveRight() {
        /* check if the paddle is not in the right bounder of the screen
        for not move it outside the screen */
        if (rectangle.getUpperLeft().getX() + rectangle.getWidth() < 800 - 19) {
            Color color = rectangle.getColor();
            rectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX() + paddleSpeed,
                    this.rectangle.getUpperLeft().getY()),
                    rectangle.getWidth(), rectangle.getHeight());
            rectangle.setColor(color);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(rectangle.getColor());
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] boundersLines = rectangle.fromBoundersToLines();
        if (boundersLines[0].isPointInLine(collisionPoint) || boundersLines[2].isPointInLine(collisionPoint)) {
            currentVelocity.hitCorner(true);
        }
        if (boundersLines[1].isPointInLine(collisionPoint) || boundersLines[3].isPointInLine(collisionPoint)) {
            currentVelocity.hitCorner(false);
            // the length of one region of the paddle
            double regionLength = rectangle.getWidth() / 5;
            double dy = currentVelocity.getDy();

            // if the ball hit the 1st region
            if (collisionPoint.getX() >= rectangle.getUpperLeft().getX()
                    && collisionPoint.getX() <= rectangle.getUpperLeft().getX() + regionLength) {
                currentVelocity = calcNewVelocity(300, currentVelocity);
            }
            // if the ball hit the 2st region
            if (collisionPoint.getX() >= rectangle.getUpperLeft().getX() + regionLength
                    && collisionPoint.getX() <= rectangle.getUpperLeft().getX() + 2 * regionLength) {
                currentVelocity = calcNewVelocity(330, currentVelocity);
            }
            // if the ball hit the 3st region - center. dont change horizinal direction

            // if the ball hit the 4st region
            if (collisionPoint.getX() >= rectangle.getUpperLeft().getX() + 3 * regionLength
                    && collisionPoint.getX() <= rectangle.getUpperLeft().getX() + 4 * regionLength) {
                currentVelocity = calcNewVelocity(30, currentVelocity);
            }
            // if the ball hit the 5st region
            if (collisionPoint.getX() >= rectangle.getUpperLeft().getX() + 4 * regionLength
                    && collisionPoint.getX() <= rectangle.getUpperLeft().getX() + 5 * regionLength) {
                currentVelocity = calcNewVelocity(60, currentVelocity);
            }
        }
        return currentVelocity;
    }

    /**
     * calc the new velocity of the ball after hitting the paddle.
     * @param angle the angle that the new horizinal direction of the ball should be.
     * @param velocity the previous velocity.
     * @return the new velocity of the ball after hitting the paddle.
     */
    private Velocity calcNewVelocity(double angle, Velocity velocity) {
        /* save the previous dy because only yhe dx should change
        after hitting the paddle */
        double previousDy = velocity.getDy();
        velocity = Velocity.fromAngleAndSpeed(angle, velocity.getSpeed());
        // return the previous dy value.
        velocity = new Velocity(velocity.getDy(), previousDy);
        return velocity;
    }
    /**
     * Add this paddle to the game by adding it.
     * for the sprites and collidable lists.
     * @param g the game of the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Setter of the paddle speed.
     * @param paddleSpeed1 paddle's speed.
     */
    public void setPaddleSpeed(int paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }
}