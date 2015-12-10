package com.infomovil.service.model

class ProductoUsuario {
	
	String email
	long userId
	long productoId
	String claveComercial
	String descripcion
	String abc
	String fechaInicio
	String fechaFin
	String referencia
	String nombreUsuario
	String nombreUsuarioLanding
	int statusProducto
	long domainId
	String nombreDominio
	boolean renovable
	String urlDominio
	String urlRenovar
	boolean activo
	
	@Override
	public String toString() {
		return "ProductoUsuarioVO [email=" + email + ", userId=" + userId + ", productoId=" + productoId
				+ ", claveComercial=" + claveComercial + ", descripcion=" + descripcion + ", abc=" + abc
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", referencia=" + referencia
				+ ", nombreUsuario=" + nombreUsuario + ", nombreUsuarioLanding=" + nombreUsuarioLanding
				+ ", statusProducto=" + statusProducto + ", domainId=" + domainId + ", nombreDominio=" + nombreDominio
				+ ", renovable=" + renovable + ", urlDominio=" + urlDominio + "]"
	}

}
