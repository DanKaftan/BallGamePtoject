import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class of a filled Circle extends Circle.
 */
public class FilledCircle extends Circle {
    private Point middlePoint;
    private int radius;
    private Color color;

    /**
     * Constructor.
     * @param middlePoint middle point of the circle.
     * @param radius radius of the circle.
     * @param color color of the circle.
     */
    public FilledCircle(Point middlePoint, int radius, Color color) {
        super(middlePoint, radius, color);
        this.middlePoint = middlePoint;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle((int) middlePoint.getX(), (int) middlePoint.getY(), (int) radius);
        super.drawOn(d);
    }
}
