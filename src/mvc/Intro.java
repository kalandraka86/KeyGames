package mvc;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Intro extends JFrame {

	private JLabel imagenlbl;
	private JProgressBar progressBar;

	public Intro() {
//		PadreJFrame frame = new PadreJFrame();
		JFrame frame = new JFrame();
		frame.setTitle("Bienvenido a KeyGames!!!");

		frame.setBounds(0, 200, 200, 200);

		// Crear un panel para contener la imagen y la barra de progreso
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setOpaque(false); // Hacer que el panel sea transparente
		frame.add(contentPanel);

		// Agregar la imagen al panel en el centro
		ImageIcon imagen = new ImageIcon("imagenes/logo.png");
		imagenlbl = new JLabel(imagen);
		contentPanel.add(imagenlbl, BorderLayout.CENTER);

		// Crear y agregar la barra de progreso en la parte inferior
		progressBar = new JProgressBar();
		progressBar.setStringPainted(false);
		contentPanel.add(progressBar, BorderLayout.SOUTH);

		// Configurar temporizador para simular progreso de carga
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int progreso = 0;

			@Override
			public void run() {
				progreso += 10;
				progressBar.setValue(progreso);

				if (progreso == 100) {
					frame.dispose();
					Inicio i = new Inicio();
					cancel();
				}
			}
		}, 0, 500); // 500 milisegundos

		// Ajustar automáticamente el tamaño del JFrame
		frame.pack();

		// Centrar la ventana en la pantalla
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);

		frame.setVisible(true);
	}
}
