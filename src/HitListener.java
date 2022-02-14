/**
 * Interface of all the listeners to the hits.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the hitted object.
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}