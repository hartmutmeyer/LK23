import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ZeichenZaehlen extends JFrame {
	// globale Variablen
	private static final int WIDTH = 585;
	private static final int HEIGHT = 235;
	private JLabel lblDateiname = new JLabel();
	private JTextField tfDateiname = new JTextField();
	private JButton btnZaehlen = new JButton();
	private JButton btnFileChooser = new JButton();
	private JFileChooser fileChooser = new JFileChooser();
	private JLabel lblAuswertung = new JLabel();
	private DefaultListModel<String> auswertung = new DefaultListModel<String>();
	private JList<String> listAuswertung = new JList<String>(auswertung);

	public ZeichenZaehlen() {
		createGUI();
	}
	
	private void createGUI() {
		setTitle("Zeichen Zählen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		lblDateiname.setBounds(16, 24, 91, 16);
		lblDateiname.setText("Dateiname:");
		contentPane.add(lblDateiname);
		tfDateiname.setBounds(125, 16, 300, 24);
		tfDateiname.setText("");
		contentPane.add(tfDateiname);
		btnZaehlen.setBounds(479, 16, 84, 25);
		btnZaehlen.setText("zählen");
		contentPane.add(btnZaehlen);
		btnZaehlen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				zaehlen();
			}
		});

		lblAuswertung.setBounds(16, 56, 91, 16);
		lblAuswertung.setText("Auswertung:");
		contentPane.add(lblAuswertung);
		listAuswertung.setBounds(16, 80, 547, 105);
		contentPane.add(listAuswertung);

		btnFileChooser.setText("...");
		btnFileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dateiWaehlen();
			}
		});
		btnFileChooser.setBounds(438, 17, 23, 23);
		contentPane.add(btnFileChooser);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void dateiWaehlen() {
		// Aufgabenteil noch nicht bearbeitet
	}

	public void zaehlen() {
		URL url = getClass().getResource(tfDateiname.getText()); // import java.net.URL
		System.out.println("Check: " + url);
		if (url == null || tfDateiname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Datei existiert nicht");
			return;
		}
		try (
			InputStream fileIn = new FileInputStream(URLDecoder.decode(url.getFile(), "UTF-8"));
			InputStreamReader in = new InputStreamReader(fileIn, "UTF-8");) {
			int zeichen;
			char c;
			int anzahlKleineBuchstaben = 0;
			int anzahlGrosseBuchstaben = 0;
			int anzahlZiffern = 0;
			int anzahlSonstige = 0;
			// Die Aufgabe wurde noch nicht komplett bearbeitet!
			while ((zeichen = in.read()) != -1) {
				c = (char) zeichen;
				if (Character.isLowerCase(c)) {
					anzahlKleineBuchstaben++;
				} else if (Character.isUpperCase(c)) {
					anzahlGrosseBuchstaben++;
				} else if (Character.isDigit(c)) {
					anzahlZiffern++;
				} else {
					anzahlSonstige++;
				}
			}
			auswertung.clear();
			auswertung.addElement("Anzahl Großbuchstaben: " + anzahlGrosseBuchstaben);
			auswertung.addElement("Anzahl Kleinbuchstaben: " + anzahlKleineBuchstaben);
			auswertung.addElement("Anzahl Ziffern: " + anzahlZiffern);		
			auswertung.addElement("Anzahl sonstige Zeichen: " + anzahlSonstige);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ZeichenZaehlen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}