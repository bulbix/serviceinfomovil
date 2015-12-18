package com.infomovil.service.business

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service

import com.infomovil.service.dao.editorPagina.GetEditorPaginaDAO;
import com.infomovil.service.dao.editorPagina.UpsertEditorPaginaDAO;
import com.infomovil.service.model.editorPagina.KeywordData
import com.infomovil.service.model.editorPagina.Ubicacion;

@Service
class UpsertEditorPaginaService {
	
	@Autowired
	protected GetEditorPaginaDAO getEditorPaginaDAO
	
	@Autowired
	protected UpsertEditorPaginaDAO upsertEditorPaginaDAO
	
	def upsertUbicacion(String email, Ubicacion ubicacion){
		upsertEditorPaginaDAO.upsertUbicacion(ubicacion)
		
		KeywordData direccionFound = getEditorPaginaDAO.getKeywordData(email).keywordData.find{it.keywordField == "a1"}
		
		String[] keywordsDireccion = ["a1","a2","a3","tc","sp","c","pc"];
		
		if(!direccionFound && ubicacion.direccion){
			keywordsDireccion.each{ keywordField ->
				try{
					String resultDireccionMap = upsertEditorPaginaDAO.insertKeywordData(new KeywordData(
							idDominio:ubicacion.idDominio,
							keywordField:keywordField,
							keywordValue:keywordField=="a1"?ubicacion.direccion:"",
							keywordPos:getEditorPaginaDAO.getPriord(ubicacion.idDominio)
							));
				}catch(Exception e){}
			}
		}
		else{
			direccionFound.keywordValue = ubicacion.direccion
			direccionFound.idDominio = ubicacion.idDominio
			String resultDireccionMap = upsertEditorPaginaDAO.updateKeywordData(direccionFound)
		}
		
		[codeError:0]
		
	}

}
