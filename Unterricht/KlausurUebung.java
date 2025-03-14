package tmpQ1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class KlausurUebung extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEingabe;
	private JTextField tfAusgabe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KlausurUebung frame = new KlausurUebung();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KlausurUebung() {
		createGUI();
	}
	
	private void createGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDateiLesen = new JButton("Datei Lesen");
		btnDateiLesen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lesenUndRechnen();
			}
		});
		btnDateiLesen.setBounds(165, 11, 133, 23);
		contentPane.add(btnDateiLesen);
		
		tfEingabe = new JTextField();
		tfEingabe.setEditable(false);
		tfEingabe.setBounds(168, 151, 86, 20);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		tfAusgabe = new JTextField();
		tfAusgabe.setEditable(false);
		tfAusgabe.setBounds(168, 182, 86, 20);
		contentPane.add(tfAusgabe);
		tfAusgabe.setColumns(10);
		
		JLabel lblEingabe = new JLabel("Eingabe:");
		lblEingabe.setBounds(89, 154, 69, 14);
		contentPane.add(lblEingabe);
		
		JLabel lblAusgabe = new JLabel("Ausgabe:");
		lblAusgabe.setBounds(89, 185, 69, 14);
		contentPane.add(lblAusgabe);
	}

	protected void lesenUndRechnen() {
		// Datei einlesen
		URL url = getClass().getResource("eingabe.txt"); // import java.net.URL

		try (InputStream fileIn = new FileInputStream(URLDecoder.decode(url.getFile(), "UTF-8"));
			InputStreamReader in = new InputStreamReader(fileIn, "UTF-8")) {
			// Sachen mit der Datei tun ...
			String dateiInhalt = "";
			int zeichen;
			char c;
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				dateiInhalt = dateiInhalt + c;
			}
			tfEingabe.setText(dateiInhalt);
			
			// eingelesene Zahl verdoppeln
			double zahl = Double.parseDouble(dateiInhalt);
			double ergebnis = 2 * zahl;
			
			// Ergebnis ausgeben ...
			// ... in Textfeld
			tfAusgabe.setText("" + ergebnis);
			// ... und in Datei
			url = getClass().getResource("ausgabe.txt"); // import java.net.URL
			OutputStream fileOut = new FileOutputStream(URLDecoder.decode(url.getFile(), "UTF-8"));
			OutputStreamWriter out = new OutputStreamWriter(fileOut, "UTF-8");
			out.write("" + ergebnis);
			out.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
