/**
 * responsible to remove a ball from the game when it fall down.
 */
public class BallRemover implements HitListener {
    // the game
    private GameLevel gameLevel;
    // count the remaining balls in the game
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel the game.
     * @param remainingBalls count the remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
