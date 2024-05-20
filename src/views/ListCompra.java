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
import mvc.VideojuegoService;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ListCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private JButton volver;
	private final CompraService services = new CompraService();
	private List<Compra> compras;

	/**
	 * Create the frame.
	 */
	public ListCompra() {
		setTitle("Lista Compras Realizadas");
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ListVideojuegos().setVisible(true);;
                dispose(); // Cierra la ventana actual
            }
        });
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
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
		volver.addActionListener(new VolverActionListener());
		panelInferior.add(volver);
	}

	/*private void showCompras() {
		
		try {
			this.compras = this.services.getAllVideojuegos(mvc.Conexion.obtener());
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
		
	}*/

	private class VolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ListVideojuegos().setVisible(true);
		}
	}
}
