package tmpQ1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class RaupeAnwendnung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Raupe tim;
	private Raupe mia;
	private Raupe erwin;
	private Hund bruno;

	public RaupeAnwendnung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		tim = new Raupe(200, 200, Color.BLUE, 5);
		mia = new Raupe(100, 100, Color.PINK, 2);
		erwin = new Raupe(300, 300, Color.YELLOW, 1);
		bruno = new Hund(400, 500, Color.RED);
		Timer timer = new Timer(10, this);
		timer.start();
	}
	
	
	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		tim.zeichnen(g);
		tim.bewegen(WIDTH);
		mia.zeichnen(g);
		mia.bewegen(WIDTH);
		erwin.zeichnen(g);
		erwin.bewegen(WIDTH);
		bruno.stehen(g);
		bruno.bewegen(4, WIDTH);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaupeAnwendnung anwendung = new RaupeAnwendnung("RaupeAnwendnung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}