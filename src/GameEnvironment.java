import java.util.ArrayList;
import java.util.List;

/**
 * this Class responsible of the collection of all the collidable objects in the game
 * that the ball can collide with.
 * The ball will know the game environment,
 * and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    // list of all the collidable objects in the game.
    private List<Collidable> collidableList = new ArrayList();

    // add the given collidable to the environment.

    /**
     * add the given collidable to the collidable list.
     * @param c the collidable witch added
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * remove collidable from the collidable list.
     * @param c the removed collidable.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * @param trajectory line from the center of the ball to the place
     * that the ball will be in the next step.
     * @return If this object will not collide with any of the collidables in this collection, return null.
     * else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable = null;
        Point closestCollisionPoint = null;
        /* run on all of the collidables an check with witch collidable
           the collision point is the closest */
        for (Collidable collidable : collidableList) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                /* if the collision is closer than the end of the end of the trajectory
                   and than the closest coll' point for now. replace this point as the closest*/
                if (trajectory.start().distance(collisionPoint) <= trajectory.length() + 0.00001) {
                    if (closestCollidable == null || trajectory.start().distance(collisionPoint)
                            <= trajectory.start().distance(closestCollisionPoint)) {
                        closestCollidable = collidable;
                        closestCollisionPoint = collisionPoint;
                    }
                }
            }
        }
        return closestCollidable != null ? new CollisionInfo(closestCollisionPoint, closestCollidable) : null;
    }

}