package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import mvc.Conexion;
import mvc.Usuario;
import mvc.UsuarioService;

public class EditUserFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textID;
    private JTextField textUsername;
    private JPasswordField textPsw;
    private JTextField textEmail;
    private JTextField textDir;
    private JTextField textTelf;
    private JButton btnGuardar;
    private JButton btnVolver;
    private UsuarioService userService = new UsuarioService();
    private UsuarioFrame parentFrame;
    private int userId;

    public EditUserFrame(int id, String username, String password, String direccion, String correo, String rol,
                         int telefono, UsuarioFrame parentFrame) throws HeadlessException {
        this.userId = id;
        this.parentFrame = parentFrame;

        setBounds(100, 100, 560, 402);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblid = new JLabel("Cod-Usuario");
        lblid.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblid.setBounds(105, 36, 99, 20);
        getContentPane().add(lblid);

        textID = new JTextField(String.valueOf(id));
        textID.setEditable(false);
        textID.setColumns(10);
        textID.setBounds(249, 40, 38, 19);
        getContentPane().add(textID);

        JLabel lblUsername = new JLabel("Usuario");
        lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblUsername.setBounds(105, 60, 99, 20);
        getContentPane().add(lblUsername);

        textUsername = new JTextField(username);
        textUsername.setColumns(10);
        textUsername.setBounds(249, 64, 218, 19);
        getContentPane().add(textUsername);

        JLabel lblPsw = new JLabel("Contraseña");
        lblPsw.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblPsw.setBounds(105, 90, 99, 20);
        getContentPane().add(lblPsw);

        textPsw = new JPasswordField(password);
        textPsw.setColumns(10);
        textPsw.setBounds(249, 90, 218, 19);
        getContentPane().add(textPsw);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblEmail.setBounds(105, 131, 99, 20);
        getContentPane().add(lblEmail);

        textEmail = new JTextField(correo);
        textEmail.setBounds(249, 135, 218, 19);
        getContentPane().add(textEmail);

        JLabel lblDir = new JLabel("Dirección");
        lblDir.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblDir.setBounds(105, 180, 99, 20);
        getContentPane().add(lblDir);

        textDir = new JTextField(direccion);
        textDir.setColumns(10);
        textDir.setBounds(249, 180, 218, 19);
        getContentPane().add(textDir);

        JLabel telfLabel = new JLabel("Teléfono");
        telfLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        telfLabel.setBounds(105, 229, 99, 20);
        getContentPane().add(telfLabel);

        textTelf = new JTextField(String.valueOf(telefono));
        textTelf.setColumns(10);
        textTelf.setBounds(249, 229, 218, 19);
        getContentPane().add(textTelf);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnGuardar.addActionListener(new BtnActionListener());
        btnGuardar.setBounds(130, 301, 117, 29);
        getContentPane().add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnVolver.addActionListener(new BtnActionListener());
        btnVolver.setBounds(309, 301, 117, 29);
        getContentPane().add(btnVolver);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class BtnActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnGuardar) {
                updateUser();
            }
            if (e.getSource() == btnVolver) {
                dispose();
                parentFrame.setVisible(true);
            }
        }

        private void updateUser() {
            try {
                Connection conexion = Conexion.obtener();

                boolean relleno = true;
                String user = textUsername.getText();
                @SuppressWarnings("deprecation")
                String password = textPsw.getText();
                String email = textEmail.getText();
                String direccion = textDir.getText();
                int telefono = Integer.parseInt(textTelf.getText());

                if (user.equals("") || password.equals("") || email.equals("") || direccion.equals("")
                        || textTelf.getText().equals("")) {
                    JOptionPane.showMessageDialog(EditUserFrame.this,
                            "Por favor complete todos los campos.");
                    relleno = false;
                }

                if (relleno) {
                    Usuario usu = new Usuario(userId, user, password, direccion, email, "Usuario", telefono);

                    userService.updateUsuario(conexion, usu);

                    JOptionPane.showMessageDialog(EditUserFrame.this, "Usuario actualizado con éxito.", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    parentFrame.showUser();
                    dispose();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(EditUserFrame.this, "Ha surgido un error y no se ha podido actualizar el usuario.");
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(EditUserFrame.this, "Ha surgido un error y no se ha podido actualizar el usuario.");
            }
        }
    }
}
