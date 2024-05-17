package examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;

public class Detalles {

	private JPanel panelRellenar;
	private JPanel panelBotones;
	private JButton btnComprar;
	private JButton btnValoracion;
	private JButton btnVolver;
	private List<Videojuego> videojuegos;
	private JPanel panel;
	private JLabel lblPrecio;
	private JTextField textFieldPrecio;
	private JLabel lblNombre;
	private JTextField textField_Nombre;
	private JLabel lblGenero;
	private JTextField textField_Genero;
	private JLabel lblStock;
	private JTextField textFieldStock;
	private JButton btnDescripcion;
	private JLabel lblPlataformas;
	private JTextField textFieldPlataformas;
	private PadreJFrame frame = new PadreJFrame();

	public Detalles() throws IOException {
		videojuegos = new ArrayList<>();
		videojuegos.clear();
		recogerDatosDB();

		
		frame.setTitle("Detalles del videojuego");
		frame.setBounds(100, 100, 700, 500);

		panelRellenar = new JPanel();
		frame.getContentPane().add(panelRellenar, BorderLayout.CENTER);
		panelRellenar.setLayout(null);
		panelRellenar.setBackground(new Color(72, 79, 84, 255));

		panel = new JPanel(new BorderLayout());
		panel.setBounds(467, 67, 178, 230);
		panelRellenar.add(panel);

		lblPrecio = new JLabel("  Precio");
		lblPrecio.setBackground(new Color(119, 128, 136));
		lblPrecio.setOpaque(true);
		lblPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPrecio.setForeground(Color.white);
		lblPrecio.setBounds(440, 330, 78, 36);
		panelRellenar.add(lblPrecio);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setText((String) null);
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
		textField_Nombre.setText((String) null);
		textField_Nombre.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textField_Nombre.setEditable(false);
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(171, 42, 241, 26);
		panelRellenar.add(textField_Nombre);

		lblGenero = new JLabel("  Género");
		lblGenero.setBackground(new Color(119, 128, 136));
		lblGenero.setOpaque(true);
		lblGenero.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblGenero.setBounds(49, 185, 85, 36);
		lblGenero.setForeground(Color.white);
		panelRellenar.add(lblGenero);

		textField_Genero = new JTextField();
		textField_Genero.setText((String) null);
		textField_Genero.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textField_Genero.setEditable(false);
		textField_Genero.setColumns(10);
		textField_Genero.setBounds(160, 190, 252, 26);
		panelRellenar.add(textField_Genero);

		lblStock = new JLabel("  Stock");
		lblStock.setBackground(new Color(119, 128, 136));
		lblStock.setOpaque(true);
		lblStock.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblStock.setBounds(49, 325, 78, 36);
		lblStock.setForeground(Color.white);
		panelRellenar.add(lblStock);

		textFieldStock = new JTextField();
		textFieldStock.setText((String) null);
		textFieldStock.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textFieldStock.setEditable(false);
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(160, 335, 252, 26);
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
		textFieldPlataformas.setText((String) null);
		textFieldPlataformas.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textFieldPlataformas.setEditable(false);
		textFieldPlataformas.setColumns(10);
		textFieldPlataformas.setBounds(208, 262, 204, 26);
		panelRellenar.add(textFieldPlataformas);

		leerLista();

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void recogerDatosDB() {
		// TODO Auto-generated method stub

	}

	private void leerLista() {
//			Videojuego videojuegoActual = videojuegos.get(indice);
//			panel.removeAll();
//			panel.repaint();
//			panel.revalidate();
//			NombretextField.setText(videojuegoActual.getNombre());
//			ApellidostextField.setText(videojuegoActual.getApellidos());
//			EdadtextField.setText(videojuegoActual.getEdad() + "");
//			EmailtextField.setText(videojuegoActual.getEmail());
//			NotatextField.setText(videojuegoActual.getNota() + "");
		JLabel lbl = new JLabel(new ImageIcon("caratulas/ds.png"));

		panel.add(lbl, BorderLayout.CENTER);
//			panel.repaint();
//			panel.revalidate();
//			frame.setVisible(true);
	}

	class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnVolver) {
				frame.dispose();
			}
			if (e.getSource() == btnValoracion) {
				new Valoracion();
			}

		}
	}
}
