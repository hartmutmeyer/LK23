package tmpQ1;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextField;

public class CalculatorLeseThread extends Thread {

	private JTextField tfAusgabe;
	private InputStreamReader in;

	public CalculatorLeseThread(JTextField tfAusgabe, InputStreamReader in) {
		this.tfAusgabe = tfAusgabe;
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			int zeichen;
			char c;
			String ergebnis = "";
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				switch (c) {
				case '#':
					ergebnisAnzeigen();
					break;
				case '!':
					fehlermeldung();
					break;
				default:
					System.out.println("PROTOKOLLFEHLER");
				}
				ergebnis = ergebnis + c;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fehlermeldung() {
		
	}

	private void ergebnisAnzeigen() {		
		
	}

}
