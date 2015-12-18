package com.infomovil.service.business

import com.infomovil.service.dao.editorPagina.DeleteEditorPaginaDAO
import com.infomovil.service.dao.editorPagina.GetEditorPaginaDAO
import com.infomovil.service.dao.editorPagina.UpsertEditorPaginaDAO;
import com.infomovil.service.model.editorPagina.KeywordData
import com.infomovil.service.model.editorPagina.Ubicacion

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DeleteEditorPaginaService {
	
	@Autowired
	protected GetEditorPaginaDAO getEditorPaginaDAO
	
	@Autowired
	protected DeleteEditorPaginaDAO deleteEditorPaginaDAO
	
	@Autowired
	protected UpsertEditorPaginaDAO upsertEditorPaginaDAO
	
	def deleteUbicacion(String email, Ubicacion ubicacion){
		deleteEditorPaginaDAO.deleteUbicacion(ubicacion)
		KeywordData direccionFound = getEditorPaginaDAO.getKeywordData(email).keywordData.find{it.keywordField == "a1"}
		
		if(direccionFound){
			direccionFound.keywordValue = " "
			direccionFound.idDominio = ubicacion.idDominio
			String resultDireccionMap = upsertEditorPaginaDAO.updateKeywordData(direccionFound)
		}
		
		[codeError:0]
		
	}

}
