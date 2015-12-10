package com.infomovil.service.model

class Pais {

    String paisid
	String paisnombre
	String usuario

 
	
	@Override
	public String toString() {
		return String.format("(%s) %s",paisid,paisnombre)
	}
}
