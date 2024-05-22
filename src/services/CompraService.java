package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Compra;

public class CompraService {
	private final String tabla = "Compra";

	public void saveCompra(Connection conexion, Compra compra) throws SQLException {
		try {
			PreparedStatement consulta;
			consulta = conexion.prepareStatement("INSERT INTO " + this.tabla
					+ "(Codigo_usuario, Codigo_videojuego, Fecha_compra, Unidades) VALUES(?, ?, ?, ?)");
			consulta.setInt(1, compra.getCodUsuario());
			consulta.setInt(2, compra.getCodVideojuego());
			consulta.setTimestamp(3, compra.getFechaCompra());
			consulta.setInt(4, compra.getUnidades());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public Compra getCompra(Connection conexion, java.sql.Timestamp fechaCompra) throws SQLException {
		Compra compra = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement("SELECT Codigo_usuario, Codigo_videojuego, Unidades "
					+ " FROM " + this.tabla + " WHERE Fecha_compra = ?");
			consulta.setTimestamp(1, fechaCompra);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				compra = new Compra(resultado.getInt("Usuario"), resultado.getInt("Videojuego"), fechaCompra,
						resultado.getInt("Unidades"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return compra;
	}

	public void remove(Connection conexion, Compra compra) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE Codigo = ? ");
			consulta.setTimestamp(1,compra.getFechaCompra());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public List<Compra> getAllCompras(Connection conexion) throws SQLException {
		List<Compra> compras = new ArrayList<>();
		String query = "SELECT Codigo_usuario, Codigo_videojuego, Fecha_compra, Unidades FROM " + this.tabla;

		try (PreparedStatement consulta = conexion.prepareStatement(query)) {
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				compras.add(new Compra(resultado.getInt("Codigo_usuario"), resultado.getInt("Codigo_videojuego"),
						resultado.getTimestamp("Fecha_compra"), resultado.getInt("Unidades")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return compras;
	}

	public void nuevoStock(Connection conexion, Compra compra, int stockIni, int stockCompra) throws SQLException {
		try {
			PreparedStatement consulta;

			consulta = conexion.prepareStatement("UPDATE Videojuego SET Stock = ? WHERE Codigo = ?");

			consulta.setInt(1, stockIni - stockCompra);
			consulta.setInt(2, compra.getCodVideojuego());

			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

}
