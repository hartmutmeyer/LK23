import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
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
		System.out.println("jetzt soll eine Fehlermeldung angezeigt werden");
		JOptionPane.showMessageDialog(null, "Falsche Eingabe - nur ganze Zahlen sind möglich!");
	}

	private void ergebnisAnzeigen() throws IOException {		
		System.out.println("jetzt soll das Ergebnis angezeigt werden");
		int zeichen;
		char c;
		String ergebnis = "";
		while ((zeichen = in.read()) != '$') {
			c = (char) zeichen;
			ergebnis += c;
		}
		System.out.println("Ergebnis gelesen: " + ergebnis);
		tfAusgabe.setText(ergebnis);
	}

}
