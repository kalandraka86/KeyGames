package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import models.PadreJFrame;
import models.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MiCuenta {

	private PadreJFrame frame = new PadreJFrame();
	private JLabel lblUsername;
	private JLabel lblPsw;
	private JLabel lblEmail;
	private JLabel lblDir;
	private JLabel telfLabel;
	private JTextField passwdField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnVolver;
	private Inicio inicio;

	public MiCuenta(Inicio i) throws ClassNotFoundException, SQLException {
		inicio = i;
		Usuario u = inicio.seleccionadoUsuario();
		frame.setTitle("Datos de "+u.getUsername());
		frame.setBounds(100, 100, 452, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblUsername = new JLabel("Usuario");
		lblUsername.setForeground(new Color(254, 255, 255));
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUsername.setBounds(67, 22, 99, 20);
		frame.getContentPane().add(lblUsername);

		lblPsw = new JLabel("Contraseña");
		lblPsw.setForeground(new Color(254, 255, 255));
		lblPsw.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPsw.setBounds(67, 70, 99, 20);
		frame.getContentPane().add(lblPsw);

		lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(254, 255, 255));
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEmail.setBounds(67, 117, 99, 20);
		frame.getContentPane().add(lblEmail);

		lblDir = new JLabel("Dirección");
		lblDir.setForeground(new Color(254, 255, 255));
		lblDir.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDir.setBounds(67, 162, 99, 20);
		frame.getContentPane().add(lblDir);

		telfLabel = new JLabel("Teléfono");
		telfLabel.setForeground(new Color(254, 255, 255));
		telfLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		telfLabel.setBounds(67, 211, 99, 20);
		frame.getContentPane().add(telfLabel);

		passwdField = new JTextField();
		passwdField.setEditable(false);
		passwdField.setText(u.getPassword());
		passwdField.setColumns(10);
		passwdField.setBackground(new Color(62, 106, 180));
		passwdField.setBounds(208, 72, 218, 19);
		frame.getContentPane().add(passwdField);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText(u.getUsername());
		textField.setColumns(10);
		textField.setBackground(new Color(86, 147, 255));
		textField.setBounds(208, 22, 218, 19);
		frame.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setText(u.getCorreo());
		textField_1.setBackground(new Color(33, 76, 131));
		textField_1.setBounds(208, 117, 218, 19);
		frame.getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setText(u.getDireccion());
		textField_2.setBackground(new Color(255, 146, 0));
		textField_2.setBounds(208, 162, 218, 19);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText(String.valueOf(u.getTelefono()));
		textField_3.setColumns(10);
		textField_3.setBackground(new Color(231, 162, 83));
		textField_3.setBounds(208, 211, 218, 19);
		frame.getContentPane().add(textField_3);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new BtnVolverActionListener());
		btnVolver.setBounds(166, 256, 117, 29);
		frame.getContentPane().add(btnVolver);
		
        frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	private class BtnVolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
}
