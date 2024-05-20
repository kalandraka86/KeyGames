package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import mvc.Usuario;
import mvc.UsuarioService;

public class EditUserFrame extends JFrame {

    private JTextField idField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField direccionField;
    private JTextField correoField;
    private JTextField rolField;
    private JTextField telefonoField;
    private JButton saveButton;
    private UsuarioService userService = new UsuarioService();
    private UsuarioFrame parentFrame;

    public EditUserFrame(int id, String username, String password, String direccion, String correo, String rol,
            int telefono, UsuarioFrame parentFrame) {
        this.parentFrame = parentFrame;

        // Configuración del frame de edición
        setTitle("Editar Usuario");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        // Campos de texto para los datos del usuario
        add(new JLabel("ID:"));
        idField = new JTextField(String.valueOf(id));
        idField.setEditable(false); // El ID no se puede editar
        add(idField);

        add(new JLabel("Username:"));
        usernameField = new JTextField(username);
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JTextField(password);
        add(passwordField);

        add(new JLabel("Direccion:"));
        direccionField = new JTextField(direccion);
        add(direccionField);

        add(new JLabel("Correo:"));
        correoField = new JTextField(correo);
        add(correoField);

        add(new JLabel("Rol:"));
        rolField = new JTextField(rol);
        add(rolField);

        add(new JLabel("Telefono:"));
        telefonoField = new JTextField(telefono);
        add(telefonoField);

        // Botón para guardar los cambios
        saveButton = new JButton("Guardar");
        saveButton.addActionListener(new SaveButtonListener());
        add(saveButton);

        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener los datos actualizados del usuario
            int id = Integer.parseInt(idField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            String direccion = direccionField.getText();
            String correo = correoField.getText();
            String rol = rolField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());

            // Crear un objeto Usuario con los datos actualizados
            Usuario usuario = new Usuario();
            usuario.setCodigo(id);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario.setDireccion(direccion);
            usuario.setCorreo(correo);
            usuario.setRol(rol);
            usuario.setTelefono(telefono);

            // Actualizar el usuario en la base de datos
            try {
                userService.updateUsuario(mvc.Conexion.obtener(), usuario);
                JOptionPane.showMessageDialog(EditUserFrame.this, "Usuario actualizado con éxito.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cerrar el frame de edición
                parentFrame.showUser(); // Actualizar la tabla en el frame principal
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(EditUserFrame.this,
                        "Ha surgido un error y no se ha podido actualizar el usuario.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}


