package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.PadreJFrame;
import models.Usuario;
import services.Conexion;
import services.UsuarioService;

import java.awt.Color;

public class Registro extends JFrame {

	private JLabel lblUsername;
	private JTextField textUsername;
	private JLabel lblPsw;
	private JPasswordField textPsw;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JLabel lblDir;
	private JTextField textDir;
	private JLabel telfLabel;
	private JTextField textTelf;
	private PadreJFrame frame = new PadreJFrame();
	private JButton btnRegistro;
	private JButton btnVolver;
	private JButton btnCuenta;
	private final UsuarioService services = new UsuarioService();

	public Registro() {
		frame.setBounds(100, 100, 560, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setTitle("Registro");

		lblUsername = new JLabel("Usuario");
		lblUsername.setForeground(new Color(254, 255, 255));
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUsername.setBounds(105, 40, 99, 20);
		frame.getContentPane().add(lblUsername);

		textUsername = new JTextField();
		textUsername.setBackground(new Color(86, 147, 255));
		textUsername.setColumns(10);
		textUsername.setBounds(249, 40, 218, 19);
		frame.getContentPane().add(textUsername);

		lblPsw = new JLabel("Contraseña");
		lblPsw.setForeground(new Color(254, 255, 255));
		lblPsw.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPsw.setBounds(105, 88, 99, 20);
		frame.getContentPane().add(lblPsw);

		textPsw = new JPasswordField();
		textPsw.setBackground(new Color(62, 106, 180));
		textPsw.setColumns(10);
		textPsw.setBounds(249, 90, 218, 19);
		frame.getContentPane().add(textPsw);

		lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(254, 255, 255));
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEmail.setBounds(105, 135, 99, 20);
		frame.getContentPane().add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBackground(new Color(33, 76, 131));
		textEmail.setColumns(10);
		textEmail.setBounds(249, 135, 218, 19);
		frame.getContentPane().add(textEmail);

		lblDir = new JLabel("Dirección");
		lblDir.setForeground(new Color(254, 255, 255));
		lblDir.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDir.setBounds(105, 180, 99, 20);
		frame.getContentPane().add(lblDir);

		textDir = new JTextField();
		textDir.setBackground(new Color(255, 146, 0));
		textDir.setColumns(10);
		textDir.setBounds(249, 180, 218, 19);
		frame.getContentPane().add(textDir);

		telfLabel = new JLabel("Teléfono");
		telfLabel.setForeground(new Color(254, 255, 255));
		telfLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		telfLabel.setBounds(105, 229, 99, 20);
		frame.getContentPane().add(telfLabel);

		textTelf = new JTextField();
		textTelf.setText("Número de máximo 11 dígitos");
		textTelf.setBackground(new Color(231, 162, 83));
		textTelf.setColumns(10);
		textTelf.setBounds(249, 229, 218, 19);
		frame.getContentPane().add(textTelf);

		btnRegistro = new JButton("Registrarse");
		btnRegistro.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnRegistro.addActionListener(new BtnActionListener());
		btnRegistro.setBounds(130, 301, 117, 29);
		frame.getContentPane().add(btnRegistro);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnVolver.addActionListener(new BtnActionListener());
		btnVolver.setBounds(309, 301, 117, 29);
		frame.getContentPane().add(btnVolver);

		ImageIcon logo = new ImageIcon("imagenes/logo.png");
		Image image = logo.getImage();
		Image newImage = image.getScaledInstance(169, 132, Image.SCALE_SMOOTH);
		logo = new ImageIcon(newImage);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnRegistro) {
				updateUser();
			}
			if (e.getSource() == btnVolver) {
				frame.dispose();
				dispose();
			}
		}
	}

	private void updateUser() {

		try {
			Connection conexion = Conexion.obtener();

			boolean relleno = false;
			Usuario usu = new Usuario();
			String user = textUsername.getText();
			String password = textPsw.getText();
			String email = textEmail.getText();
			String direccion = textDir.getText();
			int telefono = 0;
			if (user.equals("") || password.equals("") || email.equals("") || direccion.equals("")
					|| textTelf.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"Por favor complete todos los campos y verifique que el nombre de usuario no esté en uso.");
			} else {
				try {
					telefono = Integer.parseInt(textTelf.getText());
					relleno = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Por favor, introduzca un número de teléfono válido",
							"ERROR TELÉFONO", JOptionPane.ERROR_MESSAGE);
				}
			}

			if (relleno) {

				List<Usuario> usuarios = services.getAllUsuarios(conexion); // Obtener todos los usuarios existentes
				int codigo = usuarios.stream().mapToInt(Usuario::getCodigo).max().orElse(0) + 1; // Obtener nuevo código

				String rol = "Usuario";

				usu = new Usuario(codigo, user, password, direccion, email, rol, telefono);
				usuarios.add(usu);

				services.saveUsuario(conexion, usu);

				JOptionPane.showMessageDialog(this, "Usuario creado correctamente");
				textUsername.setText("");
				textPsw.setText("");
				textEmail.setText("");
				textDir.setText("");
				textTelf.setText("");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this,
					"Ha surgido un error y no se han podido recuperar los registros de usuarios");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this,
					"Ha surgido un error y no se han podido recuperar los registros de usuarios");
		}

	}
}
