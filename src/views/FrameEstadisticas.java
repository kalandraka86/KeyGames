package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import mvc.Compra;
import mvc.CompraService;

public class FrameEstadisticas extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Compra> compras;

    /**
     * Create the frame.
     * @throws ClassNotFoundException 
     */
    public FrameEstadisticas() throws ClassNotFoundException {
        super("Estadísticas");

        // Rellenar la lista de compras desde la base de datos
        rellenarListaCompras();

        // Crear el dataset
        DefaultCategoryDataset dataset = createDataset(compras);

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createLineChart(
                "Gráfico de Líneas sobre Las Compras", // Título del gráfico
                "Año", // Etiqueta de categoría (eje X)
                "Unidades", // Etiqueta de valor (eje Y)
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
                new ListVideojuegos().setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });

        setLocationRelativeTo(null);
        // Configurar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
    }

    private void rellenarListaCompras() throws ClassNotFoundException {
        
    	 try {
             // Establecer conexión a la base de datos (aquí asumiendo que tienes algún método para hacerlo)
             Connection conexion = mvc.Conexion.obtener();

             // Crear instancia de CompraService
             CompraService compraService = new CompraService();

             // Obtener todas las compras de la base de datos
             compras = compraService.getAllCompras(conexion);

             // Cerrar la conexión a la base de datos
             conexion.close();
         } catch (SQLException ex) {
             // Manejar cualquier excepción SQL que pueda ocurrir
             ex.printStackTrace();
         }
    }

    private DefaultCategoryDataset createDataset(List<Compra> compras) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Mapa para almacenar las sumas de unidades compradas por usuario y año
        Map<Integer, Map<Integer, Integer>> dataMap = new HashMap<>();

        // Calcular las sumas de unidades compradas por usuario y año
        for (Compra compra : compras) {
            int codUsuario = compra.getCodUsuario();
            int year = getYear(compra.getFechaCompra());
            int unidades = compra.getUnidades();

            if (!dataMap.containsKey(codUsuario)) {
                dataMap.put(codUsuario, new HashMap<>());
            }

            Map<Integer, Integer> userMap = dataMap.get(codUsuario);
            userMap.put(year, userMap.getOrDefault(year, 0) + unidades);
        }

        // Agregar los valores al dataset
        for (int codUsuario : dataMap.keySet()) {
            Map<Integer, Integer> userMap = dataMap.get(codUsuario);
            for (int year : userMap.keySet()) {
                dataset.addValue(userMap.get(year), "Usuario " + codUsuario, String.valueOf(year));
            }
        }

        return dataset;
    }

    // Método para obtener el año de una fecha
    private int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private class BtnVolverActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new ListVideojuegos().setVisible(true);
        }
    }
}




