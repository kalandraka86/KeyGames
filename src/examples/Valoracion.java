package examples;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Valoracion {

	private PadreJFrame frame = new PadreJFrame();
	private JLabel lblTitulo;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Valoracion window = new Valoracion();
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
	public Valoracion() {
		frame.setTitle("Valoración");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("New label");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(30, 28, 356, 26);
		frame.getContentPane().add(lblTitulo);
		
		textArea = new JTextArea();
		textArea.setBounds(30, 68, 283, 107);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(30, 68, 283, 107);
        frame.getContentPane().add(scrollPane);


	}
}
