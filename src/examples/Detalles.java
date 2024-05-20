package examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.Inicio;
import mvc.Videojuego;

public class Detalles extends JFrame{

	private JPanel panelRellenar;
	private JPanel panelBotones;
	private JButton btnComprar;
	private JButton btnValoracion;
	private JButton btnVolver;
	private JPanel panel;
	private JLabel lblPrecio;
	private JTextField textFieldPrecio;
	private JLabel lblNombre;
	private JTextField textField_Nombre;
	private JLabel lblGenero;
	private JTextField txtDdd;
	private JLabel lblStock;
	private JTextField textFieldStock;
	private JButton btnDescripcion;
	private JLabel lblPlataformas;
	private JTextField textFieldPlataformas;
	private PadreJFrame frame = new PadreJFrame();
	private static Videojuego videojuego = null;
	private JLabel lbl;
	private Inicio inicio = new Inicio();

	public Detalles(Principal p, Inicio i) throws IOException, ClassNotFoundException, SQLException {
		videojuego = p.videojuegoSeleccionado();
		inicio = i;

		panelRellenar = new JPanel();
		frame.getContentPane().add(panelRellenar, BorderLayout.CENTER);
		panelRellenar.setLayout(null);
		panelRellenar.setBackground(new Color(72, 79, 84, 255));
		
		panel = new JPanel(new BorderLayout());
		panel.setBounds(486, 67, 150, 192);
		panelRellenar.add(panel);
		
		lbl = new JLabel(new ImageIcon(videojuego.getImagen()));
		panel.add(lbl, BorderLayout.CENTER);
		
		frame.setTitle("Detalles de "+videojuego.getNombre());
		frame.setBounds(100, 100, 700, 500);

		lblPrecio = new JLabel("  Precio");
		lblPrecio.setBackground(new Color(119, 128, 136));
		lblPrecio.setOpaque(true);
		lblPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPrecio.setForeground(Color.white);
		lblPrecio.setBounds(440, 330, 78, 36);
		panelRellenar.add(lblPrecio);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setText(videojuego.getPrecio()+" €");
		textFieldPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(530, 335, 134, 26);
		panelRellenar.add(textFieldPrecio);

		lblNombre = new JLabel("  Nombre");
		lblNombre.setBackground(new Color(119, 128, 136));
		lblNombre.setOpaque(true);
		lblNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNombre.setBounds(49, 37, 95, 36);
		lblNombre.setForeground(Color.white);
		panelRellenar.add(lblNombre);

		textField_Nombre = new JTextField();
		textField_Nombre.setText(videojuego.getNombre());
		textField_Nombre.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textField_Nombre.setEditable(false);
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(171, 42, 303, 26);
		panelRellenar.add(textField_Nombre);

		lblGenero = new JLabel("  Género");
		lblGenero.setBackground(new Color(119, 128, 136));
		lblGenero.setOpaque(true);
		lblGenero.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblGenero.setBounds(49, 185, 85, 36);
		lblGenero.setForeground(Color.white);
		panelRellenar.add(lblGenero);

		txtDdd = new JTextField();
		txtDdd.setText(videojuego.getGenero());
		txtDdd.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtDdd.setEditable(false);
		txtDdd.setColumns(10);
		txtDdd.setBounds(160, 190, 178, 26);
		panelRellenar.add(txtDdd);

		lblStock = new JLabel("  Stock");
		lblStock.setBackground(new Color(119, 128, 136));
		lblStock.setOpaque(true);
		lblStock.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblStock.setBounds(49, 325, 78, 36);
		lblStock.setForeground(Color.white);
		panelRellenar.add(lblStock);

		textFieldStock = new JTextField();
		textFieldStock.setBackground(new Color(72, 80, 84));
		textFieldStock.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textFieldStock.setEditable(false);
		textFieldStock.setForeground(null);
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(188, 335, 150, 26);
		if(videojuego.getStock() > 0) {
			textFieldStock.setText("DISPONIBLE");
			textFieldStock.setForeground(Color.GREEN);
		}
		else {
			textFieldStock.setText("NO DISPONIBLE");
			textFieldStock.setForeground(Color.RED);
		}
		panelRellenar.add(textFieldStock);

		btnDescripcion = new JButton("Ver descripción");
		btnDescripcion.addActionListener(new BtnActionListener());
		btnDescripcion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnDescripcion.setBounds(132, 104, 197, 48);
		panelRellenar.add(btnDescripcion);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(72, 79, 84));
		panelBotones.setBounds(0, 405, 710, 48);
		panelBotones.setForeground(new Color(72, 79, 84, 255));
		panelRellenar.add(panelBotones);

		btnValoracion = new JButton("Valoración");
		btnValoracion.setBounds(422, 6, 135, 30);
		btnValoracion.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnValoracion.addActionListener(new BtnActionListener());
		panelBotones.add(btnValoracion);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(125, 6, 114, 30);
		btnVolver.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnVolver.addActionListener(new BtnActionListener());
		panelBotones.setLayout(null);
		panelBotones.add(btnVolver);

		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(239, 6, 183, 30);
		btnComprar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		panelBotones.add(btnComprar);
		btnComprar.addActionListener(new BtnActionListener());
		panelBotones.add(btnComprar);

		lblPlataformas = new JLabel("  Plataformas");
		lblPlataformas.setOpaque(true);
		lblPlataformas.setForeground(Color.WHITE);
		lblPlataformas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPlataformas.setBackground(new Color(119, 128, 136));
		lblPlataformas.setBounds(49, 257, 127, 36);
		panelRellenar.add(lblPlataformas);

		textFieldPlataformas = new JTextField();
		textFieldPlataformas.setText(" "+videojuego.getPlataformas());
		textFieldPlataformas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textFieldPlataformas.setEditable(false);
		textFieldPlataformas.setColumns(10);
		textFieldPlataformas.setBounds(208, 262, 247, 26);
		panelRellenar.add(textFieldPlataformas);


		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}


	class BtnActionListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnVolver) {
				frame.dispose();
				dispose();
			}
			if (e.getSource() == btnValoracion) {
				try {
					new ValoracionFrame(videojuego,inicio);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnDescripcion) {
				JOptionPane.showMessageDialog(null, videojuego.getDescripcion(),videojuego.getNombre(),JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
