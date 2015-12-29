package com.infomovil.service.business

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*
import com.infomovil.service.dao.editorPagina.GetEditorPaginaDAO
import com.infomovil.service.dao.editorPagina.UpsertEditorPaginaDAO;
import com.infomovil.service.model.editorPagina.KeywordData
import com.infomovil.service.model.editorPagina.Ubicacion

@Service
class GetEditorPaginaService {

	@Autowired
	protected GetEditorPaginaDAO getEditorPaginaDAO
	
	@Autowired
	protected UpsertEditorPaginaDAO upsertEditorPaginaDAO
	
	def getUbicacion(String email){
		println "Correo " + email
		def mapUbicacion = getEditorPaginaDAO.getUbicacion(email)
		println mapUbicacion
		def direccion = getEditorPaginaDAO.getKeywordData(email).keywordData.find{it.keywordField == "a1"}
		println direccion?.keywordValue
		mapUbicacion.ubicacion[0].direccion = direccion?.keywordValue
		[codeError:0, ubicacion:mapUbicacion.ubicacion]
	}
	
	
	
}
