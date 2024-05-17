package examples;

public class Videojuego {
	private int codigo;
	private String nombre, descripcion, genero, image;
	private int stock;
	private float precio;

	public Videojuego(int codigo, String nombre, String descripcion, String genero, String image, int stock,
			float precio) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.genero = genero;
		this.image = image;
		this.stock = stock;
		this.precio = precio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Videojuego [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", genero="
				+ genero + ", image=" + image + ", stock=" + stock + ", precio=" + precio + "]";
	}

}
