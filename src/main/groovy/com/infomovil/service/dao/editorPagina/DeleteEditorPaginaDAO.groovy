package com.infomovil.service.dao.editorPagina

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource

import com.infomovil.service.model.editorPagina.Ubicacion
import com.infomovil.service.model.editorVolante.VContacto
import com.infomovil.service.model.editorVolante.VUbicacion
import org.springframework.stereotype.Repository

import static com.infomovil.service.util.Util.*

@Repository
class DeleteEditorPaginaDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	def deleteUbicacion(Ubicacion ubicacion){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paDomainId", ubicacion.idDominio)
		callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFODELETE_PG2","SP_DELETELOCRECORD", inparams)
		[codeError:0]
	}

}
