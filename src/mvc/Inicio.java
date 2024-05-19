package mvc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Inicio extends JFrame {

	private JTextField nameText;
	private JLabel nameLabel;
	private JLabel passwdLabel;
	private JButton btnConectar;
	private JPasswordField passText;
	private JButton btnSalir;
	private JButton btnRegistrar;

	public Inicio() {
		super("LOG IN");
		initialize();
	}

	public static void main(String[] args) {
		new Inicio();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Establecer el color de fondo
		getContentPane().setBackground(new Color(72, 79, 84, 255));
		nameText = new JTextField();
		nameText.setBackground(new Color(10, 76, 136));
		nameText.setHorizontalAlignment(SwingConstants.CENTER);
		nameText.setBounds(155, 53, 145, 26);
		getContentPane().add(nameText);
		nameText.setColumns(10);

		nameLabel = new JLabel("Usuario");
		nameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		nameLabel.setBounds(41, 41, 52, 51);
		nameLabel.setForeground(Color.white);
		getContentPane().add(nameLabel);

		passwdLabel = new JLabel("Contraseña");
		passwdLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		passwdLabel.setBounds(41, 129, 80, 16);
		passwdLabel.setForeground(Color.white);
		getContentPane().add(passwdLabel);

		btnConectar = new JButton("Conectar");
		btnConectar.setForeground(Color.black);
		btnConectar.addActionListener(new btnActionListener());
		btnConectar.setBounds(186, 201, 80, 29);
		getContentPane().add(btnConectar);

		passText = new JPasswordField();
		passText.setBackground(new Color(231, 162, 83));
		passText.setHorizontalAlignment(SwingConstants.CENTER);
		passText.addActionListener(new btnActionListener());
		passText.setBounds(155, 124, 145, 26);
		getContentPane().add(passText);

		ImageIcon img = new ImageIcon("files/salir.png");

		btnSalir = new JButton(img);
		btnSalir.setBounds(41, 174, 50, 50);
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.addActionListener(new btnActionListener());
		getContentPane().add(btnSalir);

		btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.addActionListener(new btnActionListener());
		btnRegistrar.setBounds(339, 16, 94, 29);
		getContentPane().add(btnRegistrar);
		setResizable(false);
		setVisible(true);
	}

	private class btnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSalir) {
				JOptionPane.showMessageDialog(null, "Gracias por usar nuestro programa :)");
				System.exit(0);
			}
			if (e.getSource() == btnConectar) {
				try {
					File soundFile = new File("files/lujazo.wav");
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (e.getSource() == btnRegistrar) {
				new Registro();
			}
		}
	}
}
