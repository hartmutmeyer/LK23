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
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class ListeSortieren extends JFrame {

	private JPanel contentPane;
	private JTextField tfNeuerWert;
	private DefaultListModel<String> werte = new DefaultListModel<String>();
	private JList<String> listWerte = new JList<String>(werte);
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeSortieren frame = new ListeSortieren();
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
	public ListeSortieren() {
		createGUI();
		Random zufall = new Random();
		for (int i = 0; i < 20; i++) {
			werte.addElement("" + zufall.nextInt(1000));
		}
	}

	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfNeuerWert = new JTextField();
		tfNeuerWert.setBounds(10, 11, 273, 20);
		contentPane.add(tfNeuerWert);
		tfNeuerWert.setColumns(10);

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 414, 301);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(listWerte);
		listWerte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
		// und dann zur nächsten Position (Variable start)
		String eintrag = "";
		int zahl;
		int kleinsterWert;
		int index = -1;
		int start = 0;

		for (int j = 0; j < werte.size(); j++) {
			// 1. Den kleinsten Wert aus der Liste suchen und als String speichern
			kleinsterWert = Integer.MAX_VALUE;
			for (int i = start; i < werte.size(); i++) {
				eintrag = werte.elementAt(i);
				zahl = Integer.parseInt(eintrag);
				System.out.println("j = " + j + ", i = " + i + ", zahl = " + zahl + ", kleinsterWert = " + kleinsterWert);
				if (zahl < kleinsterWert) {
					kleinsterWert = zahl;
					index = i;
				}
			}
			System.out.println(kleinsterWert + " an Position " + index);
			// 2. Das entsprechende Element aus dem Datenmodell löschen
			werte.remove(index);
			// 3. das Element an Position 0 in das Datenmodell einfügen
			werte.add(start, "" + kleinsterWert);
			start++;
		}

	}

	protected void eintragLoeschen() {
		if (!listWerte.isSelectionEmpty()) {
			int zuLoeschenderEintrag = listWerte.getSelectedIndex();
			werte.remove(zuLoeschenderEintrag);
		} else {
			System.out.println("Du hast nichts ausgewählt");
		}
	}

	protected void allesLoeschen() {
		werte.clear();
	}

	protected void hinzufuegen() {
		String neuerEintrag = tfNeuerWert.getText();
		boolean isInteger = true;
		for (int i = 0; i < neuerEintrag.length(); i++) {
			if (!Character.isDigit(neuerEintrag.charAt(i))) {
				isInteger = false;
			}
		}
		if (isInteger) {
			werte.addElement(neuerEintrag);
		} else {
			JOptionPane.showMessageDialog(this, "Nur ganze Zahlen als Eingabe erlaubt");
		}
		tfNeuerWert.setText("");
	}
}
