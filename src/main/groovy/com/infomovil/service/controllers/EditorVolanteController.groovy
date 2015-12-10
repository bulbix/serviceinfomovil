package com.infomovil.service.controllers

import java.lang.reflect.Method

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import com.infomovil.service.dao.EditorVolanteDAO;
import com.infomovil.service.dao.GetInfomovilDAO
import com.infomovil.service.model.Pais
import org.springframework.web.bind.annotation.RequestMethod;
import static com.infomovil.service.util.Util.*

@CrossOrigin(maxAge = 3600l)
@RestController
@RequestMapping("/api/editorVolante")
class EditorVolanteController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private EditorVolanteDAO editorVolanteDAO
	
	@Autowired
	private GetInfomovilDAO getInfomovilDAO
	
	
	@RequestMapping(value="getPaises", method = RequestMethod.GET )
	ResponseEntity<Object> getPaises(String hashUser){
		
		if(environment.getActiveProfiles()[0] == "dev"){
			println "ok dev"
		}
		
		if(okHashUser(hashUser, getInfomovilDAO)){
			return new ResponseEntity<Object>(editorVolanteDAO.getPaises("%"), HttpStatus.OK)
		}
		else{
			def error = [codeError:-1, msgError:'Credenciales Incorrectas']
			return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND)
		}
	}
	
	def upsertContacto(){
		
	}
	
	
}
