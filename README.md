# KEYGAMES

Proyecto que consiste en una aplicación de escritorio basada en una tienda de videojuegos denominada **KeyGames**, donde los usuarios pueden visualizar el catálogo y realizar compras de los distintos videojuegos existentes en la tienda. 

También, los administradores podrán acceder a los datos de los usuarios y de las compras realizadas. 



## Tecnologías

* Java Swing
* SQL (MySQL Workbench)
* JFreeChart
* Eclipse software


## Funcionalidades del usuario


| Funcionalidad | Descripción |
|--------|-------------|
| `Login y registro` | Para la gestión de datos de usuario y para acceder a la página. |
| `Catálogo` | Disposición de un catálogo de videojuegos a elegir. |
| `Detalles` | Muestra todo tipo de detalles del videojuego seleccionado. |
| `Comprar` | El usuario podrá comprar videojuegos. |
| `Valorar` | El usuario podrá realizar valoraciones y comentarios sonbre los videojuegos. |

---


## Funcionalidades del administrador


| Funcionalidad | Descripción |
|--------|-------------|
| `Usurio` | Realización de todo tipo de operaciones sobre usuarios, tanto desde el programa como desde la base de datos. |
| `Videojuego` | Realización de todo tipo de operaciones sobre videojuegos, tanto desde el programa como desde la base de datos. |
| `Compras` | Realización de todo tipo de operaciones sobre compras, tanto desde el programa como desde la base de datos.. |
| `Gráficos` | Consulta de estadísticas sobre las compras. |


---


## Ejemplos

### Conexión con la base de datos

```java
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


```

### Lista de usuarios de la base de datos

```java

public List<Usuario> getAllUsuarios(Connection conexion) throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"SELECT Codigo, Username, Password, Direccion, Correo, Rol, Telefono " + " FROM " + this.tabla);
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				usuarios.add(new Usuario(resultado.getInt("Codigo"), resultado.getString("Username"),
						resultado.getString("Password"), resultado.getString("Direccion"),
						resultado.getString("Correo"), resultado.getString("Rol"), resultado.getInt("Telefono")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return usuarios;
	}

````

### Navegación entre botones

```java

if (e.getSource() == btnDetalles) {
				try {
					if (Principal.this == null || comboBox.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null, "Por favor, selecciona un videojuego del catálogo","ERROR",JOptionPane.ERROR_MESSAGE);
					} else
						new Detalles(Principal.this, inicio);

				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (e.getSource() == btnCuenta) {
				try {
					new MiCuenta(inicio);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

```


## Interfaz gráfica

El programa está dotado de una interfaz gráfica con una clase padre que le proporciona un aspecto visual común. 



## Repositorio

**Github**: https://github.com/kalandraka86/KeyGames.git


## Desarrolladores

- Alejandro Fernández Franco
- Hugo Gil Bailón
- Alfonso Jesús Anillo Romero