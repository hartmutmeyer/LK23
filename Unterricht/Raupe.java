package tmpQ1;

import java.awt.Color;
import java.awt.Graphics;

public class Raupe {

// Attribute (globale Variablen)
	
	private Color farbe;
	private int speed;
	private int x = 150;
	private int y;
	private int langOderKurz = 0;
	
// Konstuktor (oder auch mehrere Konstruktoren)
	
	public Raupe(int x, int y, Color farbe, int v) {
		this.x = x;
		this.y = y;
//		this.farbe = new Color(148, 0, 211);
		this.farbe = farbe;
		this.speed = v;
	}
	
// Methoden
	
	public int verdoppeln(int zahl) {
		return 2 * zahl;
	}
	
	public void zeichnen(Graphics g) {
		switch (langOderKurz++ % 6) {
		case 0, 1, 2:   // lange Raupe zeichnen
			g.setColor(farbe);
			g.fillOval(x, y, 20, 20);
			g.fillRect(x + 10, y + 3, 60, 15);
			g.fillOval(x + 60, y, 20, 20);
			break;
		case 3, 4, 5:   // kurze Raupe zeichnen
			g.setColor(farbe);
			g.fillOval(x + 10, y, 20, 20);
			g.fillRect(x + 20, y + 3, 40, 15);
			g.fillOval(x + 50, y, 20, 20);
			break;
		default:
			System.out.println(langOderKurz);
		}
//		langOderKurz++;
	}

	public void bewegen(int fensterbreite) {
		x = x + speed;
		if (x > fensterbreite) {
			x = -100;
		}
		
	}
	
}
