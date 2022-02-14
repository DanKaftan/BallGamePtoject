import biuoop.DrawSurface;

/**
 * class of background of a level.
 */
public class Background implements Sprite {
    // list of all the sprites of the background.
    private SpriteCollection spritesOfBackground = new SpriteCollection();
    @Override
    public void drawOn(DrawSurface d) {
        spritesOfBackground.drawAllOn(d);
    }

    @Override
    public void timePassed() {
        spritesOfBackground.notifyAllTimePassed();
    }

    /**
     * add sprite for the background sprite collection.
     * @param sprite drawable object.
     */
    public void addSpriteToCollection(Sprite sprite) {
        spritesOfBackground.addSprite(sprite);
    }

    /**
     * @return spriteList of the background.
     */
    public SpriteCollection getSpritesOfBackground() {
        return spritesOfBackground;
    }
}
