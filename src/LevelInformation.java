import java.util.List;

/**
 * Interface of information on levels.
 */
public interface LevelInformation {
    /**
     * @return number of balls in the game.
     */
    int numberOfBalls();
    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle's speed.
     */
    int paddleSpeed();
    /**
     * @return paddle's width.
     */
    int paddleWidth();
    /**
     * @return level's name.
     * the level name will be displayed at the top of the screen.
     */
    String levelName();
    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();
    /**
     * @return The Blocks that make up this level.
     * each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}