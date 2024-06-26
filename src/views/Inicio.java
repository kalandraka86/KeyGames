package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Usuario;
import services.Conexion;
import services.UsuarioService;

public class Inicio extends JFrame {

	private JTextField nameText;
	private JLabel nameLabel;
	private JLabel passwdLabel;
	private JButton btnConectar;
	private JPasswordField passText;
	private JButton btnSalir;
	private JButton btnRegistrar;
	private UsuarioService service = new UsuarioService();
	private Conexion conex = new Conexion();

	public Inicio() throws ClassNotFoundException, SQLException {
		super("Log in");
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
		btnConectar.addActionListener(new BtnActionListener());
		btnConectar.setBounds(186, 201, 80, 29);
		getContentPane().add(btnConectar);

		passText = new JPasswordField();
		passText.setBackground(new Color(231, 162, 83));
		passText.setHorizontalAlignment(SwingConstants.CENTER);
		passText.addActionListener(new BtnActionListener());
		passText.setBounds(155, 124, 145, 26);
		getContentPane().add(passText);

		ImageIcon img = new ImageIcon("imagenes/salir.png");

		btnSalir = new JButton(img);
		btnSalir.setBounds(41, 174, 50, 50);
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.addActionListener(new BtnActionListener());
		getContentPane().add(btnSalir);

		btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setForeground(Color.BLACK);
		btnRegistrar.addActionListener(new BtnActionListener());
		btnRegistrar.setBounds(339, 16, 94, 29);
		getContentPane().add(btnRegistrar);

		setResizable(false);
		setVisible(true);
	}

	private class BtnActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == btnSalir) {
	            JOptionPane.showMessageDialog(null, "Gracias por usar nuestro programa :)");
	            System.exit(0);
	        }
	        if (e.getSource() == btnConectar) {
	            try {
	                if (service.validarUsuario(conex.obtener(), nameText.getText(), new String(passText.getPassword()))) {
	                    JOptionPane.showMessageDialog(null, "Loggeo con éxito!!!", "LOGEANDO", JOptionPane.INFORMATION_MESSAGE);

	                    
	                    String username = nameText.getText();
	                    if (service.esAdmin(conex.obtener(), username)) {
	                        new ListVideojuegos().setVisible(true);
	                    } else {
	                    	new Principal(Inicio.this);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "Compruebe que están bien introducidos los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (ClassNotFoundException | SQLException e1) {
	                JOptionPane.showMessageDialog(null, "Este usuario no está en la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
	                e1.printStackTrace();
	            }
	        }

	        if (e.getSource() == btnRegistrar) {
	            new Registro();
	        }
	    }
	}


	// Método que devuelve el código de usuario
	public int codUsuario() throws ClassNotFoundException, SQLException {
		return service.getCodigoUsuario(conex.obtener(), nameText.getText(), new String(passText.getPassword()));
	}

	public Usuario seleccionadoUsuario() throws ClassNotFoundException, SQLException {
		return service.getUsuario(conex.obtener(), nameText.getText(), new String(passText.getPassword()));
	}
}