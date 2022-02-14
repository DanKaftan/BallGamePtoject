import biuoop.DrawSurface;
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.

/**
 * The CountdownAnimation will show a countdown from countFrom back to 1.
 * where each number will appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    // time of displaying the countdown
    private double numOfSeconds;
    // the number the counting will be starting from.
    private int countFrom;
    // collection of all the sprites of the game.
    private SpriteCollection gameScreen;

    /**
     * Constractor of the class.
     * @param numOfSeconds time of displaying the countdown.
     * @param countFrom the number the counting will be starting from.
     * @param gameScreen collection of all the sprites of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }
    /**
     * showing the countdown in a single frame.
     * @param d DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.notifyAllTimePassed();
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 30);
        countFrom = countFrom - 1;
    }

    /**
     * @return true if the animation came to its end and should be stopped,
     * and false otherwise.
     */
    public boolean shouldStop() {
        if (countFrom == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getMillisecondsPerFrame() {
        return (int) Math.round(numOfSeconds / countFrom) * 1000;
    }
}