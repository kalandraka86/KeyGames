package models;

public class Valoracion {

	private int codigo_usuario,codigo_videojuego,nota;
	private String comentario;
	
	public Valoracion(int codigo_usuario, int codigo_videojuego, int nota, String comentario) {
		super();
		this.codigo_usuario = codigo_usuario;
		this.codigo_videojuego = codigo_videojuego;
		this.nota = nota;
		this.comentario = comentario;
	}
	

	public Valoracion() {
		super();
	}



	public int getCodigo_usuario() {
		return codigo_usuario;
	}

	public void setCodigo_usuario(int codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	public int getCodigo_videojuego() {
		return codigo_videojuego;
	}

	public void setCodigo_videojuego(int codigo_videojuego) {
		this.codigo_videojuego = codigo_videojuego;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Valoracion [codigo_usuario=" + codigo_usuario + ", codigo_videojuego=" + codigo_videojuego + ", nota="
				+ nota + ", comentario=" + comentario + "]";
	}
	
	
}
