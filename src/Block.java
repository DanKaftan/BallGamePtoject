import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // rectangle of the block
    private Rectangle rectangle;
    // list of listeners
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * Constructor of the Block class.
     * @param rectangle rectangle of the Block.
     */
    public Block(Rectangle rectangle) {
        // set the rectangle field of the class
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Getter of the Block's Rectangle.
     * @return Block's Rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);
        Line[] boundersLines = rectangle.fromBoundersToLines();
        // check id the ball hit the vertical bounders
        if (boundersLines[0].isPointInLine(collisionPoint) || boundersLines[2].isPointInLine(collisionPoint)) {
            currentVelocity.hitCorner(true);
        }
        // check id the ball hit the horizialZ bounders
        if (boundersLines[1].isPointInLine(collisionPoint) || boundersLines[3].isPointInLine(collisionPoint)) {
            currentVelocity.hitCorner(false);
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(rectangle.getColor());
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        // right now do nothing
    }

    /**
     * add the block to the sprite and collidable lists
     * of the game.
     * @param g the game of the block.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove the block from the collidable and sprite list.
     * @param gameLevel th game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify hall the listeners an hit has been oqure.
     * @param hitter the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
