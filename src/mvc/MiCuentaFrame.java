package mvc;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class MiCuentaFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Usuario> usuarios = new ArrayList<>();
	private UsuarioService userService = new UsuarioService();
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textRol;
	private JTextField textCorreo;
	private JLabel lblNombre;
	private JLabel lblDireccion;
	private JLabel lblTelefono;
	private JLabel lblRol;
	private JLabel lblCorreo;
	private ImageIcon logo = new ImageIcon("imagenes/logo.png");
	private JLabel lblImagen;
	private JLabel lblBtn;
	private JButton btnVolver;


	public static void main(String[] args) {
		MiCuentaFrame frame = new MiCuentaFrame();
	}

	
	public MiCuentaFrame() {
		super("Mis datos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(42, 35, 127, 25);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(195, 30, 204, 30);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		lblDireccion = new JLabel("Dirección: ");
		lblDireccion.setBounds(42, 76, 127, 25);
		contentPane.add(lblDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(195, 74, 204, 30);
		contentPane.add(textDireccion);
		
		lblTelefono = new JLabel("Teléfono: ");
		lblTelefono.setBounds(42, 122, 127, 25);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(195, 120, 204, 30);
		contentPane.add(textTelefono);
		
		lblRol = new JLabel("Rol: ");
		lblRol.setBounds(42, 164, 127, 25);
		contentPane.add(lblRol);
		
		textRol = new JTextField();
		textRol.setColumns(10);
		textRol.setBounds(195, 162, 204, 30);
		contentPane.add(textRol);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setBounds(42, 208, 127, 25);
		contentPane.add(lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(195, 206, 204, 30);
		contentPane.add(textCorreo);
		
		
		ImageIcon logo = new ImageIcon("imagenes/logo.png");
		Image image = logo.getImage(); 
		Image newImage = image.getScaledInstance(169, 132, Image.SCALE_SMOOTH); 
		logo = new ImageIcon(newImage);
		
		lblImagen = new JLabel();
		lblImagen.setIcon(logo);
		lblImagen.setBounds(137, 341, 221, 108);
		contentPane.add(lblImagen);
		
		lblBtn = new JLabel("Volver a Inicio");
		lblBtn.setBounds(58, 277, 181, 25);
		contentPane.add(lblBtn);
		
		ManejadorEventos manejador = new ManejadorEventos();
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(273, 279, 85, 21);
		btnVolver.addActionListener(manejador);
		contentPane.add(btnVolver);
		
		setVisible(true);
	}
	
	
	private void showUser() {
		try {
			Usuario usuario = this.userService.getUsuario(Conexion.obtener(), ((Usuario) usuarios).getCodigo()); 
			if(usuario != null) { 
				textNombre.setText(usuario.getUsername()); 
				textDireccion.setText(usuario.getDireccion()); 
				textTelefono.setText(String.valueOf(usuario.getTelefono())); 
				textRol.setText(usuario.getRol()); 
				textCorreo.setText(usuario.getCorreo()); 
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		}
	}
	
	private class ManejadorEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btnVolver) {
				dispose();
			}
		}
		
	}
}
