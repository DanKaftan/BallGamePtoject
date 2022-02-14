import biuoop.DrawSurface;

/**
 * Class responsible on the pause screen.
 * when typing the key "p" the game should be pause.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
