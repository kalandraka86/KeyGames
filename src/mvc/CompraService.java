package mvc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraService {
	private final String tabla="Compra";
	
	public void saveCompra(Connection conexion, Compra compra) throws SQLException {
		try {
			PreparedStatement consulta;
				consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(Codigo_usuario, Codigo_videojuego, Fecha_compra, Unidades) VALUES(?, ?, ?, ?)");
				consulta.setInt(1, compra.getCodUsuario());
				consulta.setInt(2, compra.getCodVideojuego());
				consulta.setDate(3, (Date) compra.getFechaCompra());
				consulta.setInt(4, compra.getUnidades());
			consulta.executeUpdate();
		} catch(SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	
	public Compra getCompra(Connection conexion, Date fechaCompra) throws SQLException {
		Compra compra = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement("SELECT Codigo_usuario, Codigo_videojuego, Unidades " + " FROM " + 
					this.tabla + " WHERE Fecha_compra = ?");
			consulta.setDate(1, fechaCompra);
			ResultSet resultado = consulta.executeQuery();
			while(resultado.next()) {
				compra = new Compra(resultado.getInt("Usuario"), 
							resultado.getInt("Videojuego"), 
							fechaCompra,
							resultado.getInt("Unidades"));
			}
		} catch(SQLException ex) {
			throw new SQLException(ex);
		}
		return compra;
	}
	
	public void remove(Connection conexion, Compra compra) throws SQLException {
		try {
			PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE Codigo = ? ");
			consulta.setDate(1, (Date) compra.getFechaCompra());
			consulta.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}
	
	public List<Compra> getAllCompras(Connection conexion) throws SQLException {
		List<Compra> compras = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion.prepareStatement("SELECT Codigo, Usuario, Videojuego, Unidades " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while(resultado.next()) {
				compras.add(new Compra(
						resultado.getInt("Usuario"),
						resultado.getInt("Videojuego"),
						resultado.getDate("Fecha_compra"),
						resultado.getInt("Unidades")));
			}
		} catch(SQLException ex) {
			throw new SQLException(ex);
		}
		return compras;
	}
}
