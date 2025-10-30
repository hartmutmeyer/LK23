import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class TierdatenbankWiederholung extends JFrame {

	private JPanel contentPane;
	private JTextField tfBesitzerID;
	private JTextField tfVorname;
	private JTextField tfNachname;
	private DefaultListModel<String> tiere = new DefaultListModel<String>();
	private JList<String> listTiere = new JList<String>(tiere);

	public TierdatenbankWiederholung() {
		createGUI();
	}

	protected void besitzerLaden() {
		dbBesitzerLaden();
	}

	protected void besitzerLoeschen() {
		dbBesitzerLoeschen();
	}

	private void dbBesitzerLaden() {
		ResultSet rs = null;
		String cmdSQL;
		String besitzerID = tfBesitzerID.getText();

		cmdSQL = "SELECT vorname, nachname, name, tierart FROM tier, besitzer, beziehung WHERE besitzer_id = ";
		cmdSQL = cmdSQL + besitzerID;
		cmdSQL = cmdSQL + " AND beziehung_tier_id = tier_id";
		cmdSQL = cmdSQL + " AND beziehung_besitzer_id = besitzer_id";
		cmdSQL = cmdSQL + " AND lebendig='ja'";

		System.out.println(cmdSQL);

		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/haustier?serverTimezone=UTC", "root", "root");
			Statement stmt = conn.createStatement()
		) {
			rs = stmt.executeQuery(cmdSQL);
			if (rs.isBeforeFirst()) {
				tiere.clear();
				while (rs.next()) {
					tiere.addElement(rs.getString("name") + " ("
							+ rs.getString("tierart") + ")");
					tfVorname.setText(rs.getString("vorname"));
					tfNachname.setText(rs.getString("nachname"));
				}
			} else {
				tiere.clear();
				tfVorname.setText("");
				tfNachname.setText("");
				System.out.println("Einen Besitzer mit dieser ID gibt es nicht!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dbBesitzerLoeschen() {
		int ergebnis = 0;
		String sql;
		String besitzerID = tfBesitzerID.getText();

		sql = "DELETE FROM besitzer WHERE besitzer_id = " + besitzerID;

		System.out.println(sql);

		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/haustier?serverTimezone=UTC", "root", "root");
			Statement stmt = conn.createStatement()
		) {
			ergebnis = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tiere.clear();
		tfVorname.setText("");
		tfNachname.setText("");
		if (ergebnis == 1) {
			System.out.println("Der Besitzer mit der ID " + besitzerID + " wurde gelöscht.");
		} else {
			System.out.println("Einen Besitzer mit dieser ID gibt es nicht!");
		}
	}
	
	private void createGUI() {
		setTitle("Haustier-Datenbank: Wiederholung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBesitzerNr = new JLabel("BesitzerNr:");
		lblBesitzerNr.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBesitzerNr.setBounds(12, 12, 69, 15);
		contentPane.add(lblBesitzerNr);

		tfBesitzerID = new JTextField();
		lblBesitzerNr.setLabelFor(tfBesitzerID);
		tfBesitzerID.setBounds(84, 10, 47, 19);
		contentPane.add(tfBesitzerID);
		tfBesitzerID.setColumns(10);

		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblVorname.setBounds(12, 52, 61, 15);
		contentPane.add(lblVorname);

		tfVorname = new JTextField();
		tfVorname.setEditable(false);
		lblVorname.setLabelFor(tfVorname);
		tfVorname.setBounds(84, 50, 114, 19);
		contentPane.add(tfVorname);
		tfVorname.setColumns(10);

		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNachname.setBounds(224, 52, 69, 15);
		contentPane.add(lblNachname);

		tfNachname = new JTextField();
		tfNachname.setEditable(false);
		lblNachname.setLabelFor(tfNachname);
		tfNachname.setBounds(300, 50, 134, 19);
		contentPane.add(tfNachname);
		tfNachname.setColumns(10);

		JLabel lblTiere = new JLabel("Tiere:");
		lblTiere.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTiere.setBounds(12, 100, 55, 15);
		contentPane.add(lblTiere);

		listTiere.setBounds(12, 121, 422, 142);
		contentPane.add(listTiere);

		JButton btnLaden = new JButton("Laden");
		btnLaden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				besitzerLaden();
			}
		});
		btnLaden.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLaden.setBounds(224, 7, 95, 25);
		contentPane.add(btnLaden);

		JButton btnLoeschen = new JButton("Löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				besitzerLoeschen();
			}
		});
		btnLoeschen.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLoeschen.setBounds(339, 7, 95, 25);
		contentPane.add(btnLoeschen);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TierdatenbankWiederholung frame = new TierdatenbankWiederholung();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
