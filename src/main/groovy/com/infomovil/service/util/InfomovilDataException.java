package com.infomovil.service.util;

public class InfomovilDataException extends Exception {
	
	Integer errorCode;
	
	public Integer getErrorCode() {
		return errorCode;
	}

	public InfomovilDataException(Integer errorCode, String message){
		super(message);		
		this.errorCode = errorCode;
	}

}
