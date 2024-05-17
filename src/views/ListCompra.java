package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mvc.Compra;
import mvc.CompraService;

public class ListCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private JButton volver;
	private final CompraService services = new CompraService();
	private List<Compra> compras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListCompra frame = new ListCompra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListCompra() {
		setTitle("Lista Compras Realizadas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		contentPane.add(scrollPane);
		//showCompras();
		
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout());
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		volver = new JButton ("Volver");
		panelInferior.add(volver);
	}

	private void showCompras() {
		
		try {
			this.compras = this.services.getAllCompras(mvc.Conexion.obtener());
			tabla.setModel(new DefaultTableModel(new Object[][] {

			}, new String[] { "Cod-VideoJuego", "Cod-Usuario","Fecha-Compra", "Unidades"}));
			DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
			dtm.setRowCount(0);
			for (int i = 0; i < this.compras.size(); i++) {
				dtm.addRow(new Object[] { this.compras.get(i).getCodVideojuego(), this.compras.get(i).getCodUsuario(), 
						this.compras.get(i).getFechaCompra(), this.compras.get(i).getUnidades()});
			}
	

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los datos");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los datos");
		}
		
	}

}
