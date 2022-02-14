/**
 * Print a message every time a block is being hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * Print a message every time a block is being hit.
     * @param beingHit the hitted object.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}