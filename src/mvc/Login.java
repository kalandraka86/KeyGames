package mvc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField textUser;
	private JPasswordField textPsw;
	private JButton btnLogin;
	private JButton btnRegistro;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	


	public Login() {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 376);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(48, 10, 107, 23);
		panel.add(usernameLabel);
		
		textUser = new JTextField();
		textUser.setBounds(176, 14, 238, 19);
		panel.add(textUser);
		textUser.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(48, 46, 107, 23);
		panel.add(passwordLabel);
		
		
		textPsw = new JPasswordField();
		textPsw.setColumns(10);
		textPsw.setBounds(176, 48, 238, 19);
		panel.add(textPsw);
		
		ManejadorLogin manejador = new ManejadorLogin();
		
		btnLogin = new JButton("Iniciar sesión");
		btnLogin.setBounds(242, 85, 136, 21);
		btnLogin.addActionListener(manejador);
		panel.add(btnLogin);
		
		JLabel registerLabel = new JLabel("¿Aún no estás registrado?");
		registerLabel.setBounds(42, 131, 169, 19);
		panel.add(registerLabel);
		
		btnRegistro = new JButton("Registrarse");
		btnRegistro.setBounds(242, 130, 136, 21);
		btnRegistro.addActionListener(manejador);
		panel.add(btnRegistro);
		
		ImageIcon logo = new ImageIcon("imagenes/logo.png");
		Image image = logo.getImage(); 
		Image newImage = image.getScaledInstance(169, 132, Image.SCALE_SMOOTH); 
		logo = new ImageIcon(newImage); 

		
		JLabel imagenLabel = new JLabel();
		imagenLabel.setIcon(logo);
		imagenLabel.setBounds(128, 171, 198, 158);
		panel.add(imagenLabel);
		
        
        setVisible(true);
	}

	
	class ManejadorLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user = textUser.getText();
			String password = textPsw.getText();
			
			if(e.getSource() == btnRegistro) {
				Registro frame = new Registro();
				frame.setVisible(true);
				dispose();
			}
			
			if(e.getSource() == btnLogin) {
				boolean esUsuario = getUser();
				if (esUsuario == true) {
					// Ventana frame = new Ventana();
					// dispose();
				}
			}
			
		}
		
	}
	
	private boolean getUser() {
		
		for(Usuario u: usuarios) {
			String user = textUser.getText();
			String password = textPsw.getText();
			if(u.getUsername().equals(user) && u.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
