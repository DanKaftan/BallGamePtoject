import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible of the sprites of the game.
 * owns list of all the sprites in the game.
 */
public class SpriteCollection {
    // list of all the sprites in the game
    private List<Sprite> spriteCollection = new ArrayList<>();

    /**
     * add new sprite to the sprite collection list.
     * @param s the sprite witch added.
     */
    public void addSprite(Sprite s) {
        spriteCollection.add(s);
    }

    /**
     * notify all sprites time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<>(spriteCollection);
        for (Sprite sprite : spritesList) {
            sprite.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d drawsurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteCollection) {
            sprite.drawOn(d);
        }
    }

    /**
     * Remove sprite from the sprite list.
     * @param sprite the removed sprite.
     */
    public void removeSprite(Sprite sprite) {
        spriteCollection.remove(sprite);
    }
}