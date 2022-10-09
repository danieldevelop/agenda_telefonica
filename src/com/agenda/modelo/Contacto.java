package com.agenda.modelo;

public class Contacto {

	private Integer id;
	private String nombre;
	private String apellidos;
	private Integer movil;
	private Integer Trabajo;
	private String correo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getMovil() {
		return movil;
	}

	public void setMovil(Integer movil) {
		this.movil = movil;
	}

	public Integer getTrabajo() {
		return Trabajo;
	}

	public void setTrabajo(Integer trabajo) {
		Trabajo = trabajo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
