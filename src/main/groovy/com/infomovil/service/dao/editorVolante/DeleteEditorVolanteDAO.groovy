package com.infomovil.service.dao.editorVolante

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource

import com.infomovil.service.model.editorVolante.VContacto
import com.infomovil.service.model.editorVolante.VUbicacion
import org.springframework.stereotype.Repository

import static com.infomovil.service.util.Util.*

@Repository
class DeleteEditorVolanteDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	def deleteContacto(VContacto contacto){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_ContactoID", contacto.contactoId)
		callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_VOLANTE_PG2","SP_DELETE_CONTACTO", inparams, null)
		[codeError:0]
	}
	
	def deleteUbicacion(VUbicacion ubicacion){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_ContactoID", ubicacion.locId)
		callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_VOLANTE_PG2","SP_DELETE_UBICACION", inparams, null)
		[codeError:0]
	}

}
