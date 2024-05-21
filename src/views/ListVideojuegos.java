package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import mvc.Videojuego;
import mvc.VideojuegoService;

public class ListVideojuegos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabla;
    private JButton estadisticas;
    private JButton pedidos;
    private JButton clientes;
    private final VideojuegoService services = new VideojuegoService();
    private List<Videojuego> videojuegos;
    private JComboBox<String> comboBoxGenero;

    /**
     * Create the frame.
     */
    public ListVideojuegos() {
        setTitle("Lista de VideoJuegos En Stock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 761, 431);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();

        tabla = new JTable();
        scrollPane.setViewportView(tabla);
        contentPane.add(scrollPane);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        estadisticas = new JButton("Estadisticas");
        estadisticas.addActionListener(new EstadisticasActionListener());
        estadisticas.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pedidos = new JButton("Pedidos");
        pedidos.addActionListener(new PedidosActionListener());
        pedidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
        clientes = new JButton("Clientes");
        clientes.addActionListener(new ClientesActionListener());
        clientes.setFont(new Font("Tahoma", Font.PLAIN, 20));

        panelInferior.add(estadisticas);
        panelInferior.add(pedidos);
        panelInferior.add(clientes);

        JPanel panelSuperior = new JPanel();
        contentPane.add(panelSuperior, BorderLayout.NORTH);

        comboBoxGenero = new JComboBox<String>();
        panelSuperior.add(comboBoxGenero);

        cargarGeneros();

        // Al seleccionar un género, actualizar la tabla
        comboBoxGenero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtrarPorGenero((String) comboBoxGenero.getSelectedItem());
            }
        });
        
        showVideojuego();
    }

    private void cargarGeneros() {
        try {
            // Obtener los géneros disponibles desde la base de datos
            List<String> generos = services.getAllGenero(mvc.Conexion.obtener());
            comboBoxGenero.setModel(new DefaultComboBoxModel<String>(generos.toArray(new String[0])));
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ha surgido un error al cargar los géneros");
        }
    }

    private void showVideojuego() {

        try {
            this.videojuegos = this.services.getAllVideojuegos(mvc.Conexion.obtener());
            tabla.setModel(new DefaultTableModel(new Object[][] {

            }, new String[] { "Código", "Nombre", "Descripción", "Genero", "Portada", "Stock" }));
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            dtm.setRowCount(0);
            for (int i = 0; i < this.videojuegos.size(); i++) {
                dtm.addRow(new Object[] { this.videojuegos.get(i).getCodigo(), this.videojuegos.get(i).getNombre(),
                        this.videojuegos.get(i).getDescripcion(), this.videojuegos.get(i).getGenero(),
                        this.videojuegos.get(i).getImagen(), this.videojuegos.get(i).getStock() });
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
        }

    }

    private void filtrarPorGenero(String genero) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // Limpiar la tabla

        for (Videojuego juego : videojuegos) {
            if (juego.getGenero().equals(genero)) {
                model.addRow(new Object[] { juego.getCodigo(), juego.getNombre(), juego.getDescripcion(),
                        juego.getGenero(), juego.getImagen(), juego.getStock() });
            }
        }
    }

    private class ClientesActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new UsuarioFrame();
            dispose();
        }
    }

    private class PedidosActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new ListCompra().setVisible(true);

        }
    }

    private class EstadisticasActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new FrameEstadisticas().setVisible(true);
        }
    }
}
