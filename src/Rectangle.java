import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of rectangle.
 */
public class Rectangle {
    private Point upperLeft; // upper left corner of the rectangle
    private double width;    // width of the rectangle
    private double height;   // height of the rectangle
    private Color color;     // color of the rectangle

    /**
     * Getter of the color field of the rectangle.
     * @return color of the rectangle.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Constructor of the class Rectangle.
     * Create a new rectangle with location and width/height.
     * @param upperLeft upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        // set the fields of the class
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor of the class Rectangle with color.
     * Create a new rectangle with location and width/height.
     * @param upperLeft upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     * @param color color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        // set the fields of the class
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Setter of the color field.
     * @param color1 color of the rectangle.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line the specified line.
     * @return List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // list of all intersection points of the given line in this rectangle
        List intersectionPointsList = new ArrayList<Point>();
        // run all of the bounders and check intersection points of them with the line
        for (Line bounderLine : fromBoundersToLines()) {
            Point intersectionPoint = line.intersectionWith(bounderLine);
            if (intersectionPoint != null) {
                // add the intersection point to the list
                intersectionPointsList.add(intersectionPoint);
            }
        }
        return intersectionPointsList;
    }

    /**
     * convert the bounders of the rectangle to a lines array.
     * @return array of lines of the rectangle bounders
     */
    public Line[] fromBoundersToLines() {
        // convert all bounders to lines

        // line of the left bounder of the rectangle
        Line leftBounderLine = new Line(this.upperLeft,
                new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height));
        // line of the upper bounder of the rectangle
        Line upperBounderLine = new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
        // line of the right bounder of the rectangle
        Line rightBounderLine = new Line(new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height));
        // line of the downer bounder of the rectangle
        Line downerBounderLine = new Line(new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.getHeight()),
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height));

        // return array of all of the bounders
        return new Line[] {leftBounderLine, upperBounderLine, rightBounderLine, downerBounderLine};
    }

    /**
     * Getter of the width field.
     * @return width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Getter of the height field.
     * @return height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    /**
     * Getter of the upperLeft field.
     * @return upper left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}