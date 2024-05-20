package examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mvc.Inicio;
import mvc.Videojuego;
import mvc.VideojuegoService;

public class Principal extends JFrame {

	private PadreJFrame frame;
	private JButton btnDetalles;
	private JPanel panelCaratula;
	private JComboBox comboBox;
	private List<Videojuego> videojuegos = new ArrayList<>();
	private JLabel lbl = new JLabel();
	public Videojuego seleccionado = new Videojuego();
	private Inicio inicio = new Inicio();
	private JComboBox comboBox_1;
	private JLabel selecciona;
	private JButton btnCuenta;

	public Principal(Inicio i) throws ClassNotFoundException, SQLException {

		comboBox_1 = new JComboBox();
		getContentPane().add(comboBox_1, BorderLayout.NORTH);
		inicio = i;
		videojuegos.clear();
		VideojuegoService videojuegoservice = new VideojuegoService();
		videojuegos = videojuegoservice.getAllVideojuegos(mvc.Conexion.obtener());
		frame = new PadreJFrame();
		frame.setTitle("Catálogo");
		frame.setBounds(100, 100, 571, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnDetalles = new JButton("Ver detalles");
		btnDetalles.setBounds(337, 224, 117, 72);
		btnDetalles.addActionListener(new BtnActionListener());
		frame.getContentPane().add(btnDetalles);

		panelCaratula = new JPanel((LayoutManager) null);
		panelCaratula.setBounds(43, 52, 150, 192);
		frame.getContentPane().add(panelCaratula);
		panelCaratula.setLayout(new BorderLayout());

		panelCaratula.add(lbl, BorderLayout.CENTER);

		ImageIcon img = new ImageIcon("imagenes/cuenta.png");

		btnCuenta = new JButton(img);
		btnCuenta.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCuenta.addActionListener(new BtnActionListener());
		btnCuenta.setBorderPainted(false);
		btnCuenta.setFocusPainted(false);
		btnCuenta.setBounds(515, 10, 35, 35);
		frame.getContentPane().add(btnCuenta);

		selecciona = new JLabel("Selecciona un juego:");
		selecciona.setForeground(Color.white);
		selecciona.setBounds(250, 30, 299, 27);
		frame.getContentPane().add(selecciona);

		DefaultComboBoxModel modelo = new DefaultComboBoxModel();

		modelo.addAll(videojuegos);

		comboBox = new JComboBox(modelo);
		comboBox.addItemListener(new ComboBoxItemListener());
		comboBox.setBounds(246, 55, 299, 27);
		frame.getContentPane().add(comboBox);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnDetalles) {
				try {
					if (Principal.this == null || comboBox.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Selecciona un videojuego del catálogo");
					} else
						new Detalles(Principal.this, inicio);

				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (e.getSource() == btnCuenta) {
				try {
					new MiCuenta(inicio);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private class ComboBoxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (comboBox == e.getSource()) {
				selecciona.setVisible(false);
				seleccionado = (Videojuego) comboBox.getSelectedItem();
				lbl.setIcon(new ImageIcon(seleccionado.getImagen()));
				panelCaratula.repaint();
			}
		}
	}

	public Videojuego videojuegoSeleccionado() {
		return (Videojuego) comboBox.getSelectedItem();
	}
}
