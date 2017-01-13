// Draws and fills several shapes.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Bellevue College CS211 JFrame and JPanel demo
//W.P. Iverson, September 2015
//original code from page 201 of BJP text

public class DrawShapes4 extends JFrame implements MouseListener {

	// Construct the object:
	public DrawShapes4() {
		this.setTitle("ALWAYS do the TODO");
		setSize(400,200);			// size of my last example
		addMouseListener(this);		// listen for events		
		this.setSize(800,400);		// make JFrame twice as large	
		this.setVisible(true);
	}

	// Method is called every time user click the JPanel
	public void mouseClicked(MouseEvent arg0) {
		Graphics g = getGraphics();	// standard Oracle Graphics object
		
		// The (x,y) is relative to upper left corner
		// and likely off by width of title bar and size of JFrame
		g.fillRect(25+arg0.getX(), 50+arg0.getY(), 20, 20);
		g.drawRect(150+arg0.getX(), 10+arg0.getY(), 40, 20);
		g.drawOval(50+arg0.getX(), 25+arg0.getY(), 20, 20);
		g.fillOval(150+arg0.getX(), 50+arg0.getY(), 40, 20);
		// shift above (x,y) to get pattern at clicked location
	}

	// following intentionally not implemented at present
	// only here to satisfy Interface
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	// Some people have a separate client launcher, I put it here:
    public static void main(String[] args) {
    	new DrawShapes4(); // launches Class from console application
    }
}
