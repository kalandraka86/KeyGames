package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
private static Connection cnx = null;
	
	public static Connection obtener() throws SQLException, ClassNotFoundException {
		if(cnx==null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cnx=DriverManager.getConnection("jdbc:mysql://uqurix8o0fdwi7c6:uuWqIdoS9Wt4E2QBANDo@blmfe4dw3c83zm213uzz-mysql.services.clever-cloud.com:3306/blmfe4dw3c83zm213uzz");
			} catch(SQLException ex) {
				throw new SQLException(ex);
			} catch(ClassNotFoundException ex) {
				throw new ClassCastException(ex.getMessage());
			}
		}
		return cnx;
	}
	
	public static void cerrar() throws SQLException {
		if(cnx != null) {
			cnx.close();
		}
	}
}
