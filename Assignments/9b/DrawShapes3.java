// Draws and fills several shapes.
// From BJP text DrawShpaes2
// modified by W.P. Iverson, April 2016
// for CS211 course, Bellevue College

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawShapes3 extends JFrame implements MouseListener {
	
	JPanel panel;  // Oracle standard Panel
	int clickCount; // allows something different alternate

	public DrawShapes3() {
		clickCount = 0;
		panel = new JPanel();
		panel.setSize(400,200);
		panel.addMouseListener(this);		
		this.add(panel);
		this.setSize(400,200);		
		this.setVisible(true);
	}

	public void mouseClicked(MouseEvent arg0) {
		Graphics g = getGraphics();
		clickCount++;
		if (clickCount % 2 != 0) {
			// what we did in DrawShapes2
			g.fillRect(25, 50, 20, 20);
			g.drawRect(150, 10, 40, 20);
			g.drawOval(50, 25, 20, 20);
			g.fillOval(150, 50, 40, 20);
		} else {
			// do something different on even clicks
			int x = arg0.getX()+10;
			int y = arg0.getY()+30;
			// draw
			g.fillRect(25+x, 50+y, 20, 20);
			g.drawRect(150+x, 10+y, 40, 20);
			g.drawOval(50+x, 25+y, 20, 20);
			g.fillOval(150+x, 50+y, 40, 20);
			// erase the old
			g.setColor(panel.getBackground());
			g.fillRect(25, 50, 20, 20);
			g.drawRect(150, 10, 40, 20);
			g.drawOval(50, 25, 20, 20);
			g.fillOval(150, 50, 40, 20);
		}
	}

	// following intentionally not implemented at present
	// only here to satisfy Interface
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
    public static void main(String[] args) {
    	new DrawShapes3(); // launches Class from console application
    }
}
