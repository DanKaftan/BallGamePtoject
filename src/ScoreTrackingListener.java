/**
 * Responsible to update the score when a ball is getting hit.
 */
public class ScoreTrackingListener implements HitListener {
    // the current score.
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * add 5 points to the score when a block is getting hit.
     * @param beingHit the hitted object.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}