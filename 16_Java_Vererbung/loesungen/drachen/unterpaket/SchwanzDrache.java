import java.awt.Color;
import java.awt.Graphics;

public class SchwanzDrache extends GesichtDrache {
	
	private Color schwanzFarbe;

	public SchwanzDrache(int x, int y, Color farbe, Color schwanzFarbe) {
		super(x, y, farbe);
		this.schwanzFarbe = schwanzFarbe;
	}
	
	@Override
	public void zeichnen(Graphics g) {
		super.zeichnen(g);
		// Den Schwanz des Drachens zeichnen
		g.setColor(schwanzFarbe);
		g.fillOval(x + 30, y + 120, 20, 20);
		g.fillOval(x + 30, y + 140, 20, 20);
		g.fillOval(x + 30, y + 160, 20, 20);
	}
		
}
