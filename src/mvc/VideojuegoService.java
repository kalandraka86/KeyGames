package mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoService {
	
private final String tabla = "Videojuego";
	
	
	public void save(Connection conexion, Videojuego videojuego) throws SQLException {
		try {
			PreparedStatement consulta;
			consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(Nombre, Descripcion, Genero, Imagen, Stock, Plataformas, Precio) VALUES(?, ?, ?, ?, ?, ?, ?)");
			consulta.setString(1, videojuego.getNombre());
			consulta.setString(2, videojuego.getDescripcion());
			consulta.setString(3, videojuego.getGenero());
			consulta.setString(4, videojuego.getImagen());
			consulta.setInt(5, videojuego.getStock());
			consulta.setString(6, videojuego.getPlataformas());
			consulta.setInt(7, videojuego.getPrecio());
		consulta.executeUpdate();
		} catch(SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	
	public Videojuego getVideojuego(Connection conexion, int Codigo) throws SQLException {
		Videojuego videojuego = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement("SELECT Nombre, Descripcion, Genero, Imagen, Stock, Plataformas, Precio " + " FROM " + 
					this.tabla + " WHERE Codigo = ?");
			consulta.setInt(1, Codigo);
			ResultSet resultado = consulta.executeQuery();
			while(resultado.next()) {
				videojuego = new Videojuego(Codigo, resultado.getString("Nombre"), 
							resultado.getString("Descripcion"), 
							resultado.getString("Genero"), 
							resultado.getString("Imagen"), 
							resultado.getInt("Stock"),
							resultado.getString("Plataformas"),
							resultado.getInt("Precio"));
			}
		} catch(SQLException ex) {
			throw new SQLException(ex);
		}
		return videojuego;
	}
	
	public void remove(Connection conexion, Videojuego videojuego) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE Codigo = ? ");
			consulta.setInt(1, videojuego.getCodigo());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	public List<Videojuego> getAllVideojuegos(Connection conexion) throws SQLException {
		List<Videojuego> videojuegos = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion.prepareStatement("SELECT Codigo, Nombre, Descripcion, Genero, Imagen, Stock, Plataformas, Precio " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while(resultado.next()) {
				videojuegos.add(new Videojuego(resultado.getInt("Codigo"),
						resultado.getString("Nombre"),
						resultado.getString("Descripcion"),
						resultado.getString("Genero"),
						resultado.getString("Imagen"),
						resultado.getInt("Stock"),
						resultado.getString("Plataformas"),
						resultado.getInt("Precio")));
			}
		} catch(SQLException ex) {
			throw new SQLException(ex);
		}
		return videojuegos;
	}
}
