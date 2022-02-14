import biuoop.DrawSurface;

/**
 * Interface of all the animations.
 */
public interface Animation {
    /**
     * contain all the actions of an animation in a single frame.
     * @param d drawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();

    /***
     * @return number of seconds for each frame.
     */
    int getMillisecondsPerFrame();
}