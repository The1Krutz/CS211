/*
   Name: Thomas Kent
   Date: 13 January 2017
   Course: CS 211

   Hexagon class
   Represents a regular six sided figure (ie: all six sides are the same length)
 */
public class Hexagon implements Shape {
    // Instance fields
    private double _side;

    // Constructors
    public Hexagon(double side) {
        _side = side;
    }

    public Hexagon() {
        this(0);
    }

    // Accessors
    public double getSide() {
        return _side;
    }

    // Mutators
    public void setSide(double side) {
        _side = side;
    }

    // Instance methods
    // calculates and returns the area of regular hexagon
    public double getArea() {
        // Area of a hexagon = (side * 3*rt(3))/2
        return (3.0 * Math.sqrt(3.0) * _side * _side) / 2.0;
    }

    // calculates and returns the perimeter of a regular hexagon
    public double getPerimeter() {
        // perimeter of a hexagon = 6 * side
        return _side * 6.0;
    }
}
