package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class FrameEstadisticas extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public FrameEstadisticas() {
        super("Estadísticas");

        // Crear el dataset
        DefaultCategoryDataset dataset = createDataset();

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createLineChart(
                "Gráfico de Líneas Ejemplo", // Título del gráfico
                "Categoría", // Etiqueta de categoría (eje X)
                "Valor", // Etiqueta de valor (eje Y)
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                true, // Incluir leyenda
                true, // Incluir tooltips
                false // Excluir URLs
        );

        // Crear el panel de gráficos con el gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        // Crear panel inferior para el botón
        JPanel buttonPanel = new JPanel();
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new BtnVolverActionListener());


        // Añadir el botón al panel
        buttonPanel.add(btnVolver);

        // Configurar el layout del contentPane
        getContentPane().setLayout(new BorderLayout());

        // Añadir el panel del gráfico al centro y el panel del botón al sur
        getContentPane().add(chartPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new ListVideojuegos().setVisible(true);;
                dispose(); // Cierra la ventana actual
            }
        });

        setLocationRelativeTo(null);
        // Configurar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Añadir valores al dataset
        dataset.addValue(1, "Serie 1", "2016");
        dataset.addValue(4, "Serie 1", "2017");
        dataset.addValue(3, "Serie 1", "2018");
        dataset.addValue(5, "Serie 1", "2019");

        dataset.addValue(5, "Serie 2", "2016");
        dataset.addValue(7, "Serie 2", "2017");
        dataset.addValue(6, "Serie 2", "2018");
        dataset.addValue(8, "Serie 2", "2019");

        dataset.addValue(4, "Serie 3", "2016");
        dataset.addValue(3, "Serie 3", "2017");
        dataset.addValue(2, "Serie 3", "2018");
        dataset.addValue(3, "Serie 3", "2019");

        return dataset;
    }
	private class BtnVolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			dispose();
			new ListVideojuegos().setVisible(true);
		}
	}
}



