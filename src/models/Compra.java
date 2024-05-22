package models;

import java.util.Date;

public class Compra {

	private int codVideojuego;
	private int codUsuario;
	private java.sql.Timestamp fechaCompra;
	private int unidades;

	public Compra(int codVideojuego, int codUsuario, java.sql.Timestamp fechaCompra, int unidades) {
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

	public java.sql.Timestamp getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(java.sql.Timestamp fechaCompra) {
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