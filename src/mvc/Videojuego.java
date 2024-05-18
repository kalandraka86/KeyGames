package mvc;

public class Videojuego {
	private int Codigo;
	private String Nombre;
	private String Descripcion;
	private String Genero;
	private String Imagen;
	private int Stock;
	private String Plataformas;
	private int Precio;
	
	public Videojuego() {
		
	}
	
	public Videojuego(int codigo, String nombre, String descripcion, String genero, String imagen, int stock, String plataformas, int precio) {
		super();
		Codigo = codigo;
		Nombre = nombre;
		Descripcion = descripcion;
		Genero = genero;
		Imagen = imagen;
		Stock = stock;
		Plataformas = plataformas;
		Precio = precio;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public String getPlataformas() {
		return Plataformas;
	}

	public void setPlataformas(String plataformas) {
		Plataformas = plataformas;
	}

	public int getPrecio() {
		return Precio;
	}

	public void setPrecio(int precio) {
		Precio = precio;
	}

	@Override
	public String toString() {
		return Nombre;
	}

	
	
	
}
