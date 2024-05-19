package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import mvc.Usuario;
import mvc.UsuarioService;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SaveView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtUsername;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JTextField txtRol;
	private JTextField txtTelefono;
	private final UsuarioService services = new UsuarioService();
	private final Usuario user;

	/**
	 * Create the frame.
	 */
	
	public SaveView(Usuario user) {
		this.user = user;
		initComponents();
		txtCodigo.setText(String.valueOf(user.getCodigo()));
		txtUsername.setText(this.user.getUsername());
		txtDireccion.setText(this.user.getDireccion());
		txtCorreo.setText(this.user.getCorreo());
		txtRol.setText(this.user.getRol());
		txtTelefono.setText(String.valueOf(this.user.getTelefono()));
	}
	public SaveView() {
		this.user=new Usuario();
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		contentPane.add(lblCodigo);
		
		txtCodigo = new JTextField();
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		contentPane.add(lblNombre);
		
		txtUsername = new JTextField();
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		
		JLabel lblDireccion = new JLabel("Dirección:");
		contentPane.add(lblDireccion);
		
		txtDireccion = new JTextField();
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		
		JLabel lblCorreo = new JLabel("Correo:");
		contentPane.add(lblCorreo);
		
		txtCorreo = new JTextField();
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		
		JLabel lblRol = new JLabel("Rol:");
		contentPane.add(lblRol);
		
		txtRol = new JTextField();
		contentPane.add(txtRol);
		txtRol.setColumns(10);
		
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		contentPane.add(lblTelefono);
		
		txtTelefono = new JTextField();
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Codigo = Integer.parseInt(txtCodigo.getText());
				String Nombre = txtUsername.getText();
				String Direccion = txtDireccion.getText();
				String Correo = txtCorreo.getText();
				String Rol = txtRol.getText();
				int Telefono = Integer.parseInt(txtTelefono.getText());
				user.setCodigo(Codigo);
				user.setUsername(Nombre);
				user.setDireccion(Direccion);
				user.setCorreo(Correo);
				user.setRol(Rol);
				user.setTelefono(Telefono);
				
				
				try {
					services.saveUsuario(mvc.Conexion.obtener(), user);
					SaveView.this.dispose();
					ListView vista = new ListView();
					vista.setVisible(true);
					vista.setLocationRelativeTo(null);
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(SaveView.this, "Ha surgido un error y no se ha podido guardar el registro.");
				} catch (ClassNotFoundException ex) {
					System.out.println(ex);
					JOptionPane.showMessageDialog(SaveView.this, "Ha surgido un error y no se ha podido guardar el registro.");
				}
			}
		});
		btnGuardar.setBounds(46, 123, 117, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListView vista = new ListView();
				vista.setVisible(true);
				vista.setLocationRelativeTo(null);
			}
		});
		btnCancelar.setBounds(221, 123, 117, 29);
		contentPane.add(btnCancelar);
	}
}
