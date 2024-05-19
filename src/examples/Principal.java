package examples;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import mvc.Videojuego;
import mvc.VideojuegoService;

public class Principal {

    private PadreJFrame frame;
    private JButton btnDetalles;
    private JPanel panelCaratula;
    private JComboBox comboBox;
    private List<Videojuego> videojuegos = new ArrayList<>();
    private JLabel lbl = new JLabel();
    public Videojuego seleccionado = new Videojuego();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Principal() throws ClassNotFoundException, SQLException {
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

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        modelo.addAll(videojuegos);

        comboBox = new JComboBox(modelo);
        comboBox.addItemListener(new ComboBoxItemListener());
        comboBox.setBounds(246, 52, 299, 27);
        frame.getContentPane().add(comboBox);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class BtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        	if(e.getSource() == btnDetalles) {
        		try {
        			if(Principal.this == null || comboBox.getSelectedItem() == null) {
        				JOptionPane.showMessageDialog(null, "Selecciona un videojuego del catálogo");
        			}
        			else
        				new Detalles(Principal.this);
					
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        }
    }

    private class ComboBoxItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (comboBox == e.getSource()) {
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
