package models;

public class Usuario {
	
	private int Codigo;
	private String Username;
	private String Password;
	private String Direccion;
	private String Correo;
	private String Rol;
	private int Telefono;
	
	public Usuario() {
		
	}

	public Usuario(int codigo, String username, String password, String direccion, String correo, String rol,
			int telefono) {
		super();
		Codigo = codigo;
		Username = username;
		Password = password;
		Direccion = direccion;
		Correo = correo;
		Rol = rol;
		Telefono = telefono;
	}

	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		Rol = rol;
	}

	public int getTelefono() {
		return Telefono;
	}

	public void setTelefono(int telefono) {
		Telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuario [Codigo=" + Codigo + ", Username=" + Username + ", Password=" + Password + ", Direccion="
				+ Direccion + ", Correo=" + Correo + ", Rol=" + Rol + ", Telefono=" + Telefono + "]";
	}
	
	

}
