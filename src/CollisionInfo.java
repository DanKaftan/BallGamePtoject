/**
 * Info about a collision occurs such as
 * the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    // the point at which the collision occurs
    private Point collisionPoint;
    // the collidable object involved in the collision
    private Collidable collisionObject;

    /**
     * Constructor.
     * sets all the field of the class.
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        // sets the field of the class.
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * Getter of the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Getter of the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}