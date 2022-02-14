import java.awt.Color;

/**
 * Class of a frame.
 */
public class Frame {

    // the upper left corner of the frame.
    private Point upperLeftCor;
    // height of the frame
    private double height;
    // width of the frame
    private double width;
    // color of the frame
    private Color color;


    /**
     * Constructor of the class frame.
     * set the fields of the object.
     * @param upperLeftCor the upper left corner of the frame.
     * @param height height of the frame
     * @param width width of the frame
     * @param color color of the frame
     */
    public Frame(Point upperLeftCor, double height, double width, Color color) {
        this.upperLeftCor = upperLeftCor;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    /**
     * Getter of the field upper corner.
     * @return the upper corner of the frame.
     */
    public Point getUpperLeftCor() {
        return upperLeftCor;
    }

    /**
     * Getter of the field frame's height.
     * @return the height of the frame.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Getter of the field frame's width.
     * @return the width of the frame.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter of the field frame's color.
     * @return the color of the frame.
     */
    public Color getColor() {
        return color;
    }
}
