package mvc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValoracionService {
	private final String tabla = "Valoracion";

	public void saveValoracion(Connection conexion, Valoracion valoracion) throws SQLException {
		try {
			PreparedStatement consulta;
			consulta = conexion.prepareStatement("INSERT INTO " + this.tabla
					+ "(Codigo_usuario, Codigo_videojuego, Comentario, Nota) VALUES(?, ?, ?, ?)");
			consulta.setInt(1, valoracion.getCodigo_videojuego());
			consulta.setInt(2, valoracion.getCodigo_usuario());
			consulta.setString(3, valoracion.getComentario());
			consulta.setInt(4, valoracion.getNota());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public Valoracion getValoracion(Connection conexion, int codigoUsuario, int codigoVideojuego) throws SQLException {
		Valoracion valoracion = null;

		String query = "SELECT Codigo_usuario, Codigo_videojuego, Comentario, Nota FROM " + this.tabla
				+ " WHERE Codigo_usuario = ? AND Codigo_videojuego = ?";

		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			consulta.setInt(1, codigoUsuario);
			consulta.setInt(2, codigoVideojuego);

			try (ResultSet resultado = consulta.executeQuery()) {
				if (resultado.next()) {
					int codUsuario = resultado.getInt("Codigo_usuario");
					int codVideojuego = resultado.getInt("Codigo_videojuego");
					String comentario = resultado.getString("Comentario");
					int nota = resultado.getInt("Nota");

					valoracion = new Valoracion(codUsuario, codVideojuego, nota, comentario);
				}
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}

		return valoracion;
	}

	public void removeValoracion(Connection conexion, int codigoUsuario, int codigoVideojuego) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"DELETE FROM " + this.tabla + " WHERE Codigo_usuario = ? AND Codigo_videojuego = ?");
			consulta.setInt(1, codigoUsuario);
			consulta.setInt(2, codigoVideojuego);
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public List<Valoracion> getAllValoraciones(Connection conexion) throws SQLException {
		List<Valoracion> valoraciones = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT Codigo_usuario, Codigo_videojuego, Comentario, Nota FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				int codigoUsuario = resultado.getInt("Codigo_usuario");
				int codigoVideojuego = resultado.getInt("Codigo_videojuego");
				String comentario = resultado.getString("Comentario");
				int nota = resultado.getInt("Nota");

				Valoracion valoracion = new Valoracion(codigoUsuario, codigoVideojuego, nota, comentario);
				valoraciones.add(valoracion);
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return valoraciones;
	}

}
