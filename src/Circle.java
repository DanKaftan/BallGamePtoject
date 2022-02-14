import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class of a circle witch is a sprite.
 */
public class Circle implements Sprite {
    private Point middlePoint;
    private int radius;
    private Color color;

    /**
     * Constructor.
     * @param middlePoint the middle point of the circle.
     * @param radius the radius of the circle.
     * @param color the color of the circle.
     */
    public Circle(Point middlePoint, int radius, Color color) {
        this.middlePoint = middlePoint;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.drawCircle((int) middlePoint.getX(), (int) middlePoint.getY(), radius);
    }

    @Override
    public void timePassed() {

    }
}
