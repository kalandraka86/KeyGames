package examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Principal {

	private PadreJFrame frame;
	private JButton btnDetalles;
	private JPanel panelCaratula;
	private JComboBox comboBox;
	private JLabel lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new PadreJFrame();
		frame.setTitle("Catálogo");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnDetalles = new JButton("Ver detalles");
		btnDetalles.setBounds(315, 105, 100, 69);
		frame.getContentPane().add(btnDetalles);
		
		panelCaratula = new JPanel((LayoutManager) null);
		panelCaratula.setBounds(100, 66, 119, 161);
		frame.getContentPane().add(panelCaratula);
		panelCaratula.setLayout(new BorderLayout());
		
		lbl = new JLabel(new ImageIcon("ej"));
		panelCaratula.add(lbl, BorderLayout.CENTER);
		
		comboBox = new JComboBox();
		comboBox.setBounds(60, 27, 200, 27);
		frame.getContentPane().add(comboBox);
		btnDetalles.addActionListener(new BtnNewButtonActionListener());
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
}
