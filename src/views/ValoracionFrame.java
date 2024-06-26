package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import models.FiveStarsPanel;
import models.PadreJFrame;
import models.Valoracion;
import models.Videojuego;
import services.CompraService;
import services.Conexion;
import services.ValoracionService;

public class ValoracionFrame extends JFrame{

	private PadreJFrame frame = new PadreJFrame();
	private JLabel lblTitulo;
	private JTextArea textArea;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JButton btnEnviar;
	private JButton btnVolver;
	private int rating;
	private final ValoracionService services = new ValoracionService();
	private Videojuego videojuego;
	private Inicio inicio;
//	System.out.println(inicio.usuarioSeleccionado());

	public ValoracionFrame(Videojuego videojuego, Inicio i) throws ClassNotFoundException, SQLException {
		this.videojuego = videojuego;
		inicio = i;
		frame.setTitle("Valoración");
		frame.setBounds(100, 100, 515, 339);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblTitulo = new JLabel("  "+videojuego.getNombre());
		lblTitulo.setBackground(new Color(119, 128, 136));
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(30, 28, 283, 26);
		frame.getContentPane().add(lblTitulo);

		textArea = new JTextArea();
		textArea.setBounds(30, 68, 283, 107);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(30, 68, 283, 130);
		frame.getContentPane().add(scrollPane);

		panel = new JPanel();
		panel.setBackground(new Color(72, 80, 84));
		panel.setBounds(30, 211, 283, 76);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		FiveStarsPanel estrellas = new FiveStarsPanel();
		estrellas.setBackground(new Color(72, 80, 84));
		estrellas.setBounds(0, 21, 292, 38);
		panel.add(estrellas);
		estrellas.setLayout(null);

		lblNewLabel = new JLabel("  Valoración: 1");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(119, 128, 136));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel.setBounds(339, 217, 137, 65);
		frame.getContentPane().add(lblNewLabel);

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		estrellas.setStarRatingListener(new FiveStarsPanel.StarRatingListener() {
			@Override
			public void ratingChanged(int rating) {
				lblNewLabel.setText("Valoración: " + rating);
			}
		});

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new BtnActionListener());
		btnEnviar.setBounds(359, 150, 117, 48);
		frame.getContentPane().add(btnEnviar);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new BtnActionListener());
		btnVolver.setBounds(359, 66, 117, 48);
		frame.getContentPane().add(btnVolver);
	}

	private class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnVolver) {
				frame.dispose();
			}
			if (e.getSource() == btnEnviar) {
				int confirm = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de enviar esta valoración?",
						"Confirmar", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					updateValoracion();
				}
			}

		}
	}

	private void updateValoracion() {

		try {
			Connection conexion = Conexion.obtener();

			boolean relleno = false;
			Valoracion val = new Valoracion();
			String comentario = textArea.getText();
			lblNewLabel.getText().split(" ");
			String[] partes = lblNewLabel.getText().split(" ");
			int nota = 0;
			
			if (comentario.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(this, "Por favor introduzca un comentario válido","COMENTARIO VACÍO",JOptionPane.ERROR_MESSAGE);
			} else {
				relleno = true;
				nota = Integer.parseInt(partes[1]);
			}

			if (relleno) {

				List<Valoracion> valoraciones = services.getAllValoraciones(conexion);
				int codigoUsuario = inicio.codUsuario();
				int codigoVideojuego = videojuego.getCodigo();
				// hay que obtener codigo de usuario

				val = new Valoracion(codigoVideojuego, codigoUsuario, nota, comentario);
				valoraciones.add(val);

				services.saveValoracion(conexion, val);

				JOptionPane.showMessageDialog(this, "Valoración enviada correctamente",
						"Valoración de " + lblTitulo.getText(), JOptionPane.INFORMATION_MESSAGE);
				textArea.setText("");
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
