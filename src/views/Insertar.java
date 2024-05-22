package views;

import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import models.Usuario;
import services.Conexion;
import services.UsuarioService;

public class Insertar extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton volver;
	private JButton insertar;
	private JButton eliminar;
	private JButton editar;
	private List<Usuario> usuarios = new ArrayList<>();
	private UsuarioService userService = new UsuarioService();
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
	private JButton btnInsertar;
	private JButton btnVolver;
	
	public Insertar() throws HeadlessException {
		setBounds(100, 100, 560, 402);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		lblUsername = new JLabel("Usuario");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUsername.setBounds(105, 40, 99, 20);
		getContentPane().add(lblUsername);

		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(249, 40, 218, 19);
		getContentPane().add(textUsername);

		lblPsw = new JLabel("Contraseña");
		lblPsw.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPsw.setBounds(105, 90, 99, 20);
		getContentPane().add(lblPsw);

		textPsw = new JPasswordField();
		textPsw.setColumns(10);
		textPsw.setBounds(249, 90, 218, 19);
		getContentPane().add(textPsw);

		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEmail.setBounds(105, 131, 99, 20);
		getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(249, 135, 218, 19);
		getContentPane().add(textEmail);

		lblDir = new JLabel("Dirección");
		lblDir.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDir.setBounds(105, 180, 99, 20);
		getContentPane().add(lblDir);

		textDir = new JTextField();
		textDir.setColumns(10);
		textDir.setBounds(249, 180, 218, 19);
		getContentPane().add(textDir);

		telfLabel = new JLabel("Teléfono");
		telfLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		telfLabel.setBounds(105, 229, 99, 20);
		getContentPane().add(telfLabel);

		textTelf = new JTextField();
		textTelf.setColumns(10);
		textTelf.setBounds(249, 229, 218, 19);
		getContentPane().add(textTelf);

		btnInsertar = new JButton("Insertar");
		btnInsertar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnInsertar.addActionListener(new BtnActionListener());
		btnInsertar.setBounds(130, 301, 117, 29);
		getContentPane().add(btnInsertar);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnVolver.addActionListener(new BtnActionListener());
		btnVolver.setBounds(309, 301, 117, 29);
		getContentPane().add(btnVolver);

		ImageIcon logo = new ImageIcon("imagenes/logo.png");
		Image image = logo.getImage();
		Image newImage = image.getScaledInstance(169, 132, Image.SCALE_SMOOTH);
		logo = new ImageIcon(newImage);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnInsertar) {
				updateUser();
			}
			if (e.getSource() == btnVolver) {
				dispose();
				new UsuarioFrame().setVisible(true);
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
					JOptionPane.showMessageDialog(Insertar.this,
							"Por favor complete todos los campos y verifique que el nombre de usuario no esté en uso.");
				} else {
					telefono = Integer.parseInt(textTelf.getText());
					relleno = true;
				}

				if (relleno) {

					List<Usuario> usuarios = userService.getAllUsuarios(conexion);
					int codigo = usuarios.stream().mapToInt(Usuario::getCodigo).max().orElse(0) + 1;

					String rol = "Usuario";

					usu = new Usuario(codigo, user, password, direccion, email, rol, telefono);
					usuarios.add(usu);

					userService.saveUsuario(conexion, usu);

					textUsername.setText("");
					textPsw.setText("");
					textEmail.setText("");
					textDir.setText("");
					textTelf.setText("");
					
					dispose();
					new UsuarioFrame().setVisible(true);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(Insertar.this, "Ha surgido un error y no se han podido recuperar los registros");
			} catch (ClassNotFoundException ex) {
				System.out.println(ex);
				JOptionPane.showMessageDialog(Insertar.this, "Ha surgido un error y no se han podido recuperar los registros");
			}

		}
			
	}
}
	