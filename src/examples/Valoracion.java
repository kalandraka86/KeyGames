package examples;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.Videojuego;

import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Valoracion {

    private PadreJFrame frame = new PadreJFrame();
    private JLabel lblTitulo;
    private JTextArea textArea;
    private JPanel panel;
    private JLabel lblNewLabel;
    private JButton btnEnviar;
    private JButton btnVolver;

    public Valoracion(Videojuego videojuego) {
        frame.setTitle("Valoración");
        frame.setBounds(100, 100, 515, 339);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblTitulo = new JLabel(videojuego.getNombre());
        lblTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setBounds(30, 28, 356, 26);
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

        lblNewLabel = new JLabel("Valoración: 1");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel.setBounds(339, 211, 137, 76);
        frame.getContentPane().add(lblNewLabel);
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
			if(e.getSource() == btnVolver) {
				frame.dispose();
			}
			if(e.getSource() == btnEnviar) {
				
			}
			
		}
	}
}
