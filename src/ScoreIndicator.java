import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Responsible on the score drawing.
 */
public class ScoreIndicator implements Sprite {
    // counter of the score.
    private Counter score;

    /**
     * Constructor.
     * @param score counter of the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 15, "Score: " + Integer.toString(score.getValue()), 14);
    }

    @Override
    public void timePassed() {
        // for now do nothing
    }
}
