package mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
private final String tabla = "Usuario";
	
	
public void save(Connection conexion, Usuario usuario) throws SQLException {
	try {
		PreparedStatement consulta;
			consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(Username, Password, Direccion, Correo, Rol, Telefono) VALUES(?, ?, ?, ?, ?, ?)");
			consulta.setString(1, usuario.getUsername());
			consulta.setString(2, usuario.getPassword());
			consulta.setString(3, usuario.getDireccion());
			consulta.setString(4, usuario.getCorreo());
			consulta.setString(5, usuario.getRol());
			consulta.setInt(6, usuario.getTelefono());
		consulta.executeUpdate();
	} catch(SQLException ex) {
		throw new SQLException(ex);
	}
}


public Usuario getUsuario(Connection conexion, int Codigo) throws SQLException {
	Usuario usuario = null;
	try {
		PreparedStatement consulta = conexion.prepareStatement("SELECT Username, Password, Direccion, Correo, Rol, Telefono " + " FROM " + 
				this.tabla + " WHERE Codigo = ?");
		consulta.setInt(1, Codigo);
		ResultSet resultado = consulta.executeQuery();
		while(resultado.next()) {
			usuario = new Usuario(Codigo, resultado.getString("Username"), 
						resultado.getString("Password"), 
						resultado.getString("Direccion"), 
						resultado.getString("Correro"),
						resultado.getString("Rol"),
						resultado.getInt("Stock"));
		}
	} catch(SQLException ex) {
		throw new SQLException(ex);
	}
	return usuario;
}

public void remove(Connection conexion, Usuario usuario) throws SQLException {
	try {
		PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE Codigo = ? ");
		consulta.setInt(1, usuario.getCodigo());
		consulta.executeUpdate();
	} catch (SQLException ex) {
		throw new SQLException(ex);
	}
}

public List<Usuario> getAllUsuarios(Connection conexion) throws SQLException {
	List<Usuario> usuarios = new ArrayList<>();
	try {
		PreparedStatement consulta = conexion.prepareStatement("SELECT Codigo, Username, Password, Direccion, Correo, Rol, Telefono " + " FROM " + this.tabla);
		ResultSet resultado = consulta.executeQuery();
		while(resultado.next()) {
			usuarios.add(new Usuario(resultado.getInt("Codigo"),
					resultado.getString("Username"),
					resultado.getString("Password"),
					resultado.getString("Direccion"),
					resultado.getString("Correo"),
					resultado.getString("Rol"),
					resultado.getInt("Telefono")));
		}
	} catch(SQLException ex) {
		throw new SQLException(ex);
	}
	return usuarios;
}
}
