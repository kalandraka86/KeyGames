package mvc;

import java.util.Date;


public class Compra {
	
	private int codVideojuego;
	private int codUsuario;
	private Date fechaCompra;
	private int unidades;
	
	public Compra(int codVideojuego, int codUsuario, Date fechaCompra, int unidades) {
		super();
		this.codVideojuego = codVideojuego;
		this.codUsuario = codUsuario;
		this.fechaCompra = fechaCompra;
		this.unidades = unidades;
	}

	public Compra() {
		super();
	}

	public int getCodVideojuego() {
		return codVideojuego;
	}

	public void setCodVideojuego(int codVideojuego) {
		this.codVideojuego = codVideojuego;
	}

	public int getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	@Override
	public String toString() {
		return "Compra [codVideojuego=" + codVideojuego + ", codUsuario=" + codUsuario + ", fechaCompra=" + fechaCompra
				+ ", unidades=" + unidades + "]";
	}
	
	
	
}