import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class responsible for running a given animation.
 */
public class AnimationRunner {
    private static final double FRAME_WIDTH = 800;
    private static final double FRAME_HEIGHT = 600;
    private static GUI gui = new GUI("Game", (int) FRAME_WIDTH, (int) FRAME_HEIGHT);

    /**
     * method responsible for running a given animation.
     * @param animation the given animation.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();

        int millisecondsPerFrame = animation.getMillisecondsPerFrame();
        // runs until the player stops the game
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);


            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }

    /**
     * Getter of GUI.
     * @return gui.
     */
    public GUI getGui() {
        return gui;
    }
}