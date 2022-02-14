import biuoop.DrawSurface;

/**
 * Interface of sprite.
 * sprite - a drawable object of the game.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d drawsurface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}