package tmpQ1;

import java.awt.Color;
import java.awt.Graphics;

public class Raupe {
	
	// Attribute
	private int x;
	private int y;
	private Color farbe;
	private int speed;

// Konstuktor (oder auch mehrere Konstruktoren)

	public Raupe(int x, int y, Color farbe, int v) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.farbe = farbe;
		this.speed = v;
	}


	
//	public Raupe() {
//		
//	}
	
// Methoden
	
	public void zeichnen(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(farbe);
		g.fillOval(x, y, 20, 20);
		g.fillOval(x + 60, y, 20, 20);
		g.fillRect(x + 10, y + 2, 60, 15);
	}
	
	public void bewegen(int fensterbreite) {
		x = x + speed;
		if (x > fensterbreite) {
			x = -100;
		}
	}
	

}
