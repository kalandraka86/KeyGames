package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import mvc.Usuario;
import mvc.UsuarioService;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class ListView extends JFrame {

	private JPanel contentPane;
	private JTable jtableP;
	private final UsuarioService services = new UsuarioService();
	private List<Usuario> usuario;

	public ListView() {
		setTitle("Lista de usuarios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListView.this.dispose();
				SaveView vista = new SaveView();
				vista.setVisible(true);
				vista.setLocationRelativeTo(null);
			}
		});
		btnCreate.setBounds(33, 24, 117, 29);
		contentPane.add(btnCreate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila_seleccionada = jtableP.getSelectedRow();
				if (fila_seleccionada >= 0) {
					ListView.this.dispose();
					SaveView vista = new SaveView(usuario.get(fila_seleccionada));
					vista.setVisible(true);
					vista.setLocationRelativeTo(null);
				} else {
					JOptionPane.showMessageDialog(ListView.this, "Por favor seleccione una fila.");
				}
			}
		});
		btnUpdate.setBounds(169, 24, 117, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila_seleccionada = jtableP.getSelectedRow();
				if (fila_seleccionada >= 0) {
					int decision = JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea eliminar este usuario?",
							"Advertencia", JOptionPane.YES_NO_OPTION);
					if (decision == 0) {
						try {
							services.remove(mvc.Conexion.obtener(), usuario.get(fila_seleccionada));
							showUsers();
						} catch (SQLException ex) {
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(ListView.this, "Ha surgido un error y no se ha podido eliminar el registro.");
						} catch (ClassNotFoundException ex) {
							System.out.println(ex);
							JOptionPane.showMessageDialog(ListView.this, "Ha surgido un error y no se ha podido eliminar el registro.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(ListView.this, "Por favor seleccione una fila.");
				}
			}
		});
		btnDelete.setBounds(308, 24, 117, 29);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 97, 287, 146);
		contentPane.add(scrollPane);
		
		jtableP = new JTable();
		scrollPane.setViewportView(jtableP);
		showUsers();
		
	}
	
	private void showUsers() {
		try {
			this.usuario = this.services.getAllUsuarios(mvc.Conexion.obtener());
			jtableP.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

			}, new String[] { "Código", "Username", "Dirección", "Correo", "Rol", "Teléfono" }));
			DefaultTableModel dtm = (DefaultTableModel) jtableP.getModel();
			dtm.setRowCount(0);
			for (int i = 0; i < this.usuario.size(); i++) {
				dtm.addRow(new Object[] { this.usuario.get(i).getCodigo(), this.usuario.get(i).getUsername(), this.usuario.get(i).getDireccion(), this.usuario.get(i).getCorreo(), this.usuario.get(i).getRol(), this.usuario.get(i).getTelefono()});
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
