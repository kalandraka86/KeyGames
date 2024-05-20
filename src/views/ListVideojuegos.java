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

import mvc.Videojuego;
import mvc.VideojuegoService;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListVideojuegos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla;
	private JButton estadisticas;
	private JButton pedidos;
	private JButton clientes;
	private final VideojuegoService services = new VideojuegoService();
	private List<Videojuego> videojuegos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListVideojuegos frame = new ListVideojuegos();
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
	public ListVideojuegos() {
		setTitle("Lista de VideoJuegos En Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 431);
		 setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		contentPane.add(scrollPane);
		showVideojuego();
		
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout());
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		estadisticas = new JButton("Estadisticas");
		estadisticas.addActionListener(new EstadisticasActionListener());
		estadisticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pedidos = new JButton("Pedidos");
		pedidos.addActionListener(new PedidosActionListener());
		pedidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		clientes = new JButton ("Clientes");
		clientes.addActionListener(new ClientesActionListener());
		clientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		panelInferior.add(estadisticas);
		panelInferior.add(pedidos);
		panelInferior.add(clientes);
		
		
	}

	private void showVideojuego() {
		
		try {
			this.videojuegos = this.services.getAllVideojuegos(mvc.Conexion.obtener());
			tabla.setModel(new DefaultTableModel(new Object[][] {

			}, new String[] { "Código", "Nombre","Descripción", "Genero", "Portada", "Stock" }));
			DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
			dtm.setRowCount(0);
			for (int i = 0; i < this.videojuegos.size(); i++) {
				dtm.addRow(new Object[] { this.videojuegos.get(i).getCodigo(), this.videojuegos.get(i).getNombre(), 
						this.videojuegos.get(i).getDescripcion(), this.videojuegos.get(i).getGenero(), this.videojuegos.get(i).getImagen(), this.videojuegos.get(i).getStock()});
			}
	

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		}
		
	}

	private class ClientesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new UsuarioFrame();
			dispose();
		}
	}
	private class PedidosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new ListCompra().setVisible(true);
			
		}
	}
	private class EstadisticasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			dispose();
			try {
				new FrameEstadisticas().setVisible(true);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
