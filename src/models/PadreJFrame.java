package models;

import java.awt.Color;

import javax.swing.JFrame;

public class PadreJFrame extends JFrame {

	public PadreJFrame() {
		super("Genérico");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(72, 79, 84, 255));
	}
}
