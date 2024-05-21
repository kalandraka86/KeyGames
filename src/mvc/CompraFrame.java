package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class CompraFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JTextField textUnidades;
	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblUsuario;
	private JLabel lblUnidades;
	private JLabel lblComprar;
	private JLabel lblVolver;
	private JButton btnComprar;
	private JButton btnVolver;
	private ImageIcon image;
	private Videojuego videojuego;
	private Usuario usuario;
	private List<Videojuego> videojuegos = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	
	
	public static void main(String[] args) {
		CompraFrame frame = new CompraFrame();
	}

	
	public CompraFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ManejadorEventos manejador = new ManejadorEventos();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImagen = new JLabel();
		lblImagen.setBounds(208, 24, 184, 172);
		contentPane.add(lblImagen);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(59, 224, 106, 13);
		contentPane.add(lblNombre);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(59, 269, 106, 13);
		contentPane.add(lblUsuario);
		
		JLabel lblUnidades = new JLabel("Unidades: ");
		lblUnidades.setBounds(59, 317, 106, 13);
		contentPane.add(lblUnidades);
		
		textNombre = new JTextField();
		textNombre.setBounds(226, 214, 292, 37);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(226, 255, 292, 37);
		contentPane.add(textUsuario);
		
		textUnidades = new JTextField();
		textUnidades.setColumns(10);
		textUnidades.setBounds(226, 302, 292, 37);
		contentPane.add(textUnidades);
		
		lblComprar = new JLabel("Realice su compra: ");
		lblComprar.setBounds(119, 369, 80, 13);
		contentPane.add(lblComprar);
		
		lblVolver = new JLabel("Volver: ");
		lblVolver.setBounds(119, 400, 80, 13);
		contentPane.add(lblVolver);
		
		btnComprar = new JButton("Comprar: ");
		btnComprar.setBounds(282, 365, 119, 21);
		btnComprar.addActionListener(manejador);
		contentPane.add(btnComprar);
		
		btnVolver = new JButton("Volver: ");
		btnVolver.setBounds(282, 396, 119, 21);
		btnVolver.addActionListener(manejador);
		contentPane.add(btnVolver);
		
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private class ManejadorEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnComprar) {
				JOptionPane.showMessageDialog(null, "Operación realizada con éxito");
			}
			
		}
		
	}
}
