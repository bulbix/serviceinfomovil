package com.infomovil.service.controllers

import org.apache.log4j.Logger
import org.springframework.http.ResponseEntity
import com.infomovil.service.dao.GetInfomovilDAO
import static com.infomovil.service.util.Util.*
import org.springframework.http.*

class InfomovilController {
	
	private static final Logger logger = Logger.getLogger(InfomovilController.class);
	
	protected ResponseEntity<Object> executeDAO(String hashUser,GetInfomovilDAO getInfomovilDAO, Map<String,Object> result){
		try{
			if(okHashUser(hashUser, getInfomovilDAO)){
				return new ResponseEntity<Object>(result, HttpStatus.OK)
			}
			else{
				def error = [codeError:-1, msgError:'Credenciales Incorrectas']
				return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND)
			}
		}
		catch(Exception e){
			logger.error(e)
			def error = [codeError:-1, msgError:e.getMessage()]
			return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR)
		}
		
	}

}
