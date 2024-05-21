package mvc;

import java.awt.Color;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import examples.PadreJFrame;

public class Intro{

	private JLabel imagenlbl;
	private JProgressBar progressBar;
	private PadreJFrame frame = new PadreJFrame();

	public Intro() {
		frame.setTitle("Bienvenido KeyGames!!!");
		frame.getContentPane().setForeground(new Color(72, 79, 84, 255));
		frame.setForeground(new Color(72, 79, 84, 255));
		frame.setBounds(100, 100, 455, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		
        ImageIcon imagen = new ImageIcon("imagenes/logosin.png"); 
		imagenlbl = new JLabel(imagen);
		imagenlbl.setBounds(92, 41, 290, 296);
		frame.getContentPane().add(imagenlbl);
		frame.setVisible(true);
		
		// Crear y configurar la barra de progreso
        progressBar = new JProgressBar();
        progressBar.setBounds(53, 349, 350, 20);
        progressBar.setStringPainted(false); // Mostrar porcentaje
        frame.getContentPane().add(progressBar);

		frame.setResizable(false);
        frame.setVisible(true);

        // Configurar temporizador para simular progreso de carga
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progreso = 0;

            @Override
            public void run() {
                // Incrementar el progreso
                progreso += 10;
                progressBar.setValue(progreso);

                if (progreso == 100) {
                    // Cuando la barra de progreso llega al 100%, cerrar el JFrame
                	frame.dispose();
                    try {
						Inicio i = new Inicio();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    cancel(); // Detener el temporizador
                }
            }
        }, 0, 500); // Cambiado a 500 milisegundos (0 segundos de espera antes de iniciar, 500 milisegundos entre cada actualización)


	}
}
