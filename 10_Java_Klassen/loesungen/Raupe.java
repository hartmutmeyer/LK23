import java.awt.Color;
import java.awt.Graphics;

public class Raupe {
	private int x, y, vX, zaehler;
	private Color farbe;

	public Raupe(int x, int y, Color farbe, int vX) {
		this.x = x;
		this.y = y;
		this.farbe = farbe;
		this.vX = vX;
	}

	public void zeichnen(Graphics g) {
		switch (zaehler++ % 6) {
		case 0, 1, 2:
			g.setColor(farbe);
			g.fillOval(x, y, 20, 20);
			g.fillOval(x + 60, y, 20, 20);
			g.fillRect(x + 10, y, 60, 20);
		case 3, 4, 5:
			g.setColor(farbe);
			g.fillOval(x + 10, y, 20, 20);
			g.fillOval(x + 50, y, 20, 20);
			g.fillRect(x + 20, y, 40, 20);
		}
	}

	public void bewegen() {
		if ((x += vX) > RaupeAnw.WIDTH) {
			x = -100;
		}
	}
}
