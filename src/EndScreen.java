import biuoop.DrawSurface;

/**
 * Class responsible to the end screen of the game.
 */
public class EndScreen implements Animation {
    // true if the player won the game, false otherwise.
    private Boolean isWon;
    // counter of the player's score.
    private Counter scoreCounter;

    /**
     * Constructor of the class.
     * @param isWon booleans witch tell if the player won or lost the game.
     * @param scoreCounter counter of the player's score.
     */
    public EndScreen(Boolean isWon, Counter scoreCounter) {
        this.isWon = isWon;
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWon) {
            d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2, "You Win! Your score is "
                    + scoreCounter.getValue() + ".", 30);
        } else {
            d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2,
                    "Game Over. Your score is " + scoreCounter.getValue(), 30);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public int getMillisecondsPerFrame() {
        return 10;
    }
}
