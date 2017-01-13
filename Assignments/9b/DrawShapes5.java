import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
   Name: Thomas Kent
   Date: 12 January 2017
   Course: CS 211

   DrawShapes5 class
   Draws a rectangle for the user to drag around
   Can do a black, red, or green rectangle depending on which button is used
 */
public class DrawShapes5 extends JFrame implements MouseListener, MouseMotionListener {
    // Static fields
    private static int leftClick = 1024; // constant codes used by MouseEvent to represent button 0 (left click)
    private static int middleClick = 2048; // constant codes used by MouseEvent to represent button 1 (middle click)
    private static int rightClick = 4096; // constant codes used by MouseEvent to represent button 2 (right click)
    private static Graphics g = null; // shared instance of graphics, will be set once the JFrame is initialized

    // Instance fields
    private int frameHeight; // height of the JFrame
    private int frameWidth; // width of the JFrame
    private int rectHeight; // height of the rectangle
    private int rectWidth; // width of the rectangle
    private int currentX; // x value for the top left corner of the most recent location of the rectangle
    private int currentY; // y value for the top left corner of the most recent location of the rectangle

    // Constructor
    public DrawShapes5() {
        // inititalize instance fields
        frameHeight = 500;
        frameWidth = 700;
        rectHeight = 100;
        rectWidth = 100;
        currentX = 0;
        currentY = 0;

        this.setTitle("Draw Shapes 5, different buttons for different colors!");
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setSize(frameWidth, frameHeight);
        this.setVisible(true);
    }

    // Event listeners
    // handles mouse click event (once per complete up-down click)
    public void mouseClicked(MouseEvent arg) {
        if (shouldMoveRectangle(arg.getX(), arg.getY()) || g == null) {
            if (arg.getButton() == MouseEvent.BUTTON1) {
                // black rectangle on left click
                DrawRectangle(arg.getX(), arg.getY(), Color.BLACK);
            }
            else if (arg.getButton() == MouseEvent.BUTTON3) {
                // red rectangle on right click
                DrawRectangle(arg.getX(), arg.getY(), Color.RED);
            }
            else if (arg.getButton() == MouseEvent.BUTTON2) {
                // green rectangle on middle click
                DrawRectangle(arg.getX(), arg.getY(), Color.GREEN);
            }
        }
    }

    public void mouseDragged(MouseEvent arg) {
        if (shouldMoveRectangle(arg.getX(), arg.getY()) || g == null) {
            if (arg.getModifiersEx() == leftClick) {
                // black rectangle on left drag
                DrawRectangle(arg.getX(), arg.getY(), Color.BLACK);
            }
            else if (arg.getModifiersEx() == rightClick) {
                // red rectangle on right drag
                DrawRectangle(arg.getX(), arg.getY(), Color.RED);
            }
            else if (arg.getModifiersEx() == middleClick) {
                // green rectangle on middle drag
                DrawRectangle(arg.getX(), arg.getY(), Color.GREEN);
            }
        }
    }

    // Unused event listeners, required to implement interface
    public void mouseEntered(MouseEvent arg) { }
    public void mouseExited(MouseEvent arg) { }
    public void mousePressed(MouseEvent arg) { }
    public void mouseReleased(MouseEvent arg) { }
    public void mouseMoved(MouseEvent arg) { }

    // Program entry point
    public static void main(String[] args) {
        new DrawShapes5();
    }

    // Methods
    // Draws a rectangle centered on the provided mouse coordinates, in the color provided
    private void DrawRectangle(int mouseX, int mouseY, Color rectangleColor) {
        // sets the single shared instance of the graphics object when the first rectangle is drawn, then re-uses it for the remainder of the program
        if (g == null) g = getGraphics();

        // centers the rectangle on the mouse coordinate
        currentX = mouseX - (rectHeight / 2);
        currentY = mouseY - (rectWidth / 2);

        // wipe the screen and redraw the rectangle
        g.clearRect(0, 0, frameWidth, frameHeight);
        g.setColor(rectangleColor);
        g.fillRect(currentX, currentY, rectWidth, rectHeight);
    }

    // Checks the mouse coordinates against the last known position of the rectangle to determine whether the click event occured within the rectangle or not
    private boolean shouldMoveRectangle(int x, int y) {
        // rectangle should be moved iff the x and y coordinates are both within the rectangle
        return x > currentX && x < currentX + rectHeight
               && y > currentY && y < currentY + rectWidth;
    }
}
