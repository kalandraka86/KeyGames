package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Compra;
import services.CompraService;
import services.Conexion;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ListCompra extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabla;
    private JButton volver;
    private final CompraService services = new CompraService();
    private List<Compra> compras;

    public ListCompra() {
        setTitle("Lista Compras Realizadas");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ListVideojuegos().setVisible(true);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        tabla = new JTable();
        scrollPane.setViewportView(tabla);
        contentPane.add(scrollPane);
        showCompras();

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        volver = new JButton("Volver");
        volver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        volver.addActionListener(new VolverActionListener());
        panelInferior.add(volver);
    }

    private void showCompras() {
        try {
            this.compras = this.services.getAllCompras(Conexion.obtener());
            DefaultTableModel dtm = new DefaultTableModel(new Object[][] {}, new String[] { "Cod-Usuario", "Cod-Videojuego", "Fecha-Compra", "Unidades" });
            tabla.setModel(dtm);
            for (Compra compra : this.compras) {
                dtm.addRow(new Object[] { compra.getCodUsuario(), compra.getCodVideojuego(), compra.getFechaCompra(), compra.getUnidades() });
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los datos.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class Not Found Error: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los datos.");
        }
    }

    private class VolverActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new ListVideojuegos().setVisible(true);
        }
    }
}

