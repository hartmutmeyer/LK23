package tmpQ1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class CalculatorClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEingabe;
	private JTextField tfAusgabe;
	private JButton btnVerbinden;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnGleich;
	private OutputStreamWriter out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorClient frame = new CalculatorClient();
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
	public CalculatorClient() {
		createGUI();
	}
	
	private void createGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVerbinden = new JButton("Mit Server verbinden");
		btnVerbinden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbinden();
			}
		});
		btnVerbinden.setBounds(10, 11, 287, 23);
		contentPane.add(btnVerbinden);
		
		JLabel lblEingabe = new JLabel("Eingabe:");
		lblEingabe.setBounds(10, 54, 70, 14);
		contentPane.add(lblEingabe);
		
		tfEingabe = new JTextField();
		tfEingabe.setEditable(false);
		tfEingabe.setBounds(90, 51, 207, 20);
		contentPane.add(tfEingabe);
		tfEingabe.setColumns(10);
		
		btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plus();
			}
		});
		btnPlus.setEnabled(false);
		btnPlus.setBounds(10, 100, 89, 23);
		contentPane.add(btnPlus);
		
		btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minus();
			}
		});
		btnMinus.setEnabled(false);
		btnMinus.setBounds(109, 100, 89, 23);
		contentPane.add(btnMinus);
		
		btnGleich = new JButton("=");
		btnGleich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gleich();
			}
		});
		btnGleich.setEnabled(false);
		btnGleich.setBounds(208, 100, 89, 23);
		contentPane.add(btnGleich);
		
		JLabel lblAusgabe = new JLabel("Ausgabe:");
		lblAusgabe.setBounds(10, 153, 70, 14);
		contentPane.add(lblAusgabe);
		
		tfAusgabe = new JTextField();
		tfAusgabe.setEditable(false);
		tfAusgabe.setBounds(90, 150, 204, 20);
		contentPane.add(tfAusgabe);
		tfAusgabe.setColumns(10);
	}

	protected void gleich() {
		try {
			out.write("!$");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void minus() {
		try {
			out.write("-" + tfEingabe.getText() + "$");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	protected void plus() {
		try {
			out.write("+" + tfEingabe.getText() + "$");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void verbinden() {
		Socket s;
		try {
			s = new Socket ("localhost", 22222);
			InputStreamReader in = new InputStreamReader(s.getInputStream(), "UTF-8");
			out = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
			CalculatorLeseThread clt = new CalculatorLeseThread(tfAusgabe, in);
			clt.start();
			btnVerbinden.setEnabled(false);
			tfEingabe.setEditable(true);
			btnPlus.setEnabled(true);
			btnMinus.setEnabled(true);
			btnGleich.setEnabled(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
