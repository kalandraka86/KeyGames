package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
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
		super("Bienvenido a KeyGames!!!");
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Establecer el color de fondo
		setBackground(new Color(6, 16, 25));

		// Crear un panel para contener la imagen y la barra de progreso
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setOpaque(false); // Hacer que el panel sea transparente
		add(contentPanel);

		// Agregar la imagen al panel en el centro
		ImageIcon imagen = new ImageIcon("files/logo.jpg");
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
					dispose();
					Inicio i = new Inicio();
					cancel();
				}
			}
		}, 0, 500); // 500 milisegundos

		// Ajustar automáticamente el tamaño del JFrame
		pack();

		// Centrar la ventana en la pantalla
		setLocationRelativeTo(null);
		
		setResizable(false);

		setVisible(true);
	}
}
