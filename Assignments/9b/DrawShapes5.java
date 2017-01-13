
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawShapes5 extends JFrame implements MouseListener, MouseMotionListener {
    private static int leftClick = 1024;
    private static int middleClick = 2048;
    private static int rightClick = 4096;
    private static Graphics g;

    private int frameHeight;
    private int frameWidth;
    private int rectHeight;
    private int rectWidth;
    private int currentX;
    private int currentY;

    public DrawShapes5() {
        frameHeight = 500;
        frameWidth = 500;
        rectHeight = 100;
        rectWidth = 100;

        this.setTitle("Draw Shapes 5, different buttons for different colors!");
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setSize(frameWidth, frameHeight);
        this.setVisible(true);
    }

    public void mouseClicked(MouseEvent arg) {
        if (shouldMoveRectangle(arg.getX(), arg.getY()) || g == null) {
            if (arg.getButton() == MouseEvent.BUTTON1) {
                DrawRectangle(arg.getX(), arg.getY(), Color.BLACK);
            }
            else if (arg.getButton() == MouseEvent.BUTTON3) {
                DrawRectangle(arg.getX(), arg.getY(), Color.RED);
            }
            else if (arg.getButton() == MouseEvent.BUTTON2) {
                DrawRectangle(arg.getX(), arg.getY(), Color.GREEN);
            }
        }
    }

    public void mouseDragged(MouseEvent arg) {
        if (shouldMoveRectangle(arg.getX(), arg.getY()) || g == null) {
            if (arg.getModifiersEx() == leftClick) {
                DrawRectangle(arg.getX(), arg.getY(), Color.BLACK);
            }
            else if (arg.getModifiersEx() == rightClick) {
                DrawRectangle(arg.getX(), arg.getY(), Color.RED);
            }
            else if (arg.getModifiersEx() == middleClick) {
                DrawRectangle(arg.getX(), arg.getY(), Color.GREEN);
            }
        }
    }

    public void mouseEntered(MouseEvent arg) { }
    public void mouseExited(MouseEvent arg) { }
    public void mousePressed(MouseEvent arg) { }
    public void mouseReleased(MouseEvent arg) { }
    public void mouseMoved(MouseEvent arg) { }

    public static void main(String[] args) {
        new DrawShapes5();
    }

    private void DrawRectangle(int mouseX, int mouseY, Color boxColor) {
        if (g == null) {
            g = getGraphics();
        }

        currentX = mouseX - (rectHeight / 2);
        currentY = mouseY - (rectWidth / 2);

        g.clearRect(0, 0, frameWidth, frameHeight);
        g.setColor(boxColor);
        g.fillRect(currentX, currentY, rectWidth, rectHeight);
    }

    private boolean shouldMoveRectangle(int x, int y) {
        return (x > currentX && x < currentX + rectHeight
                && y > currentY && y < currentY + rectWidth);
    }
}
