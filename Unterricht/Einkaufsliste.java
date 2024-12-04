import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Einkaufsliste extends JFrame {

	private JPanel contentPane;
	private JTextField tfNeuerEintrag;
	private DefaultListModel<String> einkaeufe = new DefaultListModel<String>();
	private JList<String> listEinkaufe = new JList<String>(einkaeufe);
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Einkaufsliste frame = new Einkaufsliste();
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
	public Einkaufsliste() {
		createGUI();
		einkaeufe.addElement("123");
		einkaeufe.addElement("aber");
		String nachricht = einkaeufe.elementAt(1).toUpperCase();
		JOptionPane.showMessageDialog(this, nachricht);
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfNeuerEintrag = new JTextField();
		tfNeuerEintrag.setBounds(10, 11, 273, 20);
		contentPane.add(tfNeuerEintrag);
		tfNeuerEintrag.setColumns(10);

		JButton btnHinzufuegen = new JButton("Hinzufügen");
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinzufuegen();
			}
		});
		btnHinzufuegen.setBounds(293, 10, 131, 23);
		contentPane.add(btnHinzufuegen);

		JButton btnLoeschen = new JButton("Eintrag Löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eintragLoeschen();
			}
		});
		btnLoeschen.setBounds(293, 354, 131, 23);
		contentPane.add(btnLoeschen);
		listEinkaufe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listEinkaufe.setBounds(10, 42, 414, 301);
		contentPane.add(listEinkaufe);

		btnClear = new JButton("Alles Löschen");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allesLoeschen();
			}
		});
		btnClear.setBounds(152, 354, 131, 23);
		contentPane.add(btnClear);

		JButton btnSortieren = new JButton("Sortieren");
		btnSortieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortieren();
			}
		});
		btnSortieren.setBounds(10, 354, 132, 23);
		contentPane.add(btnSortieren);
	}

	protected void sortieren() {
		// In einer Schleife
		// und dann zur nächsten Position
		String eintrag = "";
		int zahl;
		int kleinsterWert = 999999999;
		int index = -1;
		int start = 0;

		// 1. Den kleinsten Wert aus der Liste suchen und als String speichern
		for (int i = 0; i < einkaeufe.size(); i++) {
			for (int j = start; j < einkaeufe.size(); j++) {
				eintrag = einkaeufe.elementAt(i);
				zahl = Integer.parseInt(eintrag);
				if (zahl < kleinsterWert) {
					kleinsterWert = zahl;
					index = i;
				}
			}
			System.out.println(kleinsterWert + " an Position " + index);
			// 2. Das entsprechende Element aus dem Datenmodell löschen
			einkaeufe.remove(index);
			// 3. das Element an Position 0 in das Datenmodell einfügen
			einkaeufe.add(0, "" + kleinsterWert);
			start++;
		}

	}

	protected void eintragLoeschen() {
		if (!listEinkaufe.isSelectionEmpty()) {
			int zuLoeschenderEintrag = listEinkaufe.getSelectedIndex();
			einkaeufe.remove(zuLoeschenderEintrag);
		} else {
			System.out.println("Du hast nichts ausgewählt");
		}
	}

	protected void allesLoeschen() {
		einkaeufe.clear();
	}

	protected void hinzufuegen() {
		String neuerEintrag = tfNeuerEintrag.getText();
		einkaeufe.addElement(neuerEintrag);
	}
}
