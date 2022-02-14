/**
 * Interface of collidable objects.
 * collidable - object witch ball can collide with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * @param collisionPoint the collision point.
     * @param currentVelocity the ball velocity before the hit.
     * @param hitter the hitter ball.
     * @return new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
