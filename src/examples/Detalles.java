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

public class Detalles {

	private JPanel panelRellenar;
	private JPanel panelBotones;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnFirst;
	private JButton btnLast;
	private JButton btnExit;
	private List<Videojuego> videojuegos;
	private int indice = 0;
	private int indCon = 1;
	private JLabel lblCodigo;
	private JTextField textFieldCodigo;
	private JLabel videojuegoslbl;
	private JPanel panel;
	private JLabel lbl;
	private JLabel lblPrecio;
	private JTextField textFieldPrecio;
	private JLabel lblNombre;
	private JTextField textField_Nombre;
	private JLabel lblGenero;
	private JTextField textField_Genero;
	private JTextField textField_Image;
	private JLabel lblStock;
	private JTextField textFieldStock;
	private JButton btnDescripcion;

	public Detalles() throws IOException {
		videojuegos = new ArrayList<>();
		videojuegos.clear();
		recogerDatosDB();

		PadreJFrame frame = new PadreJFrame();
		frame.setTitle("Detalles del videojuego");
		frame.setBounds(100, 100, 700, 500);

		panelRellenar = new JPanel();
		frame.getContentPane().add(panelRellenar, BorderLayout.CENTER);
		panelRellenar.setLayout(null);
		panelRellenar.setBackground(new Color(72, 79, 84, 255));

		lblCodigo = new JLabel("  Código");
		lblCodigo.setBackground(new Color(119, 128, 136));
		lblCodigo.setOpaque(true);
		lblCodigo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCodigo.setBounds(49, 43, 85, 36);
		lblCodigo.setForeground(Color.white);
		panelRellenar.add(lblCodigo);

		textFieldCodigo = new JTextField();
		textFieldCodigo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textFieldCodigo.setText((String) null);
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		textFieldCodigo.setBounds(171, 48, 241, 26);
		panelRellenar.add(textFieldCodigo);

		videojuegoslbl = new JLabel("1/0");
		videojuegoslbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		videojuegoslbl.setBounds(537, 19, 78, 36);
		videojuegoslbl.setForeground(Color.white);
		panelRellenar.add(videojuegoslbl);

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
		lblNombre.setBounds(49, 103, 95, 36);
		lblNombre.setForeground(Color.white);
		panelRellenar.add(lblNombre);

		textField_Nombre = new JTextField();
		textField_Nombre.setText((String) null);
		textField_Nombre.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textField_Nombre.setEditable(false);
		textField_Nombre.setColumns(10);
		textField_Nombre.setBounds(171, 108, 241, 26);
		panelRellenar.add(textField_Nombre);

		lblGenero = new JLabel("  Género");
		lblGenero.setBackground(new Color(119, 128, 136));
		lblGenero.setOpaque(true);
		lblGenero.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblGenero.setBounds(49, 251, 85, 36);
		lblGenero.setForeground(Color.white);
		panelRellenar.add(lblGenero);

		textField_Genero = new JTextField();
		textField_Genero.setText((String) null);
		textField_Genero.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		textField_Genero.setEditable(false);
		textField_Genero.setColumns(10);
		textField_Genero.setBounds(160, 256, 252, 26);
		panelRellenar.add(textField_Genero);

		lblStock = new JLabel("  Stock");
		lblStock.setBackground(new Color(119, 128, 136));
		lblStock.setOpaque(true);
		lblStock.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblStock.setBounds(49, 325, 85, 36);
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
		btnDescripcion.setBounds(132, 170, 197, 48);
		panelRellenar.add(btnDescripcion);

		panelBotones = new JPanel();
		panelBotones.setBackground(new Color(72, 79, 84));
		panelBotones.setBounds(0, 405, 710, 48);
		panelBotones.setForeground(new Color(72, 79, 84, 255));
		panelRellenar.add(panelBotones);
		panelBotones.setLayout(null);

		btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnPrevious.setBounds(111, 5, 130, 37);
		panelBotones.add(btnPrevious);
		btnPrevious.addActionListener(new BtnActionListener());

		btnNext = new JButton("Next");
		btnNext.setBounds(246, 5, 85, 37);
		btnNext.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		panelBotones.add(btnNext);
		btnNext.addActionListener(new BtnActionListener());

		btnFirst = new JButton("First");
		btnFirst.setBounds(336, 5, 85, 37);
		btnFirst.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		panelBotones.add(btnFirst);
		btnFirst.addActionListener(new BtnActionListener());

		btnLast = new JButton("Last");
		btnLast.setBounds(423, 5, 85, 37);
		btnLast.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnLast.addActionListener(new BtnActionListener());
		panelBotones.add(btnLast);

		btnExit = new JButton("Exit");
		btnExit.setBounds(508, 5, 85, 37);
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnExit.addActionListener(new BtnActionListener());
		panelBotones.add(btnExit);

		leerLista();

		comprobarPos();

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void recogerDatosDB() {
		// TODO Auto-generated method stub

	}

	private void comprobarPos() {
		if (indice <= 0) {
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(true);
		}
		if (indice < (videojuegos.size() - 1) && indice > 0)
			btnPrevious.setEnabled(true);

		if (indice >= (videojuegos.size() - 1)) {
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(true);
		}
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

			if (e.getSource() == btnExit) {
				System.exit(0);
			}

			if (e.getSource() == btnNext) {
				comprobarPos();
				videojuegoslbl.setText((indCon += 1) + "/" + videojuegos.size());

				indice += 1;
				leerLista();
				comprobarPos();
			}

			if (e.getSource() == btnPrevious) {
				comprobarPos();
				videojuegoslbl.setText((indCon -= 1) + "/" + videojuegos.size());

				indice -= 1;
				leerLista();
				comprobarPos();
			}

			if (e.getSource() == btnFirst) {
				comprobarPos();
				videojuegoslbl.setText(1 + "/" + videojuegos.size());
				btnNext.setEnabled(true);

				indice = 0;
				leerLista();
				comprobarPos();
			}

			if (e.getSource() == btnLast) {
				comprobarPos();
				videojuegoslbl.setText(videojuegos.size() + "/" + videojuegos.size());
				btnPrevious.setEnabled(true);

				indice = videojuegos.size() - 1;
				leerLista();
				comprobarPos();
			}

		}

	}

	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
}
