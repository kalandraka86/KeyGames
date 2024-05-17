package mvc;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField textUsername;
	private JPasswordField textPsw;
	private JTextField textEmail;
	private JTextField textDir;
	private JButton btnFin;
	private JButton btnLog;
	private ImageIcon logo = new ImageIcon("imagenes/logo.png");
	private JTextField textTelf;
	private final UsuarioService services = new UsuarioService();
	private List<Usuario> usuario;
	
	
	public Registro() {
		super("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 416);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(48, 10, 99, 20);
		panel.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(186, 11, 218, 19);
		panel.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblPsw = new JLabel("Password: ");
		lblPsw.setBounds(48, 40, 99, 20);
		panel.add(lblPsw);
		
		textPsw = new JPasswordField();
		textPsw.setBounds(186, 40, 218, 19);
		panel.add(textPsw);
		textPsw.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(48, 70, 99, 20);
		panel.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(186, 101, 218, 19);
		panel.add(textEmail);
		
		JLabel lblDir = new JLabel("Dirección: ");
		lblDir.setBounds(48, 100, 99, 20);
		panel.add(lblDir);
		
		textDir = new JTextField();
		textDir.setColumns(10);
		textDir.setBounds(186, 71, 218, 19);
		panel.add(textDir);
		
		ManejadorRegistro manejador = new ManejadorRegistro();
		
		btnFin = new JButton("Registrarse: ");
		btnFin.setBounds(38, 184, 152, 20);
		btnFin.addActionListener(manejador);
		panel.add(btnFin);
		
		btnLog = new JButton("Volver: ");
		btnLog.setBounds(252, 184, 146, 20);
		btnLog.addActionListener(manejador);
		panel.add(btnLog);
		
		ImageIcon logo = new ImageIcon("imagenes/logo.png");
		Image image = logo.getImage(); 
		Image newImage = image.getScaledInstance(169, 132, Image.SCALE_SMOOTH); 
		logo = new ImageIcon(newImage); 

		
		JLabel imagenLabel = new JLabel();
		imagenLabel.setIcon(logo);
		imagenLabel.setBounds(127, 214, 218, 143);
		panel.add(imagenLabel);
		
		JLabel telfLabel = new JLabel("Teléfono: ");
		telfLabel.setBounds(48, 135, 99, 20);
		panel.add(telfLabel);
		
		textTelf = new JTextField();
		textTelf.setColumns(10);
		textTelf.setBounds(186, 136, 218, 19);
		panel.add(textTelf);
		
		setVisible(true);
	}
	
	class ManejadorRegistro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			 
			if(e.getSource() == btnLog) {
				Login frame = new Login();
			    dispose();
			} 
			if(e.getSource()==btnFin){
				updateUser();
				Login frame = new Login();
				dispose();
			}
		}
		
	}
	
	
	private void updateUser() {
		
			try {
		        Connection conexion = Conexion.obtener();
		        Usuario usu = new Usuario();
		        String user = textUsername.getText();
		        String password = textPsw.getText();
		        String email = textEmail.getText();
		        String direccion = textDir.getText();
		        int telefono = Integer.parseInt(textTelf.getText());

		        List<Usuario> usuarios = services.getAllUsuarios(conexion); // Obtener todos los usuarios existentes
		        int codigo = usuarios.stream().mapToInt(Usuario::getCodigo).max().orElse(0) + 1; // Obtener nuevo código

		        String rol = "Usuario";

		        if (user.equals("") || password.equals("") || email.equals("") || direccion.equals("") || textTelf.getText().equals("")) {
		            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos y verifique que el nombre de usuario no esté en uso.");
		        } else {
		            usu = new Usuario(codigo, user, password, direccion, email, rol, telefono);
		            usuarios.add(usu);

		            services.save(conexion, usu);

		            JOptionPane.showMessageDialog(this, "Usuario creado correctamente");
		            textUsername.setText("");
		            textPsw.setText("");
		            textEmail.setText("");
		            textDir.setText("");
		            textTelf.setText("");
		        }

		    } catch (SQLException ex) {
		        System.out.println(ex.getMessage());
		        JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		    } catch (ClassNotFoundException ex) {
		        System.out.println(ex);
		        JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		    }
		
		
	}
}
