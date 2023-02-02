package com.w2m.springboot.api.superheroes.exceptions;

import java.util.Date;

public class MensajeError {

	private int codigo;
	private Date timestamp;
	private String mensaje;
	private String descripcion;

	public MensajeError() {
	}

	public MensajeError(int codigo, Date timestamp, String mensaje, String descripcion) {
		this.codigo = codigo;
		this.timestamp = timestamp;
		this.mensaje = mensaje;
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
