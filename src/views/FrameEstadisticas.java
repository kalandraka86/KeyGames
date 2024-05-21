package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import mvc.Compra;
import mvc.CompraService;

public class FrameEstadisticas extends JFrame {

    private static final long serialVersionUID = 1L;

    public FrameEstadisticas() {
        super("Estadísticas de Compras por Usuario");
        createChartPanel();
        addVolverButton();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createChartPanel() {
        List<Compra> compras = obtenerCompras();
        DefaultCategoryDataset dataset = createDataset(compras);
        JFreeChart chart = ChartFactory.createBarChart(
                "Cantidad Total de Compras por Usuario",
                "Usuario",
                "Cantidad Total de Compras",
                dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    private List<Compra> obtenerCompras() {
        try {
            CompraService compraService = new CompraService();
            Connection conexion = mvc.Conexion.obtener();
            return compraService.getAllCompras(conexion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private DefaultCategoryDataset createDataset(List<Compra> compras) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (compras == null) {
            return dataset;
        }
        Map<Integer, Integer> totalComprasPorUsuario = new HashMap<>();

        for (Compra compra : compras) {
            int codUsuario = compra.getCodUsuario();
            int unidades = compra.getUnidades();
            totalComprasPorUsuario.put(codUsuario, totalComprasPorUsuario.getOrDefault(codUsuario, 0) + unidades);
        }

        totalComprasPorUsuario.forEach((codUsuario, totalUnidades) -> {
            dataset.addValue(totalUnidades, "Usuario " + codUsuario, "");
        });

        return dataset;
    }

    private void addVolverButton() {
        JPanel panel = new JPanel();
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar el frame ListVideojuegos al hacer clic en "Volver"
                dispose(); // Cerrar la ventana actual
                new ListVideojuegos().setVisible(true);
            }
        });
        panel.add(btnVolver);
        add(panel, BorderLayout.SOUTH);
    }
}






