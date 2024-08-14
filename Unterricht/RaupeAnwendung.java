package tmpQ1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

import hilfe.*;

public class RaupeAnwendung extends HJFrame {
	// globale Variablen
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final Color BACKGROUND = Color.WHITE;
	private static final Color FOREGROUND = Color.BLACK;
	private Raupe olli;
	private Raupe lena;

	public RaupeAnwendung(final String title) {
		super(WIDTH, HEIGHT, BACKGROUND, FOREGROUND, title);
		// eigene Initialisierung
		olli = new Raupe(250, 250, Color.GREEN, 3);
		lena = new Raupe(200, 200, Color.PINK, 10);
		Timer timer = new Timer(100, this);
		timer.start();
	}

	@Override
	public void myPaint(Graphics g) {
		// wird aufgerufen, wenn das Fenster neu gezeichnet wird
		olli.zeichnen(g);
		olli.bewegen(WIDTH);
		lena.zeichnen(g);
		lena.bewegen(WIDTH);
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaupeAnwendung anwendung = new RaupeAnwendung("RaupeAnwendung");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}