import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Tierdatenbank2elegant extends JFrame {
	private static final int WIDTH = 450;
	private static final int HEIGHT = 200;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfTierart;
	private JTextField tfGeschlecht;
	private JTextField tfGeburtstag;
	private JTextField tfTodestag;
	private JButton btnVor;
	private JButton btnZurueck;
	private final static int ERSTES_TIER = 0;
	private final static int NAECHSTES_TIER = 1;
	private final static int VORHERIGES_TIER = 2;
	private ResultSet rs;

	public Tierdatenbank2elegant(final String title) {
		super(title);
		createGUI();
		datenbankAbfrage();
		tierAnzeigen(ERSTES_TIER);
	}

	private void datenbankAbfrage() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/haustier?serverTimezone=UTC", "root",
					"root");
			// Das Statement Objekt mit zusätzlichen (optionalen) Parametern
			// erzeugen, damit im ResultSetau auch rückwärts gesucht werden kann
			// mit rs.previous() 
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_SCROLL_SENSITIVE);
			String sql = "SELECT * from tier";
			System.out.println("datenbankAbfrage(): " + sql);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void tierAnzeigen(int suchRichtung) {
		try {
			switch (suchRichtung) {
			case ERSTES_TIER:
				if (!rs.next()) { // die Datenbank ist leer!
					JOptionPane.showMessageDialog(this, "Die Datenbank ist leer und enthält keine Tiere!");
					System.exit(0);
				}
				break;
			case NAECHSTES_TIER:
				rs.next();
				break;
			case VORHERIGES_TIER:
				// ACHTUNG: rs.previous() funktioniert nur, wenn das Statement-Objekt
				//          entsprechend erzeugt wurde (siehe Kommentar oben)  
				rs.previous();
			}
			// Jetzt sind wir beim richtigen Tier!
			tfId.setText(rs.getString("tier_id"));
			tfName.setText(rs.getString("name"));
			tfTierart.setText(rs.getString("tierart"));
			tfGeschlecht.setText(rs.getString("geschlecht"));
			tfGeburtstag.setText(rs.getString("geburtstag"));
			tfTodestag.setText(rs.getString("todestag"));
			// Die Buttons für vor und zurück aktivieren/deaktivieren
			if (rs.isLast()) {
				btnVor.setEnabled(false);
			} else {
				btnVor.setEnabled(true);
			}
			if (rs.isFirst()) {
				btnZurueck.setEnabled(false);
			} else {
				btnZurueck.setEnabled(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void vor() {
		tierAnzeigen(NAECHSTES_TIER);
	}

	public void zurueck() {
		tierAnzeigen(VORHERIGES_TIER);
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, WIDTH, HEIGHT);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("id:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(54, 14, 27, 15);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setHorizontalAlignment(SwingConstants.RIGHT);
		tfId.setBounds(89, 12, 114, 19);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(253, 14, 44, 15);
		contentPane.add(lblName);

		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setHorizontalAlignment(SwingConstants.RIGHT);
		tfName.setBounds(302, 12, 114, 19);
		contentPane.add(tfName);
		tfName.setColumns(10);

		JLabel lblTierart = new JLabel("Tierart:");
		lblTierart.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTierart.setBounds(26, 39, 55, 15);
		contentPane.add(lblTierart);

		tfTierart = new JTextField();
		tfTierart.setEditable(false);
		tfTierart.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTierart.setBounds(89, 37, 114, 19);
		contentPane.add(tfTierart);
		tfTierart.setColumns(10);

		JLabel lblGeschlecht = new JLabel("Geschlecht:");
		lblGeschlecht.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeschlecht.setBounds(218, 39, 79, 15);
		contentPane.add(lblGeschlecht);

		tfGeschlecht = new JTextField();
		tfGeschlecht.setEditable(false);
		tfGeschlecht.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGeschlecht.setBounds(302, 37, 114, 19);
		contentPane.add(tfGeschlecht);
		tfGeschlecht.setColumns(10);

		JLabel lblGeburtstag = new JLabel("Geburtstag:");
		lblGeburtstag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGeburtstag.setBounds(2, 66, 79, 15);
		contentPane.add(lblGeburtstag);

		tfGeburtstag = new JTextField();
		tfGeburtstag.setEditable(false);
		tfGeburtstag.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGeburtstag.setBounds(89, 64, 114, 19);
		contentPane.add(tfGeburtstag);
		tfGeburtstag.setColumns(10);

		JLabel lblTodestag = new JLabel("Todestag:");
		lblTodestag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTodestag.setBounds(218, 66, 79, 15);
		contentPane.add(lblTodestag);

		tfTodestag = new JTextField();
		tfTodestag.setEditable(false);
		tfTodestag.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTodestag.setBounds(302, 64, 114, 19);
		contentPane.add(tfTodestag);
		tfTodestag.setColumns(10);

		btnVor = new JButton("vor");
		btnVor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vor();
			}
		});
		btnVor.setBounds(105, 136, 98, 25);
		contentPane.add(btnVor);

		btnZurueck = new JButton("zurück");
		btnZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zurueck();
			}
		});
		btnZurueck.setEnabled(false);
		btnZurueck.setBounds(231, 136, 98, 25);
		contentPane.add(btnZurueck);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Tierdatenbank2elegant frame = new Tierdatenbank2elegant("Haustier-Datenbank: Aufgabe 2");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
