package com.infomovil.service.controllers

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.infomovil.service.business.DeleteEditorPaginaService
import com.infomovil.service.business.GetEditorPaginaService
import com.infomovil.service.business.UpsertEditorPaginaService
import com.infomovil.service.dao.GetInfomovilDAO
import com.infomovil.service.dao.editorPagina.DeleteEditorPaginaDAO
import com.infomovil.service.dao.editorPagina.GetEditorPaginaDAO
import com.infomovil.service.dao.editorPagina.UpsertEditorPaginaDAO
import com.infomovil.service.dao.editorVolante.DeleteEditorVolanteDAO;
import com.infomovil.service.dao.editorVolante.GetEditorVolanteDAO
import com.infomovil.service.model.editorPagina.Ubicacion
import com.infomovil.service.model.editorVolante.VUbicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*
import org.springframework.web.bind.annotation.RequestMethod
import static com.infomovil.service.util.Util.*

@CrossOrigin(maxAge = 3600l)
@RestController
@RequestMapping("/api/editorPagina")
class EditorPaginaController extends InfomovilController {
	
	@Autowired
	protected GetInfomovilDAO getInfomovilDAO
	
	@Autowired
	protected GetEditorPaginaService getEditorPaginaService
	
	@Autowired
	protected UpsertEditorPaginaService upsertEditorPaginaService
	
	@Autowired
	protected DeleteEditorPaginaService deleteEditorPaginaService

	
	@RequestMapping(value="getUbicacion", method = RequestMethod.GET )
	ResponseEntity<Object> getUbicacion(String hashUser){
		def credenciales = getCredenciales(hashUser)
		def login = okHashUser(hashUser, getInfomovilDAO);
		return executeService(login.pa_DomainId, getEditorPaginaService.getUbicacion(credenciales[0]))
	}
	
	@RequestMapping(value="upsertUbicacion", method = RequestMethod.POST )
	ResponseEntity<Object> upsertUbicacion(@RequestBody Ubicacion ubicacion){
		def credenciales = getCredenciales(ubicacion.hashUser)
		def login = okHashUser(ubicacion.hashUser, getInfomovilDAO);
		ubicacion.idDominio = login.pa_DomainId
		return executeService(login.pa_DomainId, upsertEditorPaginaService.upsertUbicacion(credenciales[0],ubicacion))
	}
	
	@RequestMapping(value="deleteUbicacion", method = RequestMethod.DELETE )
	ResponseEntity<Object> deleteUbicacion(@RequestBody Ubicacion ubicacion){
		def credenciales = getCredenciales(ubicacion.hashUser)
		def login = okHashUser(ubicacion.hashUser, getInfomovilDAO)
		ubicacion.idDominio = login.pa_DomainId
		return executeService(login.pa_DomainId, deleteEditorPaginaService.deleteUbicacion(credenciales[0], ubicacion))
	}
	
}
