package com.infomovil.service.controllers

import java.lang.reflect.Method

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment
import org.springframework.http.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import com.infomovil.service.dao.GetInfomovilDAO
import com.infomovil.service.dao.editorVolante.DeleteEditorVolanteDAO;
import com.infomovil.service.dao.editorVolante.GetEditorVolanteDAO;
import com.infomovil.service.dao.editorVolante.UpsertEditorVolanteDAO;
import com.infomovil.service.model.Pais
import com.infomovil.service.model.editorVolante.VContacto
import com.infomovil.service.model.editorVolante.VUbicacion;

import org.springframework.web.bind.annotation.RequestMethod
import static com.infomovil.service.util.Util.*

@CrossOrigin(maxAge = 3600l)
@RestController
@RequestMapping("/api/editorVolante")
class EditorVolanteController extends InfomovilController {
	
	@Autowired
	protected Environment environment
	
	@Autowired
	protected GetInfomovilDAO getInfomovilDAO
	
	@Autowired
	protected UpsertEditorVolanteDAO upsertEditorVolanteDAO
	
	@Autowired
	protected DeleteEditorVolanteDAO deleteEditorVolanteDAO
	
	@Autowired
	protected GetEditorVolanteDAO getEditorVolanteDAO 
	
	
	@RequestMapping(value="getContacto", method = RequestMethod.GET )
	ResponseEntity<Object> getContacto(Long offerId, String hashUser){
		def login = okHashUser(hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, getEditorVolanteDAO.getContacto(offerId))
	}
	
	@RequestMapping(value="getUbicacion", method = RequestMethod.GET )
	ResponseEntity<Object> getUbicacion(Long offerId, String hashUser){
		def login = okHashUser(hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, getEditorVolanteDAO.getUbicacion(offerId))
	}
	
	@RequestMapping(value="publicaVolante", method = RequestMethod.POST )
	ResponseEntity<Object> publicaVolante(Long offerId, String hashUser){
		def login = okHashUser(hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, upsertEditorVolanteDAO.publicaVolante(offerId))
	}
	
	@RequestMapping(value="updateEmpresa", method = RequestMethod.POST )
	ResponseEntity<Object> updateEmpresa(Long offerId, String empresa, String hashUser){
		def login = okHashUser(hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, upsertEditorVolanteDAO.updateEmpresa(offerId,empresa))
	}
	
	@RequestMapping(value="upsertContacto", method=RequestMethod.POST)
	ResponseEntity<Object> upsertContacto(@RequestBody VContacto contacto){
		def login = okHashUser(contacto.hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, upsertEditorVolanteDAO.upsertContacto(contacto))
	}
	
	@RequestMapping(value="upsertUbicacion", method = RequestMethod.POST )
	ResponseEntity<Object> upsertUbicacion(@RequestBody VUbicacion ubicacion){
		def login = okHashUser(ubicacion.hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, upsertEditorVolanteDAO.upsertUbicacion(ubicacion))
	}
	
	@RequestMapping(value="deleteContacto", method = RequestMethod.DELETE )
	ResponseEntity<Object> deleteContacto(@RequestBody VContacto contacto){
		def login = okHashUser(contacto.hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, deleteEditorVolanteDAO.deleteContacto(contacto))
	}
	
	@RequestMapping(value="deleteUbicacion", method = RequestMethod.DELETE )
	ResponseEntity<Object> deleteUbicacion(@RequestBody VUbicacion ubicacion){
		def login = okHashUser(ubicacion.hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, deleteEditorVolanteDAO.deleteUbicacion(ubicacion))
	}
	
	
}
