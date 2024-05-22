package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Compra;
import models.PadreJFrame;
import models.Usuario;
import models.Videojuego;
import services.CompraService;
import services.Conexion;

public class CompraFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JLabel lblNombre;
	private JLabel lblUsuario;
	private JLabel lblUnidades;
	private ImageIcon image;
	private List<Videojuego> videojuegos = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	private PadreJFrame frame = new PadreJFrame();
	private JButton btnComprar;
	private JButton btnVolver;
	private JLabel lbl;
	private static Videojuego videojuego = null;
	private Inicio inicio;
	private Principal principal;
	private Usuario usuario;
	private JComboBox cantidadcomboBox;
	private Date utilDate = new Date();
	private java.sql.Timestamp fechaHoy = new java.sql.Timestamp(System.currentTimeMillis());
	private final CompraService services = new CompraService();

	public CompraFrame(Principal p, Inicio i) throws ClassNotFoundException, SQLException {
		videojuego = p.videojuegoSeleccionado();
		principal = p;
		inicio = i;
		usuario = i.seleccionadoUsuario();
		frame.setTitle("Compra de " + videojuego.getNombre());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 639, 505);
		frame.getContentPane().setLayout(null);

		lblNombre = new JLabel("  Título");
		lblNombre.setBackground(new Color(119, 128, 136));
		lblNombre.setOpaque(true);
		lblNombre.setForeground(new Color(254, 255, 255));
		lblNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNombre.setBounds(64, 236, 130, 32);
		frame.getContentPane().add(lblNombre);

		lblUsuario = new JLabel("  Usuario");
		lblUsuario.setBackground(new Color(119, 128, 136));
		lblUsuario.setOpaque(true);
		lblUsuario.setForeground(new Color(254, 255, 255));
		lblUsuario.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUsuario.setBounds(64, 293, 130, 34);
		frame.getContentPane().add(lblUsuario);

		lblUnidades = new JLabel("  Unidades");
		lblUnidades.setOpaque(true);
		lblUnidades.setBackground(new Color(119, 128, 136));
		lblUnidades.setForeground(new Color(254, 255, 255));
		lblUnidades.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUnidades.setBounds(64, 339, 130, 32);
		frame.getContentPane().add(lblUnidades);

		textNombre = new JTextField();
		textNombre.setText(videojuego.getNombre());
		textNombre.setEditable(false);
		textNombre.setBounds(231, 236, 292, 37);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		textUsuario = new JTextField();
		textUsuario.setText(usuario.getUsername());
		textUsuario.setEditable(false);
		textUsuario.setColumns(10);
		textUsuario.setBounds(231, 290, 292, 37);
		frame.getContentPane().add(textUsuario);

		btnComprar = new JButton("Comprar");
		btnComprar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnComprar.addActionListener(new btnActionListener());
		btnComprar.setBounds(361, 399, 117, 45);
		frame.getContentPane().add(btnComprar);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnVolver.addActionListener(new btnActionListener());
		btnVolver.setBounds(149, 399, 117, 45);
		frame.getContentPane().add(btnVolver);

		lbl = new JLabel(new ImageIcon(videojuego.getImagen()));
		lbl.setBounds(247, 24, 150, 192);
		frame.getContentPane().add(lbl);

		String[] stock = new String[101];

		for (int f = 1; f < stock.length; f++) {
			stock[f] = String.valueOf(f);
		}

		cantidadcomboBox = new JComboBox(stock);
		cantidadcomboBox.setBounds(291, 345, 140, 27);
		frame.getContentPane().add(cantidadcomboBox);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private class btnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnComprar) {
				updateCompra();
			}
			if (e.getSource() == btnVolver) {
				frame.dispose();
			}
		}
	}

	private void updateCompra() {
//		int codVideojuego, int codUsuario, Date fechaCompra, int unidades

		try {
			Connection conexion = Conexion.obtener();

			boolean relleno = false;
			Compra com = new Compra();
			int unidades = cantidadcomboBox.getSelectedIndex();

			if (cantidadcomboBox.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Por favor introduzca una cantidad válido");
			} else {
				relleno = true;
			}

			if (relleno) {

				List<Compra> compras = services.getAllCompras(conexion);
				int codigoUsuario = inicio.codUsuario();
				int codigoVideojuego = videojuego.getCodigo();
				// hay que obtener codigo de usuario

				com = new Compra(codigoVideojuego, codigoUsuario, fechaHoy, unidades);
				compras.add(com);

				services.saveCompra(conexion, com);
				services.nuevoStock(conexion, com, videojuego.getStock(), unidades);

				JOptionPane.showMessageDialog(this, "Compra realizada con éxito!!!", "COMPRA EXITOSA",
						JOptionPane.INFORMATION_MESSAGE);
				cantidadcomboBox.setSelectedIndex(0);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "No puedes comprar el mismo videojuego 2 veces seguidas");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this, "No puedes comprar el mismo videojuego 2 veces seguidas");
		}

	}
}
