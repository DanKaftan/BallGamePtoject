/**
 * responsible to remove a ball from the game when it fall down.
 */
public class BlockRemover implements HitListener {
    // the game
    private GameLevel gameLevel;
    // count the remaining blocks in the game
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel the game.
     * @param remainingBlocks count the remaining blocks in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * remove block that being hitted from the game.
     * @param beingHit the hitted block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}