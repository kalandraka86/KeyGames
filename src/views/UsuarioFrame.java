package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mvc.Registro;
import mvc.Usuario;
import mvc.UsuarioService;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UsuarioFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton volver;
	private JButton insertar;
	private JButton eliminar;
	private JButton editar;
	private List<Usuario> usuarios = new ArrayList<>();
	private UsuarioService userService = new UsuarioService();

	public UsuarioFrame() {
		super("Lista de usuarios");
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ListVideojuegos().setVisible(true);;
                dispose(); // Cierra la ventana actual
            }
        });
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 431);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));

		JScrollPane scroll = new JScrollPane();

		table = new JTable();
		scroll.setViewportView(table);
		contentPane.add(scroll);
		showUser();
		
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout());
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		volver = new JButton ("Volver");
		volver.addActionListener(new VolverActionListener());
		volver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		eliminar = new JButton ("Eliminar");
		eliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editar = new JButton ("Editar");
		editar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		insertar = new JButton("Insertar");
		insertar.addActionListener(new InsertarActionListener());
		insertar.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		panelInferior.add(volver);
		panelInferior.add(eliminar);
		panelInferior.add(editar);
		panelInferior.add(insertar);
		setVisible(true);
	}

	private void showUser() {
		try {
			this.usuarios = this.userService.getAllUsuarios(mvc.Conexion.obtener());
			table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

			}, new String[] { "Código", "Username", "Password", "Direccion", "Correo", "Rol","Telefono" }));
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			for (int i = 0; i < this.usuarios.size(); i++) {
				dtm.addRow(new Object[] { this.usuarios.get(i).getCodigo(), this.usuarios.get(i).getUsername(),
						this.usuarios.get(i).getPassword(), this.usuarios.get(i).getDireccion(),
						this.usuarios.get(i).getCorreo(), this.usuarios.get(i).getRol(),this.usuarios.get(i).getTelefono() });
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		}

	}

	private class VolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ListVideojuegos().setVisible(true);
			dispose();
		}
	}
	private class InsertarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Insertar().setVisible(true);
		}
	}
}
